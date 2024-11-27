package thecode;

import java.util.ArrayList;
import java.util.List;

public class Optimization {
    private final List<Port> ports; 

    public Optimization(List<Port> ports) {
        this.ports = ports;
    }

    private double calculateDistance(Port p1, Port p2) {
        double latDiff = p1.getLatitude() - p2.getLatitude();
        double lonDiff = p1.getLongitude() - p2.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }

    public List<Port> findOptimalRoute(String startIsland, List<String> destinations) {
        Port currentPort = findPortByIsland(startIsland);
        if (currentPort == null) {
            throw new IllegalArgumentException("Δεν βρέθηκε το λιμάνι εκκίνησης: " + startIsland);
        }
    
        List<Port> destinationPorts = new ArrayList<>();
        for (String island : destinations) {
            Port port = findPortByIsland(island);
            if (port != null) {
                destinationPorts.add(port);
            } else {
                throw new IllegalArgumentException("Δεν βρέθηκε το λιμάνι: " + island);
            }
        }
    
        List<Port> route = new ArrayList<>();
        route.add(currentPort);
    
        while (!destinationPorts.isEmpty()) {
            Port nearestPort = null;
            double minDistance = Double.MAX_VALUE;
    
            for (Port port : destinationPorts) {
                double distance = calculateDistance(currentPort, port);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPort = port;
                }
            }
    
            route.add(nearestPort);
            destinationPorts.remove(nearestPort);
            currentPort = nearestPort;
        }
    
        return route;  // Επιστρέφουμε τη λίστα με τα λιμάνια στη διαδρομή
    }
    
    private Port findPortByIsland(String island) {
        for (Port port : ports) {
            if (port.getIsland().equalsIgnoreCase(island)) {
                return port;
            }
        }
        return null;
    }
}
