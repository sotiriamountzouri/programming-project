package thecode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.List;

public class AddDestinationListener implements ActionListener {
    private final CruiseInput cruiseInput;

    // Λίστα με τα έγκυρα λιμάνια
    private static final List<String> validPorts = Arrays.asList(
        "Άνδρος", "Γαύριο", "Μύκονος", "Παλιό Λιμάνι", "Νάξος", "Χώρα",
        "Πάρος", "Παροικιά", "Σύρος", "Ερμούπολη", "Τήνος", "Χώρα",
        "Σαντορίνη", "Αθηνιός", "Κως", "Χώρα", "Κάλυμνος", "Πόθια",
        "Λέρος", "Λακκί", "Πάτμος", "Σκάλα", "Χίος", "Χώρα", "Λέσβος",
        "Μυτιλήνη", "Αμοργός", "Κατάπολα", "Αμοργός", "Αιγιάλη", "Ίος",
        "Γυαλός", "Κίμωλος", "Ψάθη", "Μήλος", "Αδάμαντας", "Σέριφος",
        "Λιβάδι", "Σίφνος", "Καμάρες", "Φολέγανδρος", "Καραβοστάσης",
        "Δονούσα", "Δονούσα", "Αστυπάλαια", "Αστυπάλαια", "Καστελλόριζο",
        "Μεγίστη", "Νίσυρος", "Μανδράκι", "Τήλος", "Λιβάδια", "Χάλκη",
        "Νημποριό", "Λειψοί", "Λειψοί", "Αλόννησος", "Πατητήρι",
        "Σαμοθράκη", "Καμαριώτισσα", "Ψαρά", "Ψαρά", "Άγιος Ευστράτιος",
        "Άγιος Ευστράτιος", "Ρόδος", "Ρόδος", "Σάμος", "Βαθύ", "Σκιάθος",
        "Σκιάθος", "Σκόπελος", "Σκόπελος", "Λήμνος", "Μύρινα", "Ικαρία",
        "Άγιος Κήρυκος", "Κάρπαθος", "Πηγάδια", "Κέρκυρα", "Κέρκυρα",
        "Παξοί", "Γάιος", "Λευκάδα", "Λευκάδα", "Κεφαλονιά", "Αργοστόλι",
        "Ιθάκη", "Βαθύ", "Ζάκυνθος", "Χώρα", "Μεγανήσι", "Βαθύ",
        "Κύθηρα", "Διακόφτι", "Κρήτη", "Ηράκλειο", "Κρήτη", "Χανιά (Σούδα)",
        "Κρήτη", "Ρέθυμνο", "Κρήτη", "Άγιος Νικόλαος", "Κρήτη",
        "Σητεία", "Κρήτη", "Καστέλι Κισσσάμου", "Γύθειο", "Γύθειο",
        "Καλαμάτα", "Καλαμάτα", "Πάτρα", "Πάτρα", "Ηγουμενίτσα",
        "Ηγουμενίτσα", "Ναύπλιο", "Ναύπλιο", "Θεσσαλονίκη", "Θεσσαλονίκη",
        "Καβάλα", "Καβάλα", "Αλεξανδρούπολη", "Αλεξανδρούπολη", "Πειραιάς",
        "Πειραιάς", "Ραφήνα", "Ραφήνα", "Λαύριο", "Λαύριο"
    );

    public AddDestinationListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String destination = cruiseInput.getDestinationField().getText().trim();
        
        // Έλεγχος αν το πεδίο είναι κενό
        if (destination.isEmpty()) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(),
                "Το πεδίο προορισμού είναι κενό!", "Σφάλμα",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Μετατροπή του προορισμού σε πεζά γράμματα για έλεγχο
        destination = destination.toLowerCase();

        // Έλεγχος αν το λιμάνι είναι έγκυρο
        if (!isValidPort(destination)) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(),
                "Το λιμάνι \"" + destination + "\" δεν είναι έγκυρο.",
                "Σφάλμα", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Έλεγχος αν το λιμάνι υπάρχει ήδη στη λίστα
        if (cruiseInput.getDestinationListModel().contains(destination)) {
            JOptionPane.showMessageDialog(cruiseInput.getFrame(),
                "Ο προορισμός \"" + destination + "\" υπάρχει ήδη στη λίστα.",
                "Σφάλμα", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Προσθήκη του προορισμού στη λίστα
        cruiseInput.getDestinationListModel().addElement(destination);
        cruiseInput.getDestinationField().setText("");
    }

    // Μέθοδος για έλεγχο αν το λιμάνι είναι έγκυρο
    private boolean isValidPort(String port) {
        // Μετατροπή του εισερχόμενου port σε πεζά και έλεγχος με τη λίστα
        String lowerCasePort = port.toLowerCase(); // Μετατροπή του port σε πεζά
        return validPorts.stream()
                .map(String::toLowerCase) // μετατρέπουμε τα λιμάνια της λίστας σε πεζά
                .anyMatch(validPort -> validPort.equals(lowerCasePort));
    }
    
}
