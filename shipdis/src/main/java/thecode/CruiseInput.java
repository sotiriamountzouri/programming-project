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
        frame.getContentPane().setBackground(new Color(102, 155, 188));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        Color buttonColor = new Color(0, 48, 73);
        Color textColor = Color.WHITE;
        
        // Πάνω μέρος - Εισαγωγή αφετηρίας
        JPanel startPanel = new JPanel(new FlowLayout());
        startPanel.setBackground(new Color(102, 155, 188));
        JLabel startLabel = new JLabel("Αφετηρία Κρουαζιέρας:");
        startLabel.setFont(labelFont);
        startPanel.add(startLabel);

        startField = new JTextField(20);
        startField.setFont(fieldFont);
        startField.setPreferredSize(new Dimension(350, 30));
        startField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        startPanel.add(startField);

        frame.add(startPanel);
        
          // Κάτω μέρος - Πεδίο για προσθήκη προορισμού και κουμπιά
        JPanel destinationPanel = new JPanel(new FlowLayout());
        destinationPanel.setBackground(new Color(102, 155, 188));
        JLabel destinationLabel = new JLabel("Προσθήκη Προορισμού:");
        destinationLabel.setFont(labelFont);
        destinationPanel.add(destinationLabel);

        destinationField = new JTextField(15);
        destinationField.setFont(fieldFont);
        destinationField.setPreferredSize(new Dimension(350, 30));
        destinationField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        destinationPanel.add(destinationField);

        JButton addButton = new JButton("Προσθήκη");
        addButton.setFont(buttonFont);
        addButton.setBackground(buttonColor);
        addButton.setForeground(textColor);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.addActionListener(new AddDestinationListener(this));
        destinationPanel.add(addButton);

        JButton clearButton = new JButton("Καθαρισμός Λίστας");
        clearButton.setFont(buttonFont);
        clearButton.setBackground(buttonColor);
        clearButton.setForeground(textColor);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(180, 40));
        clearButton.addActionListener(new ClearListListener(this));
        destinationPanel.add(clearButton);

        frame.add(destinationPanel);

        // Κέντρο - Λίστα προορισμών
        JPanel listPanel = new JPanel();
        listPanel.setBackground(new Color(102, 155, 188));
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        JLabel listTitle = new JLabel("Λίστα Προορισμών:");
        listTitle.setFont(labelFont);
        listTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        listPanel.add(listTitle);

        destinationListModel = new DefaultListModel<>();
        destinationList = new JList<>(destinationListModel);
        destinationList.setFont(fieldFont);
        JScrollPane scrollPane = new JScrollPane(destinationList);
        scrollPane.setBorder(BorderFactory.createLineBorder(buttonColor, 2));
        scrollPane.setPreferredSize(new Dimension(300, 150));
        listPanel.add(scrollPane);

        frame.add(listPanel);
        
        // Πεδίο εμφάνισης αποτελεσμάτων
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(102, 155, 188));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JLabel resultTitle = new JLabel("Αποτέλεσμα:");
        resultTitle.setFont(labelFont);
        resultTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(resultTitle);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16));
        resultArea.setPreferredSize(new Dimension(300, 150));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setPreferredSize(new Dimension(300, 150));
        resultScrollPane.setBorder(BorderFactory.createLineBorder(buttonColor, 2));
        resultPanel.add(resultScrollPane);

        frame.add(resultPanel);

        // Κουμπί "Υποβολή"
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(new Color(102, 155, 188));

        JButton submitButton = new JButton("Υποβολή Δεδομένων");
        submitButton.setFont(buttonFont);
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(textColor);
        submitButton.setFocusPainted(false);
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.addActionListener(new SubmitDataListener(this));
        submitPanel.add(submitButton);

        frame.add(submitPanel);

        // Μετακίνηση του κουμπιού "Υποβολή" πιο κάτω
        submitPanel.setPreferredSize(new Dimension(200, 100));
        
        // Κεντράρισμα παραθύρου
        frame.setLocationRelativeTo(null);

        // Εμφάνιση παραθύρου
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
