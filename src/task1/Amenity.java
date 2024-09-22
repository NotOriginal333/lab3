package task1;

public class Amenity {
    final private String name;
    final private int cost;
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

    public Amenity(String name, int cost, int guestIncrease) {
        this.name = name;
        this.cost = cost;
        this.guestIncrease = guestIncrease;
    }
}