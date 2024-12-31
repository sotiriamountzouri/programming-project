package thecode;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SubmitDataListenerTest {

    private SubmitDataListener listener;
    private CruiseInput cruiseInput;
    private JTextField startField;
    private DefaultListModel<String> destinationListModel;
    private JTextArea resultArea;
    private GreekPorts greekPorts;
    private JFrame frame;

    @Before
    public void setUp() {
        frame = new JFrame();
        startField = new JTextField();
        destinationListModel = new DefaultListModel<>();
        resultArea = new JTextArea();
        greekPorts = new GreekPorts();

        greekPorts.setPorts(Arrays.asList(
            new Port("Port1", "Island1", 0.0, 0.0),
            new Port("Port2", "Island2", 1.0, 1.0),
            new Port("Port3", "Island3", 2.0, 2.0)
        ));

        cruiseInput = new CruiseInput(frame, startField, destinationListModel, resultArea, greekPorts);
        listener = new SubmitDataListener(cruiseInput);
    }

    @Test
    public void testEmptyStartField() {
        startField.setText("");
        listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        assertEquals("", resultArea.getText());
    }

    @Test
    public void testValidInput() {
        startField.setText("Island1");
        destinationListModel.addElement("Island2");
        destinationListModel.addElement("Island3");

        listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        String expected = "Βέλτιστη Διαδρομή:\nIsland1 -> Island2 -> Island3";
        assertEquals(expected, resultArea.getText());
    }
}
