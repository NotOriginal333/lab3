package task1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Hotel myHotel = new Hotel("Sunny Resort");

        Amenity wifi = new Amenity("WiFi", 10, 0);
        Amenity kitchen = new Amenity("Kitchen", 20, 0);
        Amenity sofa = new Amenity("Sofa", 30, 1);
        Amenity crib = new Amenity("Crib", 15, 1);
        Amenity spa = new Amenity("SPA", 100, 0);

        myHotel.addHotelAmenity(spa);

        List<Amenity> cottage1Amenities = new ArrayList<>(Arrays.asList(wifi, sofa));
        Cottage cottage1 = new Cottage("Big one", "Luxury", 200, true,
                4, 4, cottage1Amenities);

        List<Amenity> cottage2Amenities = new ArrayList<>(Arrays.asList(kitchen, crib));
        Cottage cottage2 = new Cottage("Not big", "Standard", 100, true,
                3, 3, cottage2Amenities);

        myHotel.addCottage(cottage1);
        myHotel.addCottage(cottage2);

        System.out.println("All amenities for Cottage 1:");
        List<Amenity> allAmenitiesCottage1 = myHotel.getAllAmenitiesForCottage(cottage1);
        for (Amenity amenity : allAmenitiesCottage1) {
            System.out.println(amenity.getName());
        }

        try {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            startDate.set(2024 - 1900, Calendar.NOVEMBER, 1);
            endDate.set(2024 - 1900, Calendar.NOVEMBER, 5);
            cottage1.bookCottage("John Doe", startDate, endDate, 100);
            myHotel.updateIncome(cottage1.getPricePerNight());
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }

        try {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            startDate.set(2024 - 1900, Calendar.NOVEMBER, 1);
            endDate.set(2024 - 1900, Calendar.NOVEMBER, 5);
            cottage2.bookCottage("Jane Smith", startDate, endDate, 100);
            myHotel.updateIncome(cottage2.getPricePerNight());
        } catch (BookingException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Cottages with WiFi:");
        List<Cottage> cottagesWithWifi = myHotel.filterCottagesByAmenity("WiFi");
        for (Cottage cottage : cottagesWithWifi) {
            System.out.println(cottage.getName());
        }

        System.out.println("Luxury cottages:");
        List<Cottage> luxuryCottages = myHotel.filterCottagesByCategory("Luxury");
        for (Cottage cottage : luxuryCottages) {
            System.out.println(cottage.getName());
        }

        System.out.println("\nFinancial Report:");
        System.out.println(myHotel.generateFinancialReport());
    }
}
