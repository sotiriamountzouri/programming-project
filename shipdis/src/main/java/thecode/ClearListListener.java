package thecode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ClearListListener implements ActionListener {
    private final CruiseInput cruiseInput;

    public ClearListListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Εμφανίζουμε ένα μήνυμα επιβεβαίωσης πριν καθαρίσουμε τη λίστα
        int confirm = JOptionPane.showConfirmDialog(
            cruiseInput.getFrame(), 
            // Εξασφαλίζουμε ότι το cruiseInput συνδέεται με το frame
            "Είστε σίγουροι ότι θέλετε να καθαρίσετε τη λίστα προορισμών;",
            "Επιβεβαίωση", 
            JOptionPane.YES_NO_OPTION
        );

        // Αν ο χρήστης επιλέξει "Ναι", καθαρίζουμε τη λίστα
        if (confirm == JOptionPane.YES_OPTION) {
            // Καθαρίζουμε τη λίστα προορισμών
            cruiseInput.getDestinationListModel().clear();
        }
    }
}
