package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {
    private JTextField firstNameField, lastNameField, addressField, accountNumberField, amountField;
    private JTextArea resultArea;

    public Main() {
        JFrame frame = new JFrame("Client Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 20, 80, 25);
        panel.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(150, 20, 200, 25);
        panel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 50, 80, 25);
        panel.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 50, 200, 25);
        panel.add(lastNameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 80, 80, 25);
        panel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 80, 200, 25);
        panel.add(addressField);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(20, 110, 120, 25);
        panel.add(accountNumberLabel);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(150, 110, 200, 25);
        panel.add(accountNumberField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 140, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 140, 200, 25);
        panel.add(amountField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 170, 100, 25);
        panel.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.setBounds(260, 170, 100, 25);
        panel.add(loadButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 200, 350, 150);
        panel.add(resultArea);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClientData();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadClientDataFromJsonFile();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void saveClientData() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String accountNumber = accountNumberField.getText();
        double amount = Double.parseDouble(amountField.getText());

        Client client = new Client(firstName, lastName, address, accountNumber, amount);
        String clientData = client.toString();
        resultArea.append(clientData + "\n");

        // Save as JSON
        saveToJsonFile(client);
    }

    private void saveToJsonFile(Client client) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(client);

        try {
            File file = new File("client_data.json");
            FileWriter writer = new FileWriter(file, true);
            writer.write(json + "\n");
            writer.close();
            System.out.println("Data saved as JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadClientDataFromJsonFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("client_data.json"));
            String line;
            while ((line = br.readLine()) != null) {
                Client client = new Gson().fromJson(line, Client.class);
                resultArea.append(client.toString() + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}