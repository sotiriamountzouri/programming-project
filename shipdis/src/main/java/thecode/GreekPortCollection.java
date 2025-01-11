
package thecode;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GreekPortCollection {
    public final List<Port> ports;

    public GreekPortCollection() {
        ports = new ArrayList<>();
        initializePorts();
    }

    private void initializePorts() {
    try {
        // Διαβάζουμε το αρχείο από το φάκελο resources χρησιμοποιώντας InputStream
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("InitializeGreekPorts.txt");
        if (inputStream == null) {
            throw new FileNotFoundException("File 'InitializeGreekPorts.txt' not found in resources.");
        }

        // Χρησιμοποιούμε BufferedReader για ανάγνωση του αρχείου
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace(";", "");
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    ports.add(new Port(parts[0], parts[1], 
                        Double.parseDouble(parts[2]), 
                        Double.parseDouble(parts[3])));
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error while reading InitializeGreekPorts file: " + e.getMessage());
    }
}

    

    public void printAllPortCoordinates() {
        for (Port port : ports) {
            System.out.println(port.getCoordinates());
        }
    }

    public static void main(String[] args) {
        GreekPortCollection greekPorts = new GreekPortCollection();
        greekPorts.printAllPortCoordinates();
    }

/**
 * Gets back the list of ports.
 */    
    public List<Port> getPorts() {
        return ports;
    }

}
