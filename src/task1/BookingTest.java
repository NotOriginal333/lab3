package task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

public class BookingTest {

    @Test
    public void testOverlapsWithTrue() {
        Calendar start1 = getDate(1);
        Calendar end1 = getDate(5);
        Calendar start2 = getDate(4);
        Calendar end2 = getDate(8);

        Booking booking1 = new Booking("Client1", start1, end1, 1000);
        assertTrue(booking1.overlapsWith(start2, end2));
    }

    @Test
    public void testOverlapsWithFalse() {
        Calendar start1 = getDate(1);
        Calendar end1 = getDate(2);
        Calendar start2 = getDate(3);
        Calendar end2 = getDate(4);

        Booking booking1 = new Booking("Client1", start1, end1, 1000);
        assertFalse(booking1.overlapsWith(start2, end2));
    }

    private Calendar getDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, day);
        return calendar;
    }
}
