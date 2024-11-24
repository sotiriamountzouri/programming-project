package thecode;

class Port {
    private String island;
    private String portName;
    private double latitude;
    private double longitude;

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
