import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class invoiceInterface extends JFrame {
    private double totalAmountDue;
    private ArrayList<lineItem> lineItems;
    private Address address;

    /*
    Top panel
    JTitle Invoice
    Address
     */
    JPanel topPanel;

    /*
    Middle panel
    Invoice will be generated here
    Along with amount due
     */
    JPanel midPanel;
    JTextArea displayInvoice;

    /*
    Bottom Panel
    Input areas
    Add to invoice button
    Generate invoice button
    Quit button
    Clear button
     */
    JPanel botPanel;
    JTextField itemField, qtyField, priceField;
    JButton addButton, generateButton, quitButton, clearButton;

    public invoiceInterface() {
        setTitle("Invoice");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // center frame in screen
        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lineItems = new ArrayList<>();

        createTopPanel();
        createMidPanel();
        createBotPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(botPanel, BorderLayout.SOUTH);

        add(mainPanel);

        promptForAddress();
    }

    public void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        topPanel.setBackground(new Color(244, 164, 96));

        JLabel title = new JLabel("Invoice");
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.black);

        JPanel addressPanel = new JPanel();
        addressPanel.setBackground(new Color(255, 228, 181));
        addressPanel.setLayout(new BorderLayout(5, 5));

        JLabel addressTitle = new JLabel("Address:");
        addressTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        addressTitle.setForeground(Color.black);
        addressPanel.add(addressTitle, BorderLayout.NORTH);
        JTextArea addressArea = new JTextArea(3, 35);
        addressArea.setEditable(false);
        addressArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addressPanel.add(new JScrollPane(addressArea), BorderLayout.CENTER);

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(addressPanel, BorderLayout.CENTER);
    }

    public void createMidPanel() {
        midPanel = new JPanel();
        midPanel.setBackground(new Color(173, 216, 230));
        midPanel.setLayout(new BorderLayout(10, 10));

        displayInvoice = new JTextArea(10, 35);
        displayInvoice.setEditable(false);
        displayInvoice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String item = "Item";
        String qty = "Qty";
        String price = "Price";
        String total = "Total";

        String formattedText = String.format("%-20s %-10s %-10s %-10s", item, qty, price, total);
        displayInvoice.setText(formattedText);

        JScrollPane invoiceScrollPane = new JScrollPane(displayInvoice);

        JLabel amountDue = new JLabel("Amount due: ");
        amountDue.setFont(new Font("Arial", Font.ITALIC, 18));
        amountDue.setForeground(Color.black);

        midPanel.add(invoiceScrollPane, BorderLayout.CENTER);
        midPanel.add(amountDue, BorderLayout.SOUTH);
    }

    public void createBotPanel() {
        botPanel = new JPanel();
        botPanel.setBackground(new Color(144, 238, 144));
        botPanel.setLayout(new GridLayout(2, 5, 10, 10));

        itemField = new JTextField(10);
        qtyField = new JTextField(5);
        priceField = new JTextField(7);

        addButton = new JButton("Add to Invoice");
        generateButton = new JButton("Generate Invoice");
        quitButton = new JButton("Quit");
        clearButton = new JButton("Clear");

        botPanel.add(new JLabel("Item:"));
        botPanel.add(itemField);
        botPanel.add(new JLabel("Qty:"));
        botPanel.add(qtyField);
        botPanel.add(new JLabel("Price:"));
        botPanel.add(priceField);
        botPanel.add(addButton);
        botPanel.add(generateButton);
        botPanel.add(quitButton);
        botPanel.add(clearButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLineItem();
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void addLineItem() {
        String itemName = itemField.getText();
        int quantity = Integer.parseInt(qtyField.getText());
        double unitPrice = Double.parseDouble(priceField.getText());

        Product product = new Product(itemName, unitPrice);
        lineItem lineItem = new lineItem(product, quantity);

        lineItems.add(lineItem);
        totalAmountDue += lineItem.getTotal();
    }

    private void generateInvoice() {
        StringBuilder invoiceText = new StringBuilder();
        invoiceText.append(String.format("%-20s %-10s %-10s %-10s\n", "Item", "Qty", "Price", "Total"));

        for (lineItem lineItem : lineItems) {
            Product product = lineItem.getProduct();
            invoiceText.append(String.format("%-20s %-10d %-10.2f %-10.2f\n", product.getName(), lineItem.getQuantity(), product.getUnitPrice(), lineItem.getTotal()));
        }

        displayInvoice.setText(invoiceText.toString());
        JLabel amountDueLabel = (JLabel) midPanel.getComponent(1);
        amountDueLabel.setText("Amount due: $" + String.format("%.2f", totalAmountDue));
    }

    private void clearFields() {
        itemField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }

    private void promptForAddress() {
        JTextField nameField = new JTextField(10);
        JTextField streetField = new JTextField(10);
        JTextField addressNumField = new JTextField(5);
        JTextField cityField = new JTextField(10);
        JTextField stateField = new JTextField(5);
        JTextField zipCodeField = new JTextField(5);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Street:"));
        panel.add(streetField);
        panel.add(new JLabel("Address Number:"));
        panel.add(addressNumField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("State:"));
        panel.add(stateField);
        panel.add(new JLabel("Zip Code:"));
        panel.add(zipCodeField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Address", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            address = new Address(
                    nameField.getText(),
                    streetField.getText(),
                    Integer.parseInt(addressNumField.getText()),
                    cityField.getText(),
                    stateField.getText(),
                    Integer.parseInt(zipCodeField.getText())
            );

            JTextArea addressArea = (JTextArea) ((JScrollPane) ((JPanel) topPanel.getComponent(1)).getComponent(1)).getViewport().getView();
            addressArea.setText(address.toString());
        } else {
            System.exit(0);
        }
    }
}