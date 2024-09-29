package task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmenityTest {

    @Test
    public void testAmenityCreation() {
        Amenity amenity = new Amenity("WiFi", 10, 1);
        assertEquals("WiFi", amenity.getName());
        assertEquals(10, amenity.getCost());
        assertEquals(1, amenity.getGuestIncrease());
    }
}

