//package task1;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Hotel {
//    private final String name;
//    private final List<Cottage> cottages;
//    private final List<Amenity> hotelAmenities;
//    private double income;
//    private double expenses;
//
//    public Hotel(String name) {
//        this.name = name;
//        this.cottages = new ArrayList<>();
//        this.hotelAmenities = new ArrayList<>();
//        this.income = 0.0;
//        this.expenses = 0.0;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Cottage> getCottages() {
//        return cottages;
//    }
//
//    public double getIncome() {
//        return income;
//    }
//
//    public double getExpenses() {
//        return expenses;
//    }
//
//    public List<Amenity> getHotelAmenities() {
//        return hotelAmenities;
//    }
//
//    public void setIncome(double income) {
//        this.income = income;
//    }
//
//    public void setExpenses(double expenses) {
//        this.expenses = expenses;
//    }
//
//    public void addHotelAmenity(Amenity amenity) {
//        getHotelAmenities().add(amenity);
//        setExpenses(amenity.getCost() + getExpenses());
//    }
//
//    public void addCottage(Cottage cottage) {
//        getCottages().add(cottage);
//        setExpenses(cottage.getPricePerNight() + getExpenses());
//    }
//
//    public List<Amenity> getAllAmenitiesForCottage(Cottage cottage) {
//        List<Amenity> combinedAmenities = new ArrayList<>(cottage.getAmenities());
//        combinedAmenities.addAll(getHotelAmenities());
//        return combinedAmenities;
//    }
//
//    public List<Cottage> filterCottagesByAmenity(String amenityName) {
//        return getCottages().stream()
//                .filter(cottage -> cottage.getAmenities().stream()
//                        .anyMatch(amenity -> amenity.getName().equalsIgnoreCase(amenityName)))
//                .collect(Collectors.toList());
//    }
//
//    public List<Cottage> filterCottagesByCategory(String category) {
//        return getCottages().stream()
//                .filter(cottage -> cottage.getCategory().equalsIgnoreCase(category))
//                .collect(Collectors.toList());
//    }
//
//    public void updateIncome(double amount) {
//        setIncome(getIncome() + amount);
//    }
//
//    public String generateFinancialReport() {
//        return "Hotel: " + getName() + "\nIncome: " + getIncome() + "\nExpenses: " + getExpenses() +
//                "\nNet Profit: " + (getIncome() - getExpenses());
//    }
//}