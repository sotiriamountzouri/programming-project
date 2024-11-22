import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;

class Πόλη {
    String name;
    double latitude;
    double longitude;

    public Πόλη(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class Διαδρομή {
    List<Πόλη> cities;
    double distance;

    public Διαδρομή(List<Πόλη> cities) {
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

    private double haversine(Πόλη city1, Πόλη city2) {
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
    private List<Πόλη> cities;
    private int numberOfAnts;
    private double[][] pheromone;
    private double[][] distance;
    private double pheromoneEvaporationRate;
    private double pheromoneDeposit;

    public AntColony(List<Πόλη> cities, int numberOfAnts, double pheromoneEvaporationRate, double pheromoneDeposit) {
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
                    distance[i][j] = new Διαδρομή(List.of(cities.get(i), cities.get(j))).distance;
                } else {
                    distance[i][j] = 0;
                }
            }
        }
    }

    public Διαδρομή optimize() {
        Διαδρομή bestRoute = null;
        for (int iteration = 0; iteration < 100; iteration++) {
            List<Διαδρομή> antRoutes = new ArrayList<>();
            for (int ant = 0; ant < numberOfAnts; ant++) {
                Διαδρομή route = constructRoute();
                antRoutes.add(route);
                if (bestRoute == null || route.distance < bestRoute.distance) {
                    bestRoute = route;
                }
            }
            updatePheromone(antRoutes);
        }
        return bestRoute;
    }

    private Διαδρομή constructRoute() {
        List<Πόλη> visited = new ArrayList<>();
        Random rand = new Random();
        Πόλη startCity = cities.get(rand.nextInt(cities.size()));
        visited.add(startCity);
        while (visited.size() < cities.size()) {
            Πόλη nextCity = selectNextCity(visited);
            visited.add(nextCity);
        }
        // Return to start city to complete the route
        visited.add(startCity);
        return new Διαδρομή(visited);
    }

    private Πόλη selectNextCity(List<Πόλη> visited) {
        Random rand = new Random();
        Πόλη nextCity;
        do {
            nextCity = cities.get(rand.nextInt(cities.size()));
        } while (visited.contains(nextCity));
        return nextCity;
    }

    private void updatePheromone(List<Διαδρομή> antRoutes) {
        // Evaporate pheromone
        for (int i = 0; i < pheromone.length; i++) {
            for (int j = 0; j < pheromone[i].length; j++) {
                pheromone[i][j] *= (1 - pheromoneEvaporationRate);
            }
        }
        // Deposit pheromone based on routes
        for (Διαδρομή route : antRoutes) {
            for (int i = 0; i < route.cities.size() - 1; i++) {
                int fromIndex = cities.indexOf(route.cities.get(i));
                int toIndex = cities.indexOf(route.cities.get(i + 1));
                pheromone[fromIndex][toIndex] += pheromoneDeposit / route.distance;
            }
        }
    }

    public double getWindSpeed(double latitude, double longitude) {
        // Call the weather API to get wind speed
        String apiKey = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key
        String urlString = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonResponse = new JSONObject(response.toString());
            double windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed"); // Wind speed in m/s
            return windSpeed;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0; // Return 0 if there's an error
        }
    }
}

public class ShipRouteOptimizer {
    public static void main(String[] args) {
        List<Πόλη> cities = new ArrayList<>();
        cities.add(new Πόλη("Λιμάνι Α", 34.0522, -118.2437)); // Starting Point
        cities.add(new Πόλη("Λιμάνι Β", 36.1699, -115.1398)); // Destination 1
        cities.add(new Πόλη("Λιμάνι Γ", 40.7128, -74.0060));  // Destination 2
        cities.add(new Πόλη("Λιμάνι Δ", 25.7617, -80.1918));  // Destination 3

        AntColony antColony = new AntColony(cities, 10, 0.1, 100);
        Διαδρομή optimalRoute = antColony.optimize();

        System.out.println("Βέλτιστη Διαδρομή: ");
        for (Πόλη city : optimalRoute.cities) {
            System.out.println(city.name);
            double windSpeed = antColony.getWindSpeed(city.latitude, city.longitude);
            System.out.println("Wind Speed at " + city.name + ": " + windSpeed + " m/s");
        }
        System.out.println("Συνολική Απόσταση: " + optimalRoute.distance + " χλμ");
    }
}
