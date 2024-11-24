package thecode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SubmitDataListener implements ActionListener {
    private CruiseInput cruiseInput;

    public SubmitDataListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String start = cruiseInput.getStartField().getText().trim();
        if (start.isEmpty()) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Το πεδίο αφετηρίας είναι κενό!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!cruiseInput.isValidPort(start)) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Η αφετηρία \"" + start + "\" δεν είναι έγκυρη.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> destinations = new ArrayList<>();
        for (int i = 0; i < cruiseInput.getDestinationListModel().size(); i++) {
            destinations.add(cruiseInput.getDestinationListModel().getElementAt(i));
        }
        if (destinations.isEmpty()) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Η λίστα προορισμών είναι κενή!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Optimization optimization = new Optimization(cruiseInput.getGreekPorts().getPorts());
        try {
            List<String> optimalRoute = optimization.findOptimalRoute(start, destinations);
            StringBuilder result = new StringBuilder("Βέλτιστη Διαδρομή:\n");
            for (String island : optimalRoute) {
                result.append(island).append(" -> ");
            }
            result.delete(result.length() - 4, result.length());
            cruiseInput.getResultArea().setText(result.toString());
        } catch (IllegalArgumentException ex) {
            cruiseInput.getResultArea().setText("Σφάλμα: " + ex.getMessage());
        }
    }
}
