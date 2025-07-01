package bank;

import bank.Customer;
import bank.CustomerDeposit;
import bank.RDCalculator;
import bank.ShowData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Bank Recurring Deposit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new GridLayout(0, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField sexField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField monthlyAmountField = new JTextField();
        JTextField tenureField = new JTextField();
        JTextField interestRateField = new JTextField();

        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("DOB (yyyy-mm-dd):"));
        frame.add(dobField);
        frame.add(new JLabel("Age:"));
        frame.add(ageField);
        frame.add(new JLabel("Sex:"));
        frame.add(sexField);
        frame.add(new JLabel("Phone:"));
        frame.add(phoneField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);
        frame.add(new JLabel("Address:"));
        frame.add(addressField);
        frame.add(new JLabel("Monthly Amount:"));
        frame.add(monthlyAmountField);
        frame.add(new JLabel("Tenure (months):"));
        frame.add(tenureField);
        frame.add(new JLabel("Interest Rate (%):"));
        frame.add(interestRateField);

        JButton submitButton = new JButton("Submit");
        JButton showDetailsButton = new JButton("Show Details");

        frame.add(submitButton);
        frame.add(showDetailsButton);

        final Customer[] customerHolder = new Customer[1];
        final CustomerDeposit[] depositHolder = new CustomerDeposit[1];

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String dob = dobField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String sex = sexField.getText();
                    String phone = phoneField.getText();
                    String email = emailField.getText();
                    String address = addressField.getText();
                    double monthlyAmount = Double.parseDouble(monthlyAmountField.getText());
                    int tenure = Integer.parseInt(tenureField.getText());
                    double interestRate = Double.parseDouble(interestRateField.getText());

                    Customer customer = new Customer(
                            name, dob, age, sex, phone, email, address
                    );

                    CustomerDeposit deposit = new CustomerDeposit(
                            customer, monthlyAmount, tenure, interestRate
                    );

                    RDCalculator calculator = new RDCalculator();
                    double maturity = calculator.calculateRD(
                            deposit.getMonthlyAmount(),
                            deposit.getTenureMonths(),
                            deposit.getInterestRate()
                    );

                    JOptionPane.showMessageDialog(frame,
                            "Maturity Amount: " + maturity,
                            "Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    ShowData.saveToCSV(deposit, maturity);

                    customerHolder[0] = customer;
                    depositHolder[0] = deposit;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid input. Please check your numbers.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        showDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Customer customer = customerHolder[0];
                CustomerDeposit deposit = depositHolder[0];

                if (customer == null || deposit == null) {
                    JOptionPane.showMessageDialog(frame,
                            "No details found. Please submit data first.",
                            "Info",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                RDCalculator calculator = new RDCalculator();
                double maturityAmount = calculator.calculateRD(
                        deposit.getMonthlyAmount(),
                        deposit.getTenureMonths(),
                        deposit.getInterestRate()
                );

                String[][] data = {
                        {"Name", customer.getName()},
                        {"Age", String.valueOf(customer.getAge())},
                        {"Monthly Amount", String.valueOf(deposit.getMonthlyAmount())},
                        {"Tenure (months)", String.valueOf(deposit.getTenureMonths())},
                        {"Interest Rate (%)", String.valueOf(deposit.getInterestRate())},
                        {"Maturity Amount", String.valueOf(maturityAmount)}
                };

                String[] columns = {"Field", "Value"};

                DefaultTableModel tableModel = new DefaultTableModel(data, columns);
                JTable table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);

                JButton deleteButton = new JButton("Delete Selected Row");
                JButton insertButton = new JButton("Insert Record");

                JPanel buttonPanel = new JPanel();
                buttonPanel.add(deleteButton);
                buttonPanel.add(insertButton);

                JFrame detailsFrame = new JFrame("Customer RD Details");
                detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                detailsFrame.setSize(600, 400);
                detailsFrame.setLayout(new BorderLayout());
                detailsFrame.add(scrollPane, BorderLayout.CENTER);
                detailsFrame.add(buttonPanel, BorderLayout.SOUTH);
                detailsFrame.setVisible(true);

                deleteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            tableModel.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(detailsFrame,
                                    "Selected row deleted.",
                                    "Deleted",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(detailsFrame,
                                    "Please select a row to delete.",
                                    "Info",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                insertButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JTextField nameField = new JTextField();
                        JTextField dobField = new JTextField();
                        JTextField ageField = new JTextField();
                        JTextField sexField = new JTextField();
                        JTextField phoneField = new JTextField();
                        JTextField emailField = new JTextField();
                        JTextField addressField = new JTextField();
                        JTextField monthlyField = new JTextField();
                        JTextField tenureField = new JTextField();
                        JTextField interestField = new JTextField();

                        JPanel insertPanel = new JPanel(new GridLayout(0, 2, 5, 5));
                        insertPanel.add(new JLabel("Name:"));
                        insertPanel.add(nameField);
                        insertPanel.add(new JLabel("DOB:"));
                        insertPanel.add(dobField);
                        insertPanel.add(new JLabel("Age:"));
                        insertPanel.add(ageField);
                        insertPanel.add(new JLabel("Sex:"));
                        insertPanel.add(sexField);
                        insertPanel.add(new JLabel("Phone:"));
                        insertPanel.add(phoneField);
                        insertPanel.add(new JLabel("Email:"));
                        insertPanel.add(emailField);
                        insertPanel.add(new JLabel("Address:"));
                        insertPanel.add(addressField);
                        insertPanel.add(new JLabel("Monthly Amount:"));
                        insertPanel.add(monthlyField);
                        insertPanel.add(new JLabel("Tenure (months):"));
                        insertPanel.add(tenureField);
                        insertPanel.add(new JLabel("Interest Rate (%):"));
                        insertPanel.add(interestField);

                        int result = JOptionPane.showConfirmDialog(detailsFrame,
                                insertPanel,
                                "Insert New Record",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                String name = nameField.getText();
                                String dob = dobField.getText();
                                int age = Integer.parseInt(ageField.getText());
                                String sex = sexField.getText();
                                String phone = phoneField.getText();
                                String email = emailField.getText();
                                String address = addressField.getText();
                                double monthly = Double.parseDouble(monthlyField.getText());
                                int tenure = Integer.parseInt(tenureField.getText());
                                double rate = Double.parseDouble(interestField.getText());

                                Customer newCustomer = new Customer(
                                        name, dob, age, sex, phone, email, address
                                );

                                CustomerDeposit newDeposit = new CustomerDeposit(
                                        newCustomer, monthly, tenure, rate
                                );

                                double newMaturity = new RDCalculator().calculateRD(
                                        newDeposit.getMonthlyAmount(),
                                        newDeposit.getTenureMonths(),
                                        newDeposit.getInterestRate()
                                );

                                customerHolder[0] = newCustomer;
                                depositHolder[0] = newDeposit;

                                String[][] newData = {
                                        {"Name", newCustomer.getName()},
                                        {"Age", String.valueOf(newCustomer.getAge())},
                                        {"Monthly Amount", String.valueOf(newDeposit.getMonthlyAmount())},
                                        {"Tenure (months)", String.valueOf(newDeposit.getTenureMonths())},
                                        {"Interest Rate (%)", String.valueOf(newDeposit.getInterestRate())},
                                        {"Maturity Amount", String.valueOf(newMaturity)}
                                };

                                tableModel.setDataVector(newData, columns);

                                JOptionPane.showMessageDialog(detailsFrame,
                                        "New record inserted successfully!",
                                        "Inserted",
                                        JOptionPane.INFORMATION_MESSAGE);

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(detailsFrame,
                                        "Invalid input. Please enter numbers correctly.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
            }
        });

        frame.setVisible(true);
    }
}
