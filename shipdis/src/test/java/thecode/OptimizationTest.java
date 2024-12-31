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
        ports.add(new Port("Port1", "Island1", 0.0, 0.0));
        ports.add(new Port("Port2", "Island2", 1.0, 1.0));
        ports.add(new Port("Port3", "Island3", 2.0, 2.0));
        optimization = new Optimization(ports);
    }

    @Test
    public void testFindOptimalRoute() {
        List<String> destinations = Arrays.asList("Island2", "Island3");
        List<Port> route = optimization.findOptimalRoute("Island1", destinations);

        assertNotNull("Route should not be null", route);
        assertEquals("Route should start from Island1", "Island1", route.get(0).getIsland());
        assertEquals("Route should include Island2", "Island2", route.get(1).getIsland());
        assertEquals("Route should include Island3", "Island3", route.get(2).getIsland());
    }

    @Test
    public void testFindOptimalRouteInvalidStartIsland() {
        List<String> destinations = Arrays.asList("Island2", "Island3");

        try {
            optimization.findOptimalRoute("InvalidIsland", destinations);
            fail("Expected IllegalArgumentException for invalid start island");
        } catch (IllegalArgumentException e) {
            assertEquals("Starting port not found: InvalidIsland", e.getMessage());
        }
    }

    @Test
    public void testFindOptimalRouteInvalidDestinationIsland() {
        List<String> destinations = Arrays.asList("Island2", "InvalidIsland");

        try {
            optimization.findOptimalRoute("Island1", destinations);
            fail("Expected IllegalArgumentException for invalid destination island");
        } catch (IllegalArgumentException e) {
            assertEquals("Destination port not found: InvalidIsland", e.getMessage());
        }
    }
}
