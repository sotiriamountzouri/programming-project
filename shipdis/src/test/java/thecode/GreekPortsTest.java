package thecode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PortTest {

    private Port port;

    @BeforeEach
    public void setUp() {
        // Initialize a Port object with valid data for testing
        port = new Port("Άνδρος", "Γαύριο", 37.8800, 24.7300);
    }

    @Test
    public void testConstructorInitialization() {
        // Test if the constructor correctly initializes the fields
        assertNotNull(port, "Port object should not be null");
        assertEquals("Άνδρος", port.getName(), "Port name should be Άνδρος");
        assertEquals("Γαύριο", port.getHarbor(), "Port harbor should be Γαύριο");
        assertEquals(37.8800, port.getLatitude(), 0.0001, "Latitude should be 37.8800");
        assertEquals(24.7300, port.getLongitude(), 0.0001, "Longitude should be 24.7300");
    }

    @Test
    public void testGetCoordinates() {
        // Test if the getCoordinates() method returns the correct formatted coordinates
        String coordinates = port.getCoordinates();
        assertEquals("37.8800, 24.7300", coordinates, "Coordinates should be '37.8800, 24.7300'");
    }

    @Test
    public void testLatitudeLongitudeEdgeCases() {
        // Test edge cases for latitude and longitude

        // Test with extreme valid latitude (positive)
        Port extremeLatitudePort = new Port("Extreme North", "Harbor", 90.0000, 0.0000);
        assertEquals(90.0000, extremeLatitudePort.getLatitude(), "Latitude should be 90.0000");

        // Test with extreme valid latitude (negative)
        Port extremeSouthPort = new Port("Extreme South", "Harbor", -90.0000, 0.0000);
        assertEquals(-90.0000, extremeSouthPort.getLatitude(), "Latitude should be -90.0000");

        // Test with extreme valid longitude (positive)
        Port extremeLongitudePort = new Port("Extreme East", "Harbor", 0.0000, 180.0000);
        assertEquals(180.0000, extremeLongitudePort.getLongitude(), "Longitude should be 180.0000");

        // Test with extreme valid longitude (negative)
        Port extremeWestPort = new Port("Extreme West", "Harbor", 0.0000, -180.0000);
        assertEquals(-180.0000, extremeWestPort.getLongitude(), "Longitude should be -180.0000");
    }

    @Test
    public void testInvalidCoordinates() {
        // Test invalid coordinates (values out of range)
        
        // Latitude out of range (too high)
        Port invalidLatitudePort = new Port("Invalid Latitude", "Harbor", 91.0000, 24.7300);
        assertTrue(invalidLatitudePort.getLatitude() > 90.0, "Latitude should be out of valid range (-90 to 90)");

        // Latitude out of range (too low)
        Port invalidLatitudePort2 = new Port("Invalid Latitude", "Harbor", -91.0000, 24.7300);
        assertTrue(invalidLatitudePort2.getLatitude() < -90.0, "Latitude should be out of valid range (-90 to 90)");

        // Longitude out of range (too high)
        Port invalidLongitudePort = new Port("Invalid Longitude", "Harbor", 37.8800, 181.0000);
        assertTrue(invalidLongitudePort.getLongitude() > 180.0, "Longitude should be out of valid range (-180 to 180)");

        // Longitude out of range (too low)
        Port invalidLongitudePort2 = new Port("Invalid Longitude", "Harbor", 37.8800, -181.0000);
        assertTrue(invalidLongitudePort2.getLongitude() < -180.0, "Longitude should be out of valid range (-180 to 180)");
    }

    @Test
    public void testGetName() {
        // Test the getter for the name of the port
        assertEquals("Άνδρος", port.getName(), "Port name should be Άνδρος");
    }

    @Test
    public void testGetHarbor() {
        // Test the getter for the harbor of the port
        assertEquals("Γαύριο", port.getHarbor(), "Port harbor should be Γαύριο");
    }

    @Test
    public void testGetLatitude() {
        // Test the getter for the latitude of the port
        assertEquals(37.8800, port.getLatitude(), 0.0001, "Latitude should be 37.8800");
    }

    @Test
    public void testGetLongitude() {
        // Test the getter for the longitude of the port
        assertEquals(24.7300, port.getLongitude(), 0.0001, "Longitude should be 24.7300");
    }
}
