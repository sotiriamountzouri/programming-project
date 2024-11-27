package thecode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AddDestinationListener implements ActionListener {
    private final CruiseInput cruiseInput;

    public AddDestinationListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String destination = cruiseInput.getDestinationField().getText().trim();
        if (destination.isEmpty()) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Το πεδίο προορισμού είναι κενό!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!cruiseInput.isValidPort(destination)) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Το λιμάνι \"" + destination + "\" δεν είναι έγκυρο.", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cruiseInput.getDestinationListModel().contains(destination)) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(), "Ο προορισμός \"" + destination + "\" υπάρχει ήδη στη λίστα.", "Σφάλμα", JOptionPane.WARNING_MESSAGE);
            return;
        }
        cruiseInput.getDestinationListModel().addElement(destination);
        cruiseInput.getDestinationField().setText("");
    }
}
