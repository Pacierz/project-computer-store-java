package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.mockito.Mockito.*;

class OrderTest {
    @Mock
    Date date;
    @InjectMocks
    Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOf() {
        Order result = Order.of(0, 0, 0, 0, 0f, null, "status");
        Assertions.assertEquals(new Order(0, 0, 0, 0, 0f, null, "status"), result);
    }

    @Test
    void testValidate() {
        Order.validate(0, 0, 0, 0f, "status");
    }

    @Test
    void testSetId() {
        order.setId(0);
    }

    @Test
    void testSetClientId() {
        order.setClientId(0);
    }

    @Test
    void testSetQuantity() {
        order.setQuantity(0);
    }

    @Test
    void testSetDeliveryId() {
        order.setDeliveryId(0);
    }

    @Test
    void testSetPrice() {
        order.setPrice(0f);
    }

    @Test
    void testSetDate() {
        order.setDate(null);
    }

    @Test
    void testSetStatus() {
        order.setStatus("status");
    }

    @Test
    void testBuilder() {
        Order.OrderBuilder result = Order.builder();
        Assertions.assertEquals(null, result);
    }
}

