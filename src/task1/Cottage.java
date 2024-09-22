package task1;

import java.util.*;

public class Cottage {
    final private String category;
    final private double pricePerNight;
    final private Boolean isAvailable;
    final private int maxCapacity;
    private int totalCapacity;
    final private List<Amenity> amenities;
    final private List<Booking> bookings;

    public Cottage(String category, double pricePerNight, Boolean isAvailable,
                   int maxCapacity, int totalCapacity, List<Amenity> amenities) {
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.maxCapacity = maxCapacity;
        this.totalCapacity = totalCapacity;
        this.amenities = Objects.requireNonNullElseGet(amenities, ArrayList::new);
        this.bookings = new ArrayList<>();
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
        amenities.add(amenity);
        totalCapacity = maxCapacity + amenity.getGuestIncrease();
    }

    public void bookCottage(String client, Date start, Date end) throws BookingException {
        for (Booking booking : bookings) {
            if (booking.overlapsWith(start, end)) {
                throw new BookingException("Cottage is already booked during this period.");
            }
        }

        bookings.add(new Booking(client, start, end, calculatePrice(start, end)));
        System.out.println("Cottage booked successfully for " + client + " from " + start + " to " + end);
    }

    public boolean isAvailableDuring(Date start, Date end) {
        for (Booking booking : bookings) {
            if (booking.overlapsWith(start, end)) {
                return false;
            }
        }
        return true;
    }

    private double calculatePrice(Date start, Date end) {
        long duration = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
        double totalPrice = duration * pricePerNight;

        if (isLowSeason(start) || isLowSeason(end)) {
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