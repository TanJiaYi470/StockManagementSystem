import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StockManagement extends JFrame {
    private JTextArea outputTextArea;
    private JButton viewButton, addStockButton, deductStockButton, discontinueButton, addProductButton, exitButton;

    private static List<Product> products = new ArrayList<>();
    private static UserInfo currentUser;

    public StockManagement() {
    	String fullName = JOptionPane.showInputDialog(null, "Enter your full name (First and Last):");
    	if (fullName == null || fullName.trim().isEmpty()) {
    	    JOptionPane.showMessageDialog(null, "Name cannot be empty. Exiting...");
    	    System.exit(0);
    	}
    	
    	// Create UserInfo object, which generates the ID internally
    	currentUser = new UserInfo(fullName);

    	// Show greeting and ID
    	JOptionPane.showMessageDialog(null, "Welcome, " + currentUser.getUsername() + "!\nYour ID is: " + currentUser.getUserID());
    	
    	 String input = JOptionPane.showInputDialog(null, 
                 "Would you like to add products?\nEnter 0 to exit or any other number to continue:", 
                 "Product Entry", JOptionPane.QUESTION_MESSAGE);

             if (input == null || input.trim().equals("0")) {
                 JOptionPane.showMessageDialog(null,
                     "Thank you for using the Stock Management System!\nUser ID: " + currentUser.getUserID() +
                     "\nName: " + currentUser.getUsername(),
                     "Exit", JOptionPane.INFORMATION_MESSAGE);
                 System.exit(0);
             }
    	
        setTitle("Stock Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components
        outputTextArea = new JTextArea(15, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        viewButton = new JButton("View Products");
        addStockButton = new JButton("Add Stock");
        deductStockButton = new JButton("Deduct Stock");
        discontinueButton = new JButton("Discontinue Product");
        addProductButton = new JButton("Add Product");
        exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        buttonPanel.add(viewButton);
        buttonPanel.add(addStockButton);
        buttonPanel.add(deductStockButton);
        buttonPanel.add(discontinueButton);
        buttonPanel.add(addProductButton);
        buttonPanel.add(exitButton);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });

        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStock();
            }
        });

        deductStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deductStock();
            }
        });

        discontinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discontinueProduct();
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewProduct();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });
    }
    
    private void viewProducts() {
    outputTextArea.setText("");
    outputTextArea.append("Products:\n");
    if (products.isEmpty()) {
        outputTextArea.append("No products available.\n");
    } else {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            outputTextArea.append("Product " + (i + 1) + ":\n");
            outputTextArea.append("Item ID: " + product.getItemNum() + "\n");
            outputTextArea.append("Product name: " + product.getProductName() + "\n");

            if (product instanceof TV) {
                TV tv = (TV) product;
                outputTextArea.append("Screen Type: " + tv.getScreenType() + "\n");
                outputTextArea.append("Resolution: " + tv.getResolution() + "\n");
                outputTextArea.append("Display Size: " + tv.getDisplaySize() + "\n");
            } else if (product instanceof Refrigerator) {
                Refrigerator refrigerator = (Refrigerator) product;
                outputTextArea.append("Door Design: " + refrigerator.getDoorDesign() + "\n");
                outputTextArea.append("Color: " + refrigerator.getColor() + "\n");
                outputTextArea.append("Capacity (litres): " + refrigerator.getCapacity() + "\n");
            } else if (product instanceof Microwave) {
                Microwave microwave = (Microwave) product;
                outputTextArea.append("Sensor: " + microwave.getSensors() + "\n");
                outputTextArea.append("Power Consumption (W): " + microwave.getPowerComsumption() + "\n");
                outputTextArea.append("Size (litres): " + microwave.getSize() + "\n");
            }

            outputTextArea.append("Quantity available: " + product.getQuantityAvailable() + "\n");
            outputTextArea.append("Price (RM): " + product.getPrice() + "\n");
            outputTextArea.append("Product status: " + (product.isStatusProduct() ? "Available" : "Discontinued") + "\n");
            outputTextArea.append("----------------------------------------\n");
        }
    }
}


    private void addStock() {
        outputTextArea.setText("");
        viewProducts();
        int productNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the product number to add stock to:"));
        int productIndex = productNumber - 1; // Adjust for 1-based indexing

        if (productNumber >= 1 && productNumber <= products.size()) {
            Product product = products.get(productIndex);
            if (product.isStatusProduct()) {
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity to add:"));
                product.addQuantityStock(quantity);
                outputTextArea.append("Stock added successfully.\n");
            } else {
                outputTextArea.append("Cannot add stock to a discontinued product.\n");
            }
        } else {
            outputTextArea.append("Invalid product number.\n");
        }
    }

    private void deductStock() {
        outputTextArea.setText("");
        viewProducts();
        int productIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter the product number to deduct stock from:")) - 1; // Adjust for zero-based indexing

        if (productIndex >= 0 && productIndex < products.size()) {
            Product product = products.get(productIndex);

            int quantity;
            while (true) {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity to deduct:"));
                // Check if the current stock level is sufficient for the deduction
                if (product.getQuantityAvailable() >= quantity) {
                    product.deductQuantityStock(quantity);
                    outputTextArea.append("Stock deducted successfully.\n");
                    break; // Exit the loop if the deduction was successful
                } else {
                    outputTextArea.append("Error: Insufficient quantity to deduct. Only " + product.getQuantityAvailable() + " units are available. Please enter a correct deduct number.\n");
                }
            }
        } else {
            outputTextArea.append("Invalid product number.\n");
        }
    }

    private void discontinueProduct() {
        outputTextArea.setText("");
        viewProducts();
        int productIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter the product index to discontinue:")) - 1; // -1 for zero-based indexing

        if (productIndex >= 0 && productIndex < products.size()) {
            Product product = products.get(productIndex);
            if (product.isStatusProduct()) {
                product.setStatusProduct(false);
                outputTextArea.append("Product discontinued successfully.\n");
            } else {
                outputTextArea.append("This product is already discontinued.\n");
            }
        } else {
            outputTextArea.append("Invalid product index. Please select a product from the list.\n");
        }
    }

    private void addNewProduct() {
        outputTextArea.setText("");
        String addMore;
        while (true) {
            addMore = JOptionPane.showInputDialog("Would you like to add any products? (y/n)").trim().toLowerCase();
            if (addMore.equals("n")) {
                break; // Exit the loop if the user doesn't want to add more products
            } else if (addMore.equals("y")) {
                addProductPhase(); // Call the method to add a new product
            } else {
                outputTextArea.append("Invalid input. Please enter 'y' for yes or 'n' for no.\n");
            }
        }
    }

    private void addProductPhase() {
        while (true) {
            int productType = Integer.parseInt(JOptionPane.showInputDialog("Select the product type to add:\n1. Refrigerator\n2. TV\n3.Microwave\nEnter your choice (1 or 2 or 3):"));
            switch (productType) {
                case 1:
                    addRefrigerator();
                    break;
                case 2:
                    addTV();
                    break;
                case 3:
                	addMicrowave();
                	break;
                default:
                    outputTextArea.append("Invalid selection. Please enter 1 for Refrigerator or 2 for TV or 3 for Microwave.\n");
                    break;
            }
        }
    }

    //add Refrigerator
    private void addRefrigerator() {
        int itemID = 0;
        while (true) {
            try {
                itemID = Integer.parseInt(JOptionPane.showInputDialog("Enter item ID:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        String name = JOptionPane.showInputDialog("Enter name:");
        String doorDesign = JOptionPane.showInputDialog("Enter door design:");
        String color = JOptionPane.showInputDialog("Enter color:");

        int quantity = 0;
        while (true) {
            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        double price = 0.00;
        while (true) {
            try {
                price = Double.parseDouble(JOptionPane.showInputDialog("Enter price (eg. 6.00):"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        int capacity = 0;
        while (true) {
            try {
                capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter capacity (in litres):"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        Refrigerator refrigerator = new Refrigerator(name, price, quantity, itemID, doorDesign, color, capacity);
        products.add(refrigerator);
        outputTextArea.append("Refrigerator added successfully.\n");
    }
    
    //add TV
    private void addTV() {
        int itemID = 0;
        while (true) {
            try {
                itemID = Integer.parseInt(JOptionPane.showInputDialog("Enter item ID:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        String name = JOptionPane.showInputDialog("Enter name:");
        String screenType = JOptionPane.showInputDialog("Enter screen type:");
        String resolution = JOptionPane.showInputDialog("Enter resolution:");

        int quantity = 0;
        while (true) {
            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        double price = 0.00;
        while (true) {
            try {
                price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        int displaySize = 0;
        while (true) {
            try {
                displaySize = Integer.parseInt(JOptionPane.showInputDialog("Enter display size:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        TV tv = new TV(name, price, quantity, itemID, screenType, resolution, displaySize);
        products.add(tv);
        outputTextArea.append("TV added successfully.\n");
    }
    
    //add Microwave
    private void addMicrowave() {
        int itemID = 0;
        while (true) {
            try {
                itemID = Integer.parseInt(JOptionPane.showInputDialog("Enter item ID:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        String name = JOptionPane.showInputDialog("Enter name:");
        String sensors = JOptionPane.showInputDialog("Enter sensors types:");

        int quantity = 0;
        while (true) {
            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        double price = 0.00;
        while (true) {
            try {
                price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }
        
        int powerComsumption = 0;
        while (true) {
            try {
                powerComsumption = Integer.parseInt(JOptionPane.showInputDialog("Enter power comsumption (in Watt):"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }

        int size = 0;
        while (true) {
            try {
                size = Integer.parseInt(JOptionPane.showInputDialog("Enter size (in litres):"));
                break;
            } catch (NumberFormatException e) {
                outputTextArea.append("Wrong input, please input numbers only.\n");
            }
        }


        Microwave microwave = new Microwave(name, price, quantity, itemID, sensors, powerComsumption, size);
        products.add(microwave);
        outputTextArea.append("Microwave added successfully.\n");
    }
    
    public static void showWelcomeWindow() {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setSize(600, 400);
        welcomeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        welcomeFrame.setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Stock Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel dateTimeLabel = new JLabel("", SwingConstants.CENTER);
        LocalDateTime now = LocalDateTime.now();
        dateTimeLabel.setText("Current Date and Time: " + now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        JTextArea membersTextArea = new JTextArea();
        membersTextArea.setEditable(false);
        String[] groupMembers = { "Lee An", "Tan Jia Yi", "Goh Tok Yan", "Fan William" };
        StringBuilder members = new StringBuilder("GROUP NO: 45\nGroup Members:\n");
        for (int i = 0; i < groupMembers.length; i++) {
            members.append((i + 1)).append(". ").append(groupMembers[i]).append("\n");
        }
        membersTextArea.setText(members.toString());

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(e -> {
            welcomeFrame.dispose();
            new StockManagement().setVisible(true);
        });

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(welcomeLabel);
        topPanel.add(dateTimeLabel);
        topPanel.add(new JScrollPane(membersTextArea));

        welcomeFrame.setLayout(new BorderLayout());
        welcomeFrame.add(topPanel, BorderLayout.CENTER);
        welcomeFrame.add(continueButton, BorderLayout.SOUTH);
        welcomeFrame.setVisible(true);
    }

    private void exitProgram() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showWelcomeWindow());
    }
}
