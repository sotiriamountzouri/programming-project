package thecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Optimization.
 */
public class OptimizationTest {

    private Optimization optimization;
    private List<Port> ports;

    @Before
    public void setUp() {
        ports = new ArrayList<>();
        ports.add(new Port("Island1", "Port1", 0.0, 0.0));
        ports.add(new Port("Island2", "Port2", 1.0, 1.0));
        ports.add(new Port("Island3", "Port3", 2.0, 2.0));
        optimization = new Optimization(ports);
    }

    @Test
    public void testFindOptimalRoute() {
        // Δημιουργία λίστας προορισμών
        List<String> destinations = Arrays.asList("Island2", "Island3");
        
        // Εκτέλεση της μεθόδου
        List<Port> route = optimization.findOptimalRoute("Island1", destinations);

        // Ελέγχουμε ότι η διαδρομή δεν είναι null
        assertNotNull("Route should not be null", route);

        // Ελέγχουμε ότι η διαδρομή ξεκινά από το σωστό νησί
        assertEquals("Route should start from Island1", "Island1", route.get(0).getIsland());

        // Ελέγχουμε ότι η διαδρομή περιλαμβάνει τα σωστά λιμάνια
        assertEquals("Route should include Island2", "Island2", route.get(1).getIsland());
        assertEquals("Route should include Island3", "Island3", route.get(2).getIsland());
    }

    @Test
    public void testFindOptimalRouteInvalidStartIsland() {
        // Δημιουργία λίστας προορισμών
        List<String> destinations = Arrays.asList("Island2", "Island3");

        try {
            // Κλήση της μεθόδου με άκυρο νησί εκκίνησης
            optimization.findOptimalRoute("InvalidIsland", destinations);
            fail("Expected IllegalArgumentException for invalid start island");
        } catch (IllegalArgumentException e) {
            // Έλεγχος αν το μήνυμα της εξαίρεσης είναι σωστό
            assertEquals("Starting port not found: InvalidIsland", e.getMessage());
        }
    }

    @Test
    public void testFindOptimalRouteInvalidDestinationIsland() {
        // Δημιουργία λίστας προορισμών με άκυρο προορισμό
        List<String> destinations = Arrays.asList("Island2", "InvalidIsland");

        try {
            // Κλήση της μεθόδου με άκυρο προορισμό
            optimization.findOptimalRoute("Island1", destinations);
            fail("Expected IllegalArgumentException for invalid destination island");
        } catch (IllegalArgumentException e) {
            // Έλεγχος αν το μήνυμα της εξαίρεσης είναι σωστό
            assertEquals("Destination port not found: InvalidIsland", e.getMessage());
        }
    }
}
