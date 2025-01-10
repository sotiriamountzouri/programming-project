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

    /**
     * Constructor for SubmitDataListener.
     * 
     * @param cruiseInput the CruiseInput object containing user input fields and data.
     */
    public SubmitDataListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!validateStartField()) return;
        List<String> destinations = validateDestinations();
        if (destinations == null) return;
        calculateOptimalRoute(destinations);
    }

    /**
     * Validates the start field input.
     * 
     * @return true if the start field is valid, false otherwise.
     */
    private boolean validateStartField() {
        return validateField(cruiseInput.getStartField().getText().trim(), "Το πεδίο αφετηρίας είναι κενό!", "Η αφετηρία \"%s\" δεν είναι έγκυρη.");
    }

    /**
     * Validates the destination list input.
     * 
     * @return the list of valid destinations or null if validation fails.
     */
    private List<String> validateDestinations() {
        List<String> destinations = new ArrayList<>();
        for (int i = 0; i < cruiseInput.getDestinationListModel().size(); i++) {
            String destination = cruiseInput.getDestinationListModel().getElementAt(i);
            if (!cruiseInput.isValidPort(destination)) {
                showErrorDialog("Ο προορισμός \"" + destination + "\" δεν είναι έγκυρος.");
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

    /**
     * Validates a single text field and shows an error if it's invalid.
     * 
     * @param text The text to validate.
     * @param emptyMessage The message to show if the text is empty.
     * @param invalidMessageFormat The message format to show if the port is invalid.
     * @return true if the text is valid, false otherwise.
     */
    private boolean validateField(String text, String emptyMessage, String invalidMessageFormat) {
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

    /**
     * Calculates the optimal route and updates the result area.
     * 
     * @param destinations the list of valid destination ports.
     */
    private void calculateOptimalRoute(List<String> destinations) {
        Optimization optimization = new Optimization(cruiseInput.getGreekPorts().getPorts());
        try {
            List<Port> optimalRoute = optimization.findOptimalRoute(
                cruiseInput.getStartField().getText().trim(), destinations);
            String result = "Βέλτιστη Διαδρομή:\n" + String.join(" -> ", optimalRoute.stream().map(Port::getIsland).toArray(String[]::new));
            cruiseInput.getResultArea().setText(result);
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Η βέλτιστη διαδρομή υπολογίστηκε!", "Επιτυχία", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            handleCalculationError(ex);
        }
    }

    /**
     * Handles any errors during the calculation of the optimal route.
     * 
     * @param ex The exception that occurred.
     */
    private void handleCalculationError(IllegalArgumentException ex) {
        showErrorDialog("Σφάλμα: " + ex.getMessage());
        cruiseInput.getResultArea().setText("Σφάλμα: " + ex.getMessage());
    }

    /**
     * Displays an error dialog with the given message.
     * 
     * @param message the error message to display.
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(cruiseInput.getFrame(), message, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }
}
