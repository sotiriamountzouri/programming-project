package thecode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Listener for handling the submission of cruise data.
 * Validates user input and calculates the optimal route.
 */
public class SubmitDataListener implements ActionListener {
    private final CruiseInput cruiseInput;

    // Constructor
    public SubmitDataListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Έλεγχος για έγκυρη αφετηρία
        if (!validateStartField()) return;
        
        // Έλεγχος για έγκυρους προορισμούς
        List<String> destinations = validateDestinations();
        if (destinations == null) return;

        // Υπολογισμός βέλτιστης διαδρομής
        calculateOptimalRoute(destinations);
    }

    private boolean validateStartField() {
        return validateField(cruiseInput.getStartField().getText().trim(), 
                             "Το πεδίο αφετηρίας είναι κενό!", 
                             "Η αφετηρία \"%s\" δεν είναι έγκυρη.");
    }

    private List<String> validateDestinations() {
        List<String> destinations = new ArrayList<>();
        for (int i = 0; i < cruiseInput.getDestinationListModel().size(); i++) {
            String destination = cruiseInput.
                getDestinationListModel().getElementAt(i);
            if (!cruiseInput.isValidPort(destination)) {
                showErrorDialog("Ο προορισμός \"" + 
                    destination + "\" δεν είναι έγκυρος.");
                return null;
            }
            destinations.add(destination);
        }
        if (destinations.isEmpty()) {
            showErrorDialog("Η λίστα προορισμών είναι κενή!");
            return null;
        }
        return destinations;
    }

    private boolean validateField(String text,
        String emptyMessage, String invalidMessageFormat) {
        if (text.isEmpty()) {
            showErrorDialog(emptyMessage);
            return false;
        }
        if (!cruiseInput.isValidPort(text)) {
            showErrorDialog(String.format(invalidMessageFormat, text));
            return false;
        }
        return true;
    }

    private void calculateOptimalRoute(List<String> destinations) {
        // Χρησιμοποιούμε τη GreekPortCollection για να πάρουμε τα λιμάνια
        Optimization optimization = new Optimization(
            cruiseInput.getGreekPorts().getPorts());
        try {
            // Υπολογισμός της βέλτιστης διαδρομής
            List<Port> optimalRoute = optimization.findOptimalRoute(
                cruiseInput.getStartField().getText().trim(), destinations);

            // Εμφάνιση αποτελέσματος
            String result = "Βέλτιστη Διαδρομή:\n" + String.join(
                " -> ", optimalRoute.stream().map(
                Port::getIsland).toArray(String[]::new));
            cruiseInput.getResultArea().setText(result);
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), 
                "Η βέλτιστη διαδρομή υπολογίστηκε!", 
                "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            handleCalculationError(ex);
        }
    }

    private void handleCalculationError(IllegalArgumentException ex) {
        showErrorDialog("Σφάλμα: " + ex.getMessage());
        cruiseInput.getResultArea().setText("Σφάλμα: " + ex.getMessage());
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            cruiseInput.getFrame(), 
            message, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }
}
