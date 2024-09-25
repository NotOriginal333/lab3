package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private final String name;
    private final List<Cottage> cottages;
    private final List<Amenity> hotelAmenities;
    private double income;
    private double expenses;

    public Hotel(String name) {
        this.name = name;
        this.cottages = new ArrayList<>();
        this.hotelAmenities = new ArrayList<>();
        this.income = 0.0;
        this.expenses = 0.0;
    }

    public String getName() {
        return name;
    }

    public List<Cottage> getCottages() {
        return cottages;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }

    public List<Amenity> getHotelAmenities() {
        return hotelAmenities;
    }

    public void addHotelAmenity(Amenity amenity) {
        hotelAmenities.add(amenity);
        expenses += amenity.getCost();
    }

    public void addCottage(Cottage cottage) {
        cottages.add(cottage);
        expenses += cottage.getPricePerNight();
    }

    public List<Amenity> getAllAmenitiesForCottage(Cottage cottage) {
        List<Amenity> combinedAmenities = new ArrayList<>(cottage.getAmenities());
        combinedAmenities.addAll(hotelAmenities);
        return combinedAmenities;
    }

    public List<Cottage> filterCottagesByAmenity(String amenityName) {
        return cottages.stream()
                .filter(cottage -> cottage.getAmenities().stream()
                        .anyMatch(amenity -> amenity.getName().equalsIgnoreCase(amenityName)))
                .collect(Collectors.toList());
    }

    public List<Cottage> filterCottagesByCategory(String category) {
        return cottages.stream()
                .filter(cottage -> cottage.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public void updateIncome(double amount) {
        income += amount;
    }

    public String generateFinancialReport() {
        return "Hotel: " + name + "\nIncome: " + income + "\nExpenses: " + expenses +
                "\nNet Profit: " + (income - expenses);
    }
}