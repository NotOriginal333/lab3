package task1;

public class Amenity {
    final private String name;
    final private int cost;
    final private double expenses;
    final private int guestIncrease;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getGuestIncrease() {
        return guestIncrease;
    }

    public double getExpenses() {
        return expenses;
    }

    public Amenity(String name, int cost, int guestIncrease, double expenses) {
        this.name = name;
        this.cost = cost;
        this.guestIncrease = guestIncrease;
        this.expenses = expenses;
    }
}