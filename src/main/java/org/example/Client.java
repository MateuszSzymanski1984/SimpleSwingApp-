package org.example;

class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String accountNumber;
    private double amount;

    public Client(String firstName, String lastName, String address, String accountNumber, double amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    public boolean isValidAccountNumber() {
        return accountNumber.matches("\\d+");
    }

    public boolean isValidAmount() {
        try {
            Double.parseDouble(String.valueOf(amount));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}