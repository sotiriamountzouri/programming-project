import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class City {
    String name;
    double latitude;
    double longitude;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class Route {
    List<City> cities;
    double distance;

    public Route(List<City> cities) {
        this.cities = new ArrayList<>(cities);
        this.distance = calculateDistance();
    }

    private double calculateDistance() {
        double totalDistance = 0.0;
        for (int i = 0; i < cities.size() - 1; i++) {
            totalDistance += haversine(cities.get(i), cities.get(i + 1));
        }
        return totalDistance;
    }

    private double haversine(City city1, City city2) {
        final int R = 6371; // Radius of the Earth in km
        double latDistance = Math.toRadians(city2.latitude - city1.latitude);
        double lonDistance = Math.toRadians(city2.longitude - city1.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(Math.toRadians(city1.latitude)) * Math.cos(Math.toRadians(city2.latitude)) *
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }
}

class AntColony {
    private List<City> cities;
    private int numberOfAnts;
    private double[][] pheromone;
    private double[][] distance;
    private double pheromoneEvaporationRate;
    private double pheromoneDeposit;

    public AntColony(List<City> cities, int numberOfAnts, double pheromoneEvaporationRate, double pheromoneDeposit) {
        this.cities = cities;
        this.numberOfAnts = numberOfAnts;
        this.pheromoneEvaporationRate = pheromoneEvaporationRate;
        this.pheromoneDeposit = pheromoneDeposit;
        this.pheromone = new double[cities.size()][cities.size()];
        this.distance = new double[cities.size()][cities.size()];
        initializeDistanceMatrix();
    }

    private void initializeDistanceMatrix() {
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < cities.size(); j++) {
                if (i != j) {
                    distance[i][j] = new Route(List.of(cities.get(i), cities.get(j))).distance;
                } else {
                    distance[i][j] = 0;
                }
            }
        }
    }

    public Route optimize() {
        Route bestRoute = null;
        for (int iteration = 0; iteration < 100; iteration++) {
            List<Route> antRoutes = new ArrayList<>();
            for (int ant = 0; ant < numberOfAnts; ant++) {
                Route route = constructRoute();
                antRoutes.add(route);
                if (bestRoute == null || route.distance < bestRoute.distance) {
                    bestRoute = route;
                }
            }
            updatePheromone(antRoutes);
        }
        return bestRoute;
    }

    private Route constructRoute() {
        List<City> visited = new ArrayList<>();
        Random rand = new Random();
        City startCity = cities.get(rand.nextInt(cities.size()));
        visited.add(startCity);
        while (visited.size() < cities.size()) {
            City nextCity = selectNextCity(visited);
            visited.add(nextCity);
        }
        // Return to start city to complete the route
        visited.add(startCity);
        return new Route(visited);
    }

    private City selectNextCity(List<City> visited) {
        // Implement logic to select the next city based on pheromone levels and distance
        Random rand = new Random();
        City nextCity;
        do {
            nextCity = cities.get(rand.nextInt(cities.size()));
        } while (visited.contains(nextCity));
        return nextCity;
    }

    private void updatePheromone(List<Route> antRoutes) {
        // Evaporate pheromone
        for (int i = 0; i < pheromone.length; i++) {
            for (int j = 0; j < pheromone[i].length; j++) {
                pheromone[i][j] *= (1 - pheromoneEvaporationRate);
            }
        }
        // Deposit pheromone based on routes
        for (Route route : antRoutes) {
            for (int i = 0; i < route.cities.size() - 1; i++) {
                int fromIndex = cities.indexOf(route.cities.get(i));
                int toIndex = cities.indexOf(route.cities.get(i + 1));
                pheromone[fromIndex][toIndex] += pheromoneDeposit / route.distance;
            }
        }
    }
}

public class ShipRouteOptimizer {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Port A", 34.0522, -118.2437)); // Starting Point
        cities.add(new City("Port B", 36.1699, -115.1398)); // Destination 1
        cities.add(new City("Port C", 40.7128, -74.0060));  // Destination 2
        cities.add(new City("Port D", 25.7617, -80.1918));  // Destination 3
        // Add more ports as needed

        AntColony antColony = new AntColony(cities, 10, 0.1, 100);
        Route optimalRoute = antColony.optimize();

        System.out.println("Optimal Route: ");
        for (City city : optimalRoute.cities) {
            System.out.println(city.name);
        }
        System.out.println("Total Distance: " + optimalRoute.distance + " km");
    }
}
