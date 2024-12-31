package thecode;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AddDestinationListenerTest {

    private CruiseInput cruiseInput;
    private AddDestinationListener listener;
    private JTextField destinationField;
    private DefaultListModel<String> destinationListModel;
    private JFrame frame;

    @Before
    public void setUp() {
        // Δημιουργία πραγματικών αντικειμένων
        destinationField = new JTextField();
        destinationListModel = new DefaultListModel<>();
        frame = new JFrame();

        // Δημιουργία CruiseInput με απλοποιημένη υλοποίηση
        cruiseInput = new CruiseInput() {
            @Override
            public JTextField getDestinationField() {
                return destinationField;
            }

            @Override
            public DefaultListModel<String> getDestinationListModel() {
                return destinationListModel;
            }

            @Override
            public JFrame getFrame() {
                return frame;
            }

            @Override
            public boolean isValidPort(String port) {
                // Για τις δοκιμές, θα θεωρήσουμε ότι μόνο το "Athens" είναι έγκυρο
                return "Athens".equalsIgnoreCase(port);
            }
        };

        listener = new AddDestinationListener(cruiseInput);
    }

    @Test
    public void testActionPerformed_EmptyField_ShowsError() {
        // Δοκιμή για κενό πεδίο
        destinationField.setText("");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        // Επειδή το JOptionPane δεν είναι εύκολο να ελεγχθεί, δεν κάνουμε verify εδώ.
        // Αρκεί που η λίστα παραμένει κενή.
        assertTrue(destinationListModel.isEmpty());
    }

    @Test
    public void testActionPerformed_InvalidPort_ShowsError() {
        // Δοκιμή για μη έγκυρο λιμάνι
        destinationField.setText("UnknownPort");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        assertTrue(destinationListModel.isEmpty());
    }

    @Test
    public void testActionPerformed_DuplicateDestination_ShowsWarning() {
        // Προσθήκη ενός προορισμού
        destinationListModel.addElement("Athens");

        // Προσπάθεια προσθήκης του ίδιου προορισμού
        destinationField.setText("Athens");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        // Η λίστα παραμένει με έναν μόνο προορισμό
        assertEquals(1, destinationListModel.size());
    }

    @Test
    public void testActionPerformed_ValidDestination_AddsToList() {
        // Δοκιμή για έγκυρο προορισμό
        destinationField.setText("Athens");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        // Εξασφάλιση ότι ο προορισμός προστέθηκε
        assertEquals(1, destinationListModel.size());
        assertEquals("Athens", destinationListModel.get(0));
    }
}
