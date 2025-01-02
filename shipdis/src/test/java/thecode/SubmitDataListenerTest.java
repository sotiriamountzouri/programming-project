package thecode;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class SubmitDataListenerTest {

    private SubmitDataListener submitDataListener;
    private CruiseInput cruiseInput;
    private JFrame frame;
    private JTextField startField;
    private JTextArea resultArea;
    private DefaultListModel<String> destinationListModel;

    @Before
    public void setUp() {
        // Δημιουργία πραγματικών αντικειμένων
        frame = new JFrame();
        startField = new JTextField();
        resultArea = new JTextArea();
        destinationListModel = new DefaultListModel<>();

        // Δημιουργία instance του CruiseInput
        cruiseInput = new CruiseInput() {
            @Override
            public JFrame getFrame() {
                return frame;
            }

            @Override
            public JTextField getStartField() {
                return startField;
            }

            @Override
            public JTextArea getResultArea() {
                return resultArea;
            }

            @Override
            public DefaultListModel<String> getDestinationListModel() {
                return destinationListModel;
            }

            @Override
            public boolean isValidPort(String port) {
                // Ορισμός απλής λογικής για τη δοκιμή
                return Arrays.asList("Πειραιάς", "Σύρος", "Μύκονος").contains(port);
            }

            @Override
            public GreekPorts getGreekPorts() {
                // Επιστροφή mock αντικειμένου
                return new GreekPorts();
            }
        };

        submitDataListener = new SubmitDataListener(cruiseInput);
    }

    @Test
    public void testActionPerformed_EmptyStartField() {
        startField.setText(""); // Κενό πεδίο

        submitDataListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        assertTrue(resultArea.getText().isEmpty()); // Το αποτέλεσμα πρέπει να παραμένει κενό
    }

    @Test
    public void testActionPerformed_InvalidStartField() {
        startField.setText("Αθήνα"); // Μη έγκυρο λιμάνι

        submitDataListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        assertTrue(resultArea.getText().isEmpty()); // Το αποτέλεσμα πρέπει να παραμένει κενό
    }

    @Test
    public void testActionPerformed_ValidInput() {
        startField.setText("Πειραιάς");
        destinationListModel.addElement("Σύρος");
        destinationListModel.addElement("Μύκονος");

        submitDataListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        assertFalse(resultArea.getText().isEmpty()); // Το αποτέλεσμα πρέπει να έχει βέλτιστη διαδρομή
        assertTrue(resultArea.getText().contains("Πειραιάς -> Σύρος -> Μύκονος")); // Επιβεβαίωση διαδρομής
    }
}
