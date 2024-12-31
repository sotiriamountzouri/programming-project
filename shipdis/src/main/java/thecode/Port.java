package thecode; 

/**
 * Represents a port of an island with a specific name and location/coordinates.
 */

public class Port {
    private final String island;

    private final String portName;

    private final double latitude;

    private final double longitude;

    public Port(String island, String portName, 
        double latitude, double longitude) {
        this.island = island;
        this.portName = portName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

/**
 * Prints all coordinates in a user-friendly format.
 */    
    public String getCoordinates() {
        return portName + " (" + island + "): " 
            + latitude + "° N, " + longitude + "° E";
    }

    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    public String getIsland(){
        return island;
    }
}
