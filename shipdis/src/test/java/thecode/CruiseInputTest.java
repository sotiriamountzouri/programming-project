package thecode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CruiseInputTest {
    private CruiseInput cruiseInput;

    @Before
    public void setUp() {
        cruiseInput = new CruiseInput();
    }

    @Test
    public void testAddDestination() {
        // Εισαγωγή προορισμού
        cruiseInput.getDestinationField().setText("Santorini");
        cruiseInput.getDestinationListModel().addElement(cruiseInput.getDestinationField().getText());

        // Επαλήθευση ότι ο προορισμός προστέθηκε στη λίστα
        assertEquals(1, cruiseInput.getDestinationListModel().getSize());
        assertEquals("Santorini", cruiseInput.getDestinationListModel().getElementAt(0));
    }

    @Test
    public void testClearDestinationList() {
        // Προσθήκη κάποιων προορισμών
        cruiseInput.getDestinationListModel().addElement("Mykonos");
        cruiseInput.getDestinationListModel().addElement("Santorini");

        // Εκκαθάριση λίστας
        cruiseInput.getDestinationListModel().clear();

        // Επαλήθευση ότι η λίστα είναι άδεια
        assertEquals(0, cruiseInput.getDestinationListModel().getSize());
    }

    @Test
    public void testIsValidPort_ValidPort() {
    // Έλεγχος για το λιμάνι "Πειραιάς"
    assertTrue("Πειραιάς should be a valid port", cruiseInput.isValidPort("Πειραιάς"));
    }


    @Test
    public void testIsValidPort_InvalidPort() {
        // Επαλήθευση εγκυρότητας ανύπαρκτου λιμανιού
        assertFalse(cruiseInput.isValidPort("Atlantis"));
    }

    @Test
    public void testSetAndGetStartField() {
        // Θέτουμε τιμή στο πεδίο αφετηρίας
        cruiseInput.getStartField().setText("Piraeus");

        // Επαλήθευση ότι η τιμή έχει αποθηκευτεί σωστά
        assertEquals("Piraeus", cruiseInput.getStartField().getText());
    }

    @Test
    public void testSetAndGetResultArea() {
        // Θέτουμε τιμή στο αποτέλεσμα
        cruiseInput.getResultArea().setText("Cruise route generated!");

        // Επαλήθευση ότι το αποτέλεσμα έχει αποθηκευτεί σωστά
        assertEquals("Cruise route generated!", cruiseInput.getResultArea().getText());
    }

    @Test
    public void testSubmitButtonAction() {
        // Εισαγωγή τιμών
        cruiseInput.getStartField().setText("Athens");
        cruiseInput.getDestinationListModel().addElement("Mykonos");

        // Επαλήθευση δεδομένων υποβολής
        assertEquals("Athens", cruiseInput.getStartField().getText());
        assertEquals(1, cruiseInput.getDestinationListModel().getSize());
        assertEquals("Mykonos", cruiseInput.getDestinationListModel().getElementAt(0));
    }
}
