package thecode;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PortTest {

    @Test
    public void testGetCoordinates() {
        Port port = new Port("Crete", "Heraklion", 35.3387, 25.1442);
        String expectedCoordinates = "Heraklion (Crete): 35.3387° N, 25.1442° E";
        assertEquals(expectedCoordinates, port.getCoordinates());
    }

    @Test
    public void testGetLatitude() {
        Port port = new Port("Crete", "Heraklion", 35.3387, 25.1442);
        assertEquals(35.3387, port.getLatitude(), 0.0001);
    }

    @Test
    public void testGetLongitude() {
        Port port = new Port("Crete", "Heraklion", 35.3387, 25.1442);
        assertEquals(25.1442, port.getLongitude(), 0.0001);
    }

    @Test
    public void testGetIsland() {
        Port port = new Port("Crete", "Heraklion", 35.3387, 25.1442);
        assertEquals("Crete", port.getIsland());
    }
}
