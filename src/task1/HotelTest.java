package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class HotelTest {

    private Hotel hotel;
    private Cottage cottage1;
    private Cottage cottage2;
    private Amenity wifi;
    private Amenity spa;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel("Test Hotel");
        wifi = new Amenity("WiFi", 10, 0);
        spa = new Amenity("SPA", 100, 0);
        cottage1 = new Cottage("Cottage1", "Luxury", 200, true,
                4, 4, List.of(wifi));
        cottage2 = new Cottage("Cottage2", "Standard", 150, true,
                3, 3, List.of());
    }

    @Test
    public void testAddCottage() {
        hotel.addCottage(cottage1);
        assertEquals(1, hotel.getCottages().size());
        assertEquals(200, hotel.getExpenses());
    }

    @Test
    public void testAddHotelAmenity() {
        hotel.addHotelAmenity(spa);
        assertTrue(hotel.getHotelAmenities().contains(spa));
        assertEquals(100, hotel.getExpenses());
    }

    @Test
    public void testGetAllAmenitiesForCottage() {
        hotel.addHotelAmenity(spa);
        assertTrue(hotel.getAllAmenitiesForCottage(cottage1).contains(spa));
        assertTrue(hotel.getAllAmenitiesForCottage(cottage1).contains(wifi));
    }

    @Test
    public void testFilterCottagesByAmenity() {
        hotel.addCottage(cottage1);
        hotel.addCottage(cottage2);
        assertEquals(1, hotel.filterCottagesByAmenity("WiFi").size());
    }

    @Test
    public void testGenerateFinancialReport() {
        hotel.addCottage(cottage1);
        hotel.addHotelAmenity(spa);
        hotel.updateIncome(1000);
        String report = hotel.generateFinancialReport();
        assertTrue(report.contains("Income: 1000.0"));
        assertTrue(report.contains("Expenses: 300.0"));
    }
}
