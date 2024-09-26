package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
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
        Date start = getDate(Calendar.MARCH, 1);
        Date end = getDate(Calendar.MARCH, 5);
        cottage.bookCottage("John Doe", start, end);
        assertEquals(1, cottage.getBookings().size());
    }

    @Test
    public void testIsAvailableDuring() throws BookingException {
        Date start = getDate(Calendar.MARCH, 1);
        Date end = getDate(Calendar.MARCH, 5);
        cottage.bookCottage("John Doe", start, end);
        assertFalse(cottage.isAvailableDuring(start, end));
    }

    @Test
    public void testCalculatePrice() throws BookingException {
        Date start = getDate(Calendar.NOVEMBER, 1);
        Date end = getDate(Calendar.NOVEMBER, 5);
        double price = cottage.calculatePrice(start, end);
        assertEquals(640, price, 0.01);
    }

    private Date getDate(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, month, day);
        return calendar.getTime();
    }
}
