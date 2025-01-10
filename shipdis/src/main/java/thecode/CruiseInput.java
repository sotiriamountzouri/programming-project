//write Interface code here!

package thecode;

import java.awt.*;

import javax.swing.*;

public class CruiseInput {
    private JFrame frame;

    private JTextField startField; // Πεδίο για την αφετηρία

    private JTextField destinationField; // Πεδίο για νέο προορισμό

    private DefaultListModel<String> 
        destinationListModel; // Λίστα για τους προορισμούς

    private JList<String> destinationList; // Γραφική αναπαράσταση της λίστας

    private final GreekPorts greekPorts; // Για έλεγχο λιμανιών

    private JTextArea resultArea; // Πεδίο για εμφάνιση αποτελέσματος

    public CruiseInput() {
        greekPorts = new GreekPorts(); // Φόρτωση λιμανιών
        initializeUI();
    }

    private void initializeUI() {
        // Δημιουργία του βασικού πλαισίου
        frame = new JFrame("AquaRoute");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(102, 155, 188)); // Απαλό γαλάζιο
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        Font labelFont = new Font("Arial", Font.BOLD, 16); // Στην ίδια γραμματοσειρά για όλα
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        Color buttonColor = new Color(0, 48, 73); // Σκούρο μπλε #003049
        Color textColor = Color.WHITE;
        
        // Πάνω μέρος - Εισαγωγή αφετηρίας
        JPanel startPanel = new JPanel(new FlowLayout());
        startPanel.add(new JLabel("Αφετηρία:"));
        startField = new JTextField(20);
        startPanel.add(startField);
        frame.add(startPanel, BorderLayout.NORTH);
        frame.add(startPanel);          // Αφετηρία
        
          // Κάτω μέρος - Πεδίο για προσθήκη προορισμού και κουμπιά
        JPanel destinationPanel = new JPanel(new FlowLayout());
        destinationField = new JTextField(15);
        destinationPanel.add(new JLabel("Προσθήκη Προορισμού:"));
        destinationPanel.add(destinationField);
  
        JButton addButton = new JButton("Προσθήκη");
        addButton.addActionListener(new AddDestinationListener(this)); 
        // Χρησιμοποιούμε το this για να περάσουμε την αναφορά στην κύρια κλάση
        destinationPanel.add(addButton);
  
        JButton clearButton = new JButton("Καθαρισμός Λίστας");
        clearButton.addActionListener(new ClearListListener(this)); 
        // Χρησιμοποιούμε το this για να περάσουμε την αναφορά στην κύρια κλάση
        destinationPanel.add(clearButton);
  
        frame.add(destinationPanel, BorderLayout.CENTER);

        // Κέντρο - Λίστα προορισμών
        destinationListModel = new DefaultListModel<>();
        destinationList = new JList<>(destinationListModel);
        JScrollPane scrollPane = new JScrollPane(destinationList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Προορισμοί"));
        frame.add(scrollPane, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false); // Μην επιτρέπει επεξεργασία
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setPreferredSize(new Dimension(450, 
            200)); // Ρύθμιση διαστάσεων
        frame.add(resultScrollPane, BorderLayout.CENTER);
        // Κουμπί "Υποβολή" για μελλοντική επέκταση
        JButton submitButton = new JButton("Υποβολή Δεδομένων");
        submitButton.addActionListener(new SubmitDataListener(this));
        // Χρησιμοποιούμε το this για να περάσουμε την αναφορά στην κύρια κλάση
        frame.add(submitButton, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    // Μέθοδοι για πρόσβαση στα στοιχεία GUI
    public JTextField getStartField() {
        return startField;
    }

    public JTextField getDestinationField() {
        return destinationField;
    }

    public DefaultListModel<String> getDestinationListModel() {
        return destinationListModel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JList<String> getDestinationList() {
        return destinationList;
    }

    public GreekPorts getGreekPorts() {
        return greekPorts;
    }

    public JTextArea getResultArea() {
        return resultArea;
    }

    // Έλεγχος εγκυρότητας λιμανιού
    public boolean isValidPort(String portName) {
        return greekPorts.getPorts().stream().
            anyMatch(port -> port.getIsland().equalsIgnoreCase(portName));
    }

    // Εκκίνηση της εφαρμογής
    public static void main(String[] args) {
        new CruiseInput();
    }
}
