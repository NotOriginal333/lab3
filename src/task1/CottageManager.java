package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CottageManager {
    private final List<Cottage> cottages;
    private double totalIncome;
    private double totalExpenses;

    public CottageManager() {
        this.cottages = new ArrayList<>();
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
    }

    public void addCottage(Cottage cottage) {
        cottages.add(cottage);
        updateTotalExpenses(cottage.getExpenses());
    }

    public List<Cottage> getCottages() {
        return cottages;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void updateTotalIncome(double amount) {
        this.totalIncome += amount;
    }

    public void updateTotalExpenses(double amount) {
        this.totalExpenses += amount;
    }

    public String generateFinancialReport() {
        return "Total Income: " + getTotalIncome() + "\nTotal Expenses: " + getTotalExpenses() +
                "\nNet Profit: " + (getTotalIncome() - getTotalExpenses());
    }

    public List<Cottage> filterCottagesByAmenity(String amenityName) {
        return getCottages().stream()
                .filter(cottage -> cottage.getAmenities().stream()
                        .anyMatch(amenity -> amenity.getName().equalsIgnoreCase(amenityName)))
                .collect(Collectors.toList());
    }

    public List<Cottage> filterCottagesByCategory(String category) {
        return getCottages().stream()
                .filter(cottage -> cottage.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
