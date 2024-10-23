package useless;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task1.Amenity;
import task1.BookingException;
import task1.Cottage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

public class CottageTest {

    private Cottage cottage;
    private Amenity sofaBed;

    @BeforeEach
    public void setUp() {
        sofaBed = new Amenity("Sofa Bed", 50, 2);
        cottage = new Cottage("Cottage1", "Luxury", 200,
                4, 4, List.of());
    }

    @Test
    public void testAddAmenity() {
        cottage.addAmenity(sofaBed);
        assertEquals(6, cottage.getTotalCapacity());
    }

    @Test
    public void testBookCottage() throws BookingException {
        Calendar start = getDate(Calendar.JANUARY, 1);
        Calendar end = getDate(Calendar.JANUARY, 5);
        cottage.bookCottage("John Doe", start, end);
        assertEquals(1, cottage.getBookings().size());
        assertEquals(800, cottage.calculatePrice(start, end, cottage.getPricePerNight()));
    }

    @Test
    public void testIsAvailableDuring() throws BookingException {
        Calendar start = getDate(Calendar.MARCH, 1);
        Calendar end = getDate(Calendar.MARCH, 5);
        cottage.bookCottage("John Doe", start, end);
        assertFalse(cottage.isAvailableDuring(start, end));
    }

    @Test
    public void testBookCottageAlreadyBooked() {
        Calendar start1 = getDate(Calendar.JANUARY, 1);
        Calendar end1 = getDate(Calendar.JANUARY, 5);

        try {
            cottage.bookCottage("Client1", start1, end1);
        } catch (BookingException e) {
            fail("Booking should have succeeded.");
        }

        Calendar start2 = getDate(Calendar.JANUARY, 1);
        Calendar end2 = getDate(Calendar.JANUARY, 5);

        assertThrows(BookingException.class, () ->
                cottage.bookCottage("Client2", start2, end2),
                "Expected to throw BookingException due to overlapping dates.");
    }

    @Test
    public void testBookCottageInvalidDates() {
        Calendar start = getDate(Calendar.JANUARY, 10);
        Calendar end = getDate(Calendar.JANUARY, 5);

        assertThrows(BookingException.class, () ->
                cottage.bookCottage("Client", start, end),
                "Expected to throw BookingException due to invalid dates.");
    }


    @Test
    public void testCalculatePrice() {
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
