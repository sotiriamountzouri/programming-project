package thecode;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ClearListListenerTest {

    private CruiseInput cruiseInput;
    private DefaultListModel<String> destinationListModel;
    private ClearListListener listener;

    @Before
    public void setUp() {
        // Δημιουργία πραγματικού DefaultListModel
        destinationListModel = new DefaultListModel<>();

        // Δημιουργία mock αντικειμένων με απλό τρόπο
        cruiseInput = new CruiseInput() {
            private final JTextField destinationField = new JTextField();
            @Override
            public DefaultListModel<String> getDestinationListModel() {
                return destinationListModel;
            }
            @Override
            public JTextField getDestinationField() {
                return destinationField;
            }
        };

        // Δημιουργία του listener
        listener = new ClearListListener(cruiseInput);
    }
    @Test
    public void testActionPerformed_ClearsList() {
        // Προσθήκη δεδομένων στο μοντέλο
        destinationListModel.addElement("Athens");
        destinationListModel.addElement("Santorini");
        assertEquals(2, destinationListModel.size()); // Εξασφάλιση ότι το μοντέλο έχει δεδομένα

        // Δημιουργία ActionEvent και κλήση του listener
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clear");
        listener.actionPerformed(event);

        // Έλεγχος ότι η λίστα έχει καθαριστεί
        assertEquals(0, destinationListModel.size());
    }
}
