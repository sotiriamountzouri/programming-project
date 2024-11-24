package thecode;
import java.util.ArrayList;
import java.util.List;

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

public class GreekPorts {
    private List<Port> ports;

    public GreekPorts() {
        ports = new ArrayList<>();
        initializePorts();
    }

    private void initializePorts() {
        // Λιμάνια Αιγαίου Πελάγους
        ports.add(new Port("Άνδρος", "Γαύριο", 37.8800, 24.7300));
        ports.add(new Port("Μύκονος", "Παλιό Λιμάνι", 37.4500, 25.3300));
        ports.add(new Port("Νάξος", "Χώρα", 37.1000, 25.3700));
        ports.add(new Port("Πάρος", "Παροικιά", 37.0800, 25.1500));
        ports.add(new Port("Σύρος", "Ερμούπολη", 37.4500, 24.9500));
        ports.add(new Port("Τήνος", "Χώρα", 37.5400, 25.1600));
        ports.add(new Port("Σαντορίνη", "Αθηνιός", 36.3800, 25.4300));
        ports.add(new Port("Κως", "Χώρα", 36.8900, 27.2900));
        ports.add(new Port("Κάλυμνος", "Πόθια", 36.9500, 26.9900));
        ports.add(new Port("Λέρος", "Λακκί", 37.1300, 26.8500));
        ports.add(new Port("Πάτμος", "Σκάλα", 37.3100, 26.5400));
        ports.add(new Port("Χίος", "Χώρα", 38.3700, 26.1400));
        ports.add(new Port("Λέσβος", "Μυτιλήνη", 39.1100, 26.5500));

        // Λιμάνια Ιονίου Πελάγους
        ports.add(new Port("Κέρκυρα", "Κέρκυρα", 39.6200, 19.9200));
        ports.add(new Port("Παξοί", "Γάιος", 39.2000, 20.1800));
        ports.add(new Port("Λευκάδα", "Λευκάδα", 38.8300, 20.7100));
        ports.add(new Port("Κεφαλονιά", "Αργοστόλι", 38.1800, 20.4900));
        ports.add(new Port("Ιθάκη", "Βαθύ", 38.3700, 20.7200));
        ports.add(new Port("Ζάκυνθος", "Χώρα", 37.7800, 20.9000));

        // Λιμάνια Κρήτης
        ports.add(new Port("Κρήτη", "Ηράκλειο", 35.3400, 25.1300));
        ports.add(new Port("Κρήτη", "Χανιά (Σούδα)", 35.5100, 24.0200));
        ports.add(new Port("Κρήτη", "Ρέθυμνο", 35.3700, 24.4700));
        ports.add(new Port("Κρήτη", "Άγιος Νικόλαος", 35.1900, 25.7100));
        ports.add(new Port("Κρήτη", "Σητεία", 35.2000, 26.1000));

        // Άλλα λιμάνια
        ports.add(new Port("Ρόδος", "Ρόδος", 36.4500, 28.2300));
        ports.add(new Port("Σάμος", "Βαθύ", 37.7500, 26.9700));
        ports.add(new Port("Σκιάθος", "Σκιάθος", 39.1600, 23.4900));
        ports.add(new Port("Σκόπελος", "Σκόπελος", 39.1200, 23.7300));
        ports.add(new Port("Λήμνος", "Μύρινα", 39.8700, 25.0600));
        ports.add(new Port("Ικαρία", "Άγιος Κήρυκος", 37.6100, 26.2900));
        ports.add(new Port("Κάρπαθος", "Πηγάδια", 35.5100, 27.2100));

        // Κεντρικά λιμάνια
        ports.add(new Port("Πειραιάς", "Πειραιάς", 37.9400, 23.6500));
        ports.add(new Port("Ραφήνα", "Ραφήνα", 38.0200, 24.0100));
    }

    public void printAllCoordinates() {
        for (Port port : ports) {
            System.out.println(port.getCoordinates());
        }
    }

    public static void main(String[] args) {
        GreekPorts greekPorts = new GreekPorts();
        greekPorts.printAllCoordinates();
    }

   
     public List<Port> getPorts() {
     return ports;
     }

}

