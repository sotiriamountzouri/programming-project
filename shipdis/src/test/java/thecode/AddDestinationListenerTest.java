package thecode;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AddDestinationListenerTest {

    @Test
    public void testActionPerformed_EmptyField_ShowsError() {
        // Δημιουργία των αντικειμένων μέσα στο τεστ
        JTextField destinationField = new JTextField();
        DefaultListModel<String> destinationListModel = new DefaultListModel<>();
        JFrame frame = new JFrame();

        // Δημιουργία CruiseInput με απλοποιημένη υλοποίηση
        CruiseInput cruiseInput = new CruiseInput() {
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
                return "Athens".equalsIgnoreCase(port);
            }
        };

        AddDestinationListener listener = new AddDestinationListener(cruiseInput);

        // Δοκιμή για κενό πεδίο
        destinationField.setText("");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        // Ελέγχουμε ότι η λίστα παραμένει κενή
        assertTrue(destinationListModel.isEmpty());
    }

    @Test
    public void testActionPerformed_InvalidPort_ShowsError() {
        // Δημιουργία των αντικειμένων μέσα στο τεστ
        JTextField destinationField = new JTextField();
        DefaultListModel<String> destinationListModel = new DefaultListModel<>();
        JFrame frame = new JFrame();

        // Δημιουργία CruiseInput με απλοποιημένη υλοποίηση
        CruiseInput cruiseInput = new CruiseInput() {
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
                return "Athens".equalsIgnoreCase(port);
            }
        };

        AddDestinationListener listener = new AddDestinationListener(cruiseInput);

        // Δοκιμή για μη έγκυρο λιμάνι
        destinationField.setText("UnknownPort");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        // Ελέγχουμε ότι η λίστα παραμένει κενή
        assertTrue(destinationListModel.isEmpty());
    }

    @Test
    public void testActionPerformed_DuplicateDestination_ShowsWarning() {
        // Δημιουργία των αντικειμένων μέσα στο τεστ
        JTextField destinationField = new JTextField();
        DefaultListModel<String> destinationListModel = new DefaultListModel<>();
        JFrame frame = new JFrame();

        // Δημιουργία CruiseInput με απλοποιημένη υλοποίηση
        CruiseInput cruiseInput = new CruiseInput() {
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
                return "Athens".equalsIgnoreCase(port);
            }
        };

        AddDestinationListener listener = new AddDestinationListener(cruiseInput);

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
        // Δημιουργία των αντικειμένων μέσα στο τεστ
        JTextField destinationField = new JTextField();
        DefaultListModel<String> destinationListModel = new DefaultListModel<>();
        JFrame frame = new JFrame();

        // Δημιουργία CruiseInput με απλοποιημένη υλοποίηση
        CruiseInput cruiseInput = new CruiseInput() {
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
                return "Athens".equalsIgnoreCase(port);
            }
        };

        AddDestinationListener listener = new AddDestinationListener(cruiseInput);

        // Δοκιμή για έγκυρο προορισμό
        destinationField.setText("Athens");

        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add");
        listener.actionPerformed(event);

        // Εξασφάλιση ότι ο προορισμός προστέθηκε
        assertEquals(1, destinationListModel.size());
        assertEquals("Athens", destinationListModel.get(0));
    }
}
