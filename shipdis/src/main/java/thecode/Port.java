package thecode;

public class Port {
    private final String island;
    private final String portName;
    private final double latitude;
    private final double longitude;

    public Port(String island, String portName, double latitude, double longitude) {
        this.island = island;
        this.portName = portName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCoordinates() {
        return portName + " (" + island + "): " + latitude + "° N, " + longitude + "° E";
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
