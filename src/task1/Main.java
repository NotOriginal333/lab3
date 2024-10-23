package task1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CottageManager myCottageManager = new CottageManager();

        Amenity wifi = new Amenity("WiFi", 10, 0);
        Amenity kitchen = new Amenity("Kitchen", 20, 0);
        Amenity sofa = new Amenity("Sofa", 30, 1);
        Amenity crib = new Amenity("Crib", 15, 1);

        List<Amenity> cottage1Amenities = Arrays.asList(wifi, sofa);
        Cottage cottage1 = new Cottage("Big one", "Luxury", 200,
                4, 4, cottage1Amenities);

        List<Amenity> cottage2Amenities = Arrays.asList(kitchen, crib);
        Cottage cottage2 = new Cottage("Not big", "Standard", 100,
                3, 3, cottage2Amenities);

        myCottageManager.addCottage(cottage1);
        myCottageManager.addCottage(cottage2);

        System.out.println("All amenities for Cottage 1:");
        List<Amenity> allAmenitiesCottage1 = cottage1.getAmenities();
        for (Amenity amenity : allAmenitiesCottage1) {
            System.out.println(amenity.getName());
        }

        try {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            startDate.set(2024, Calendar.NOVEMBER, 1);
            endDate.set(2024, Calendar.NOVEMBER, 5);
            cottage1.bookCottage("John Doe", startDate, endDate);
            myCottageManager.updateTotalIncome(cottage1.getPricePerNight());
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }

        try {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            startDate.set(2024, Calendar.NOVEMBER, 1);
            endDate.set(2024, Calendar.NOVEMBER, 5);
            cottage2.bookCottage("Jane Smith", startDate, endDate);
            myCottageManager.updateTotalIncome(cottage2.getPricePerNight());
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Cottages with WiFi:");
        List<Cottage> cottagesWithWifi = myCottageManager.filterCottagesByAmenity("WiFi");
        for (Cottage cottage : cottagesWithWifi) {
            System.out.println(cottage.getName());
        }

        System.out.println("Luxury cottages:");
        List<Cottage> luxuryCottages = myCottageManager.filterCottagesByCategory("Luxury");
        for (Cottage cottage : luxuryCottages) {
            System.out.println(cottage.getName());
        }

        System.out.println("\nFinancial Report:");
        System.out.println(myCottageManager.generateFinancialReport());

        System.out.println();

        try {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            startDate.set(2024, Calendar.NOVEMBER, 5);
            endDate.set(2024, Calendar.NOVEMBER, 1);
            cottage2.bookCottage("John Bender", startDate, endDate);
            myCottageManager.updateTotalIncome(cottage2.getPricePerNight());
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }
    }
}
