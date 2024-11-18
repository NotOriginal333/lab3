package task1;

import java.text.SimpleDateFormat;
import java.util.*;

public class Cottage {
    final private String name;
    final private String category;
    final private double pricePerNight;
    final private int maxCapacity;
    private int totalCapacity;
    private double expenses;
    final private List<Amenity> amenities=new ArrayList<>();
    final private List<Booking> bookings;

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public Cottage(String name, String category, double pricePerNight,
                   int maxCapacity, int totalCapacity, double expenses) {
        this.name = name;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.maxCapacity = maxCapacity;
        this.totalCapacity = totalCapacity;
        this.expenses = expenses;
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void addAmenity(Amenity amenity) {
        if (getAmenities() == null) {
            return;
        }
        getAmenities().add(amenity);
        setExpenses(getExpenses() + amenity.getExpenses());
        setTotalCapacity(getMaxCapacity() + amenity.getGuestIncrease());
    }

    public void bookCottage(String client, Calendar start, Calendar end) throws BookingException {
        if (start.after(end)) {
            throw new BookingException("Start date cannot be after end date.", getName(), client);
        }

        boolean overlapExists = getBookings().stream()
                .anyMatch(booking -> booking.overlapsWith(start, end));

        if (overlapExists) {
            throw new BookingException("Incorrect date!", getName(), client);
        }

        double totalPrice = calculatePrice(start, end, getPricePerNight());
        getBookings().add(new Booking(client, start, end, calculatePrice(start, end, getPricePerNight())));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String startDate = sdf.format(start.getTime());
        String endDate = sdf.format(end.getTime());
        System.out.println("Cottage booked successfully for " + client + " from " + startDate + " to " + endDate);
        System.out.println("Total price: $" + totalPrice);
    }

    public boolean isAvailableDuring(Calendar start, Calendar end) {
        return getBookings().stream()
                .noneMatch(booking -> booking.overlapsWith(start, end));
    }

    public double getIncome() {
         return getBookings().stream()
            .mapToDouble(Booking::getTotalPrice)
            .sum();
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