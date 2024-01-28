package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {
    Client client = new Client(0, "name", "surname", 0, "email", "address");

    @Test
    void testOf() {
        Client result = Client.of(0, "name", "surname", 0, "email", "address");
        Assertions.assertEquals(new Client(0, "name", "surname", 0, "email", "address"), result);
    }

    @Test
    void testValidate() {
        Client.validate("name", "surname", 0, "email", "address");
    }

    @Test
    void testSetId() {
        client.setId(0);
    }

    @Test
    void testSetName() {
        client.setName("name");
    }

    @Test
    void testSetSurname() {
        client.setSurname("surname");
    }

    @Test
    void testSetPhoneNumber() {
        client.setPhoneNumber(0);
    }

    @Test
    void testSetEmail() {
        client.setEmail("email");
    }

    @Test
    void testSetAddress() {
        client.setAddress("address");
    }

    @Test
    void testBuilder() {
        Client.ClientBuilder result = Client.builder();
        Assertions.assertEquals(null, result);
    }
}

