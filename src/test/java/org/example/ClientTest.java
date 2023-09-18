package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testToString() {
        // We create a Client object
        Client client = new Client("John", "Doe", "123 Main St", "1234567890", 1000.0);

        // We invoke the toString() method
        String result = client.toString();

        // Expected result
        String expected = "Client{firstName='John', lastName='Doe', address='123 Main St', accountNumber='1234567890', amount=1000.0}";

        // We are checking if the expected result matches the result returned by the toString() method
        assertEquals(expected, result);
    }

    @Test
    void testAccountNumberContainsOnlyDigits() {
        //"We create a Client object with an incorrect account number containing a letter."
        Client client = new Client("John", "Doe", "123 Main St", "123A567890", 1000.0);

        // We check if the account number contains only digits
        assertFalse(client.isValidAccountNumber());
    }

    @Test
    void testAmountContainsOnlyDigits() {
        // We create a Client object with an incorrect amount containing a letter
        Client client = new Client("John", "Doe", "123 Main St", "1234567890", Double.NaN);

        // We check if the account number contains only digits
        assertFalse(client.isValidAmount());
    }
}