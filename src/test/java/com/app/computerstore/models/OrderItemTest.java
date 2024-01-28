package com.app.computerstore.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderItemTest {
    OrderItem orderItem = new OrderItem(0, 0, 0, 0, 0f);

    @Test
    void testOf() {
        OrderItem result = OrderItem.of(0, 0, 0, 0, 0f);
        Assertions.assertEquals(new OrderItem(0, 0, 0, 0, 0f), result);
    }

    @Test
    void testValidate() {
        OrderItem.validate(0, 0, 0, 0f);
    }

    @Test
    void testSetId() {
        orderItem.setId(0);
    }

    @Test
    void testSetOrderId() {
        orderItem.setOrderId(0);
    }

    @Test
    void testSetProductId() {
        orderItem.setProductId(0);
    }

    @Test
    void testSetQuantity() {
        orderItem.setQuantity(0);
    }

    @Test
    void testSetPrice() {
        orderItem.setPrice(0f);
    }

    @Test
    void testBuilder() {
        OrderItem.OrderItemBuilder result = OrderItem.builder();
        Assertions.assertEquals(null, result);
    }
}

