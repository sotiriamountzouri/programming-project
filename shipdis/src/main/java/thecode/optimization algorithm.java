import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

class Port {
    private String island;
    private String portName;
    private double latitude;
    private double longitude;

    public Port() {}

    public Port(String island, String portName, double latitude, double longitude) {
        this.island = island;
        this.portName = portName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIsland() {
        return island;
    }

    public String getPortName() {
        return portName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCoordinates() {
        return portName + " (" + island + "): " + latitude + "° N, " + longitude + "° E";
    }
}

public class GreekPorts {
    private List<Port> ports;
    private Map<String, Port> portLookup;

    public GreekPorts() {
        ports = new ArrayList<>();
        portLookup = new HashMap<>();
        initializePorts();
    }

    private void initializePorts() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ports = objectMapper.readValue(
                new File("ports.json"), // Path to your JSON file
                new TypeReference<List<Port>>() {}
            );

            for (Port port : ports) {
                portLookup.put(port.getIsland().toLowerCase(), port);
            }

            System.out.println("Ports successfully loaded from file.");
        } catch (IOException e) {
            System.err.println("Error loading ports from file: " + e.getMessage());
        }
    }

    // Calculate distance between two points using Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Earth's radius in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    // Find the optimal visiting order using a simple TSP greedy approach
    public void findOptimalPath(List<String> islands) {
        // Retrieve the list of ports based on user input
        List<Port> selectedPorts = new ArrayList<>();
        for (String island : islands) {
            Port port = portLookup.get(island.toLowerCase());
            if (port == null) {
                System.out.println("Island '" + island + "' not found in the dataset.");
                return;
            }
            selectedPorts.add(port);
        }

        // Build a distance matrix
        int n = selectedPorts.size();
        double[][] distanceMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    distanceMatrix[i][j] = calculateDistance(
                        selectedPorts.get(i).getLatitude(), selectedPorts.get(i).getLongitude(),
                        selectedPorts.get(j).getLatitude(), selectedPorts.get(j).getLongitude()
                    );
                } else {
                    distanceMatrix[i][j] = Double.MAX_VALUE; // No self-loops
                }
            }
        }

        // Perform a greedy TSP starting from the first port
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int current = 0;
        visited[current] = true;
        order.add(current);

        double totalDistance = 0;

        for (int step = 1; step < n; step++) {
            int next = -1;
            double minDistance = Double.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && distanceMatrix[current][j] < minDistance) {
                    next = j;
                    minDistance = distanceMatrix[current][j];
                }
            }

            if (next != -1) {
                visited[next] = true;
                order.add(next);
                totalDistance += distanceMatrix[current][next];
                current = next;
            }
        }

        // Add distance to return to the starting point
        totalDistance += distanceMatrix[current][order.get(0)];

        // Print the visiting order and total distance
        System.out.println("\nOptimal Visiting Order:");
        for (int i : order) {
            System.out.println(selectedPorts.get(i).getIsland() + " (" + selectedPorts.get(i).getPortName() + ")");
        }
        System.out.printf("Total distance: %.2f km\n", totalDistance);
    }

    public static void main(String[] args) {
        GreekPorts greekPorts = new GreekPorts();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Greek Ports Menu ===");
            System.out.println("1. Show all available coordinates");
            System.out.println("2. Plan an optimal trip");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Ports:");
                    for (Port port : greekPorts.ports) {
                        System.out.println(port.getCoordinates());
                    }
                    break;

                case 2:
                    System.out.println("\nEnter the names of the islands you want to visit (comma-separated):");
                    String input = scanner.nextLine();
                    List<String> islands = Arrays.asList(input.split(",\\s*"));
                    greekPorts.findOptimalPath(islands);
                    break;

                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

