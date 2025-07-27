import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class SMSWelcome extends JFrame {
    private JLabel welcomeLabel;
    private JLabel dateTimeLabel;
    private JTextArea groupMembersTextArea;
    private JButton continueButton;

    public SMSWelcome() {
        setTitle("Stock Management System Welcome");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        welcomeLabel = new JLabel("Welcome to the Stock Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        groupMembersTextArea = new JTextArea();
        groupMembersTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(groupMembersTextArea);

        continueButton = new JButton("Continue");

        // Set layout
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(welcomeLabel);
        topPanel.add(dateTimeLabel);
        topPanel.add(scrollPane);

        add(topPanel, BorderLayout.CENTER);
        add(continueButton, BorderLayout.SOUTH);

        // Add action listener to continue button
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose(); // Close the welcome window
                new StockManagement().setVisible(true); // Open the main Stock Management window
            }
        });

        // Display welcome message, date/time, and group members
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        dateTimeLabel.setText("Current Date and Time: " + formattedDateTime);

        String[] groupMembers = { "Lee Ann", "Tan Jia Yi", "Goh Tok Yan", "Fan William" };
        StringBuilder groupMembersText = new StringBuilder("GROUP NO: 45\nGroup Members:\n");
        for (int i = 0; i < groupMembers.length; i++) {
            groupMembersText.append((i + 1)).append(". ").append(groupMembers[i]).append("\n");
        }
        groupMembersTextArea.setText(groupMembersText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SMSWelcome().setVisible(true);
            }
        });
    }
}