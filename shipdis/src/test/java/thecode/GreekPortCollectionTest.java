package thecode;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class GreekPortCollectionTest {

    private GreekPortCollection greekPortCollection;

    @Before
    public void setUp() {
        // Δημιουργία του αντικειμένου GreekPortCollection
        greekPortCollection = new GreekPortCollection();
    }

    @Test
    public void testInitializePorts() {
        // Ελέγχουμε αν η λίστα των ports δεν είναι κενή μετά την αρχικοποίηση
        List<Port> ports = greekPortCollection.getPorts();
        assertNotNull("The ports list should not be null", ports);
        assertFalse("The ports list should not be empty", ports.isEmpty());
    }

    @Test
    public void testPortDataIntegrity() {
        // Ελέγχουμε ότι τα δεδομένα που φορτώθηκαν είναι σωστά
        List<Port> ports = greekPortCollection.getPorts();
    
        // Ελέγχουμε ότι η λίστα περιέχει τουλάχιστον 5 λιμάνια (από το αρχείο δεδομένων)
        assertTrue("The ports list should have at least 5 ports", ports.size() >= 5);
    
        // Ελέγχουμε ένα συγκεκριμένο λιμάνι, π.χ., το πρώτο στη λίστα
        Port firstPort = ports.get(0); // Υποθέτουμε ότι το αρχείο περιέχει δεδομένα
        assertEquals("Άνδρος", firstPort.getIsland());
            
        // Ελέγχουμε τις συντεταγμένες και τη μορφή τους
        String expectedCoordinates = "Γαύριο (Άνδρος): 37.88° N, 24.73° E";  // 2 δεκαδικά ψηφία
        assertEquals("The coordinates format should match", expectedCoordinates, firstPort.getCoordinates());
        
        assertEquals(37.8800, firstPort.getLatitude(), 0.0001);
        assertEquals(24.7300, firstPort.getLongitude(), 0.0001);
    }

    @Test
    public void testAllPortsLoaded() {
        // Ελέγχουμε ότι έχουν φορτωθεί όλα τα λιμάνια από το αρχείο
        List<Port> ports = greekPortCollection.getPorts();

        // Από το αρχείο, υπάρχουν τουλάχιστον 50 εγγραφές
        assertTrue("The ports list should have at least 50 ports", ports.size() >= 50);
    }

    @Test
    public void testPrintAllPortCoordinates() {
        // Ελέγχουμε ότι η μέθοδος εκτυπώνει τις συντεταγμένες χωρίς σφάλματα
        try {
            greekPortCollection.printAllPortCoordinates();
        } catch (Exception e) {
            fail("Printing coordinates should not throw any exceptions");
        }
    }

    @Test
    public void testPortCoordinatesFormat() {
        // Δημιουργία δείγματος Port
        Port samplePort = new Port("Άνδρος", "Γαύριο", 37.8800, 24.7300);

        // Αναμενόμενη συμβολοσειρά με 2 δεκαδικά ψηφία
        String expectedCoordinates = "Γαύριο (Άνδρος): 37.88° N, 24.73° E";

        // Έλεγχος ότι η μέθοδος getCoordinates επιστρέφει τη σωστή μορφή
        assertEquals("Coordinates format should match", expectedCoordinates, samplePort.getCoordinates());
    }
}
