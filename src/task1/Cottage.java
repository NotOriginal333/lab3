package task1;

import java.text.SimpleDateFormat;
import java.util.*;

public class Cottage {
    final private String name;
    final private String category;
    final private double pricePerNight;
    final private Boolean isAvailable;
    final private int maxCapacity;
    private int totalCapacity;
    final private List<Amenity> amenities;
    final private List<Booking> bookings;

    public Cottage(String name, String category, double pricePerNight, Boolean isAvailable,
                   int maxCapacity, int totalCapacity, List<Amenity> amenities) {
        this.name = name;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.maxCapacity = maxCapacity;
        this.totalCapacity = totalCapacity;
        this.amenities = new ArrayList<>(Objects.requireNonNullElseGet(amenities, ArrayList::new));
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void addAmenity(Amenity amenity) {
        if (amenities == null) {
            return;
        }
        amenities.add(amenity);
        totalCapacity = maxCapacity + amenity.getGuestIncrease();
    }

    public void bookCottage(String client, Calendar start, Calendar end, int price) throws BookingException {
        boolean overlapExists = bookings.stream()
                .anyMatch(booking -> booking.overlapsWith(start, end));

        if (overlapExists) {
            throw new BookingException("Incorrect date!");
        }

        bookings.add(new Booking(client, start, end, calculatePrice(start, end, price)));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String startDate = sdf.format(start.getTime());
        String endDate = sdf.format(end.getTime());
        System.out.println("Cottage booked successfully for " + client + " from " + startDate + " to " + endDate);
    }

    public boolean isAvailableDuring(Calendar start, Calendar end) {
        return bookings.stream()
                .noneMatch(booking -> booking.overlapsWith(start, end));
    }

    public double calculatePrice(Calendar start, Calendar end, double pricePerNight) {
        long durationInMillis = end.getTimeInMillis() - start.getTimeInMillis();

        long durationInDays = durationInMillis / (1000 * 60 * 60 * 24);

        double totalPrice = durationInDays * pricePerNight;

        if (isLowSeason(start.getTime()) || isLowSeason(end.getTime())) {
            totalPrice *= 0.8;
        }

        return totalPrice;
    }

    private boolean isLowSeason(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month == Calendar.NOVEMBER || month == Calendar.MARCH;
    }
}