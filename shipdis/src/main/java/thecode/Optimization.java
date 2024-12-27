package thecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Optimization class for finding optimal routes between ports.
 */
public class Optimization {
    /**
     * List of available ports.
     */
    public final List<Port> ports;

    /**
     * Constructor to initialize the Optimization object with a list of ports.
     *
     * @param ports List of ports
     */
    public Optimization(List<Port> ports) {
        this.ports = ports;
    }

    /**
     * Calculates the distance between two ports.
     *
     * @param p1 First port
     * @param p2 Second port
     * @return Distance between the ports
     */
    private double calculateDistance(Port p1, Port p2) {
        double latDiff = p1.getLatitude() - p2.getLatitude();
        double lonDiff = p1.getLongitude() - p2.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }

    /**
     * Finds the nearest port to the current port from a list of destination ports.
     *
     * @param currentPort      Current port
     * @param destinationPorts List of destination ports
     * @return The nearest port
     */
    private Port findNearestPort(Port currentPort, List<Port> destinationPorts) {
        Port nearestPort = null;
        double minDistance = Double.MAX_VALUE;

        for (Port port : destinationPorts) {
            double distance = calculateDistance(currentPort, port);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPort = port;
            }
        }

        return nearestPort;
    }

    /**
     * Finds the optimal route starting from a given island to a list of destination islands.
     *
     * @param startIsland   The starting island
     * @param destinations  List of destination islands
     * @return A list of ports representing the optimal route
     * @throws IllegalArgumentException if a port is not found
     */
    public List<Port> findOptimalRoute(String startIsland, List<String> destinations) {
        Port currentPort = findPortByIsland(startIsland);
        if (currentPort == null) {
            throw new IllegalArgumentException("Starting port not found: " + startIsland);
        }

        List<Port> destinationPorts = new ArrayList<>();
        for (String island : destinations) {
            Port port = findPortByIsland(island);
            if (port != null) {
                destinationPorts.add(port);
            } else {
                throw new IllegalArgumentException("Destination port not found: " + island);
            }
        }

        List<Port> route = new ArrayList<>();
        route.add(currentPort);

        while (!destinationPorts.isEmpty()) {
            Port nearestPort = findNearestPort(currentPort, destinationPorts);
            route.add(nearestPort);
            destinationPorts.remove(nearestPort);
            currentPort = nearestPort;
        }

        return route;
    }

    /**
     * Finds a port by its associated island name.
     *
     * @param island The name of the island
     * @return The corresponding port or null if not found
     */
    private Port findPortByIsland(String island) {
        for (Port port : ports) {
            if (port.getIsland().equalsIgnoreCase(island)) {
                return port;
            }
        }
        return null;
    }
}
