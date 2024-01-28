package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeliveryTest {
    Delivery delivery = new Delivery(0, "name", 0f);

    @Test
    void testOf() {
        Delivery result = Delivery.of(0, "name", 0f);
        Assertions.assertEquals(new Delivery(0, "name", 0f), result);
    }

    @Test
    void testValidate() {
        Delivery.validate("name", 0f);
    }

    @Test
    void testSetId() {
        delivery.setId(0);
    }

    @Test
    void testSetName() {
        delivery.setName("name");
    }

    @Test
    void testSetPrice() {
        delivery.setPrice(0f);
    }

    @Test
    void testBuilder() {
        Delivery.DeliveryBuilder result = Delivery.builder();
        Assertions.assertEquals(null, result);
    }
}

