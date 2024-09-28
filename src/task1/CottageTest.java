package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

public class CottageTest {

    private Cottage cottage;
    private Amenity sofaBed;

    @BeforeEach
    public void setUp() {
        sofaBed = new Amenity("Sofa Bed", 50, 2);
        cottage = new Cottage("Cottage1", "Luxury", 200, true,
                4, 4, List.of());
    }

    @Test
    public void testAddAmenity() {
        cottage.addAmenity(sofaBed);
        assertEquals(6, cottage.getTotalCapacity());
    }

    @Test
    public void testBookCottage() throws BookingException {
        Calendar start = getDate(Calendar.MARCH, 1);
        Calendar end = getDate(Calendar.MARCH, 5);
        cottage.bookCottage("John Doe", start, end, 100);
        assertEquals(1, cottage.getBookings().size());
    }

    @Test
    public void testIsAvailableDuring() throws BookingException {

        Calendar start = getDate(Calendar.MARCH, 1);
        Calendar end = getDate(Calendar.MARCH, 5);
        cottage.bookCottage("John Doe", start, end, 100);
        assertFalse(cottage.isAvailableDuring(start, end));
    }

    @Test
    public void testCalculatePrice() throws BookingException {
        Calendar start = getDate(Calendar.NOVEMBER, 1);
        Calendar end = getDate(Calendar.NOVEMBER, 5);
        double price = cottage.calculatePrice(start, end, 200);
        assertEquals(640, price, 0.01);
    }

    private Calendar getDate(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, month, day);
        return calendar;
    }
}
