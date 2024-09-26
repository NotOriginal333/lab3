package task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Calendar;

public class BookingTest {

    @Test
    public void testOverlapsWith() {
        Date start1 = getDate(1);
        Date end1 = getDate(5);
        Date start2 = getDate(4);
        Date end2 = getDate(8);

        Booking booking1 = new Booking("Client1", start1, end1, 1000);
        assertTrue(booking1.overlapsWith(start2, end2));
    }

    private Date getDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, day);
        return calendar.getTime();
    }
}
