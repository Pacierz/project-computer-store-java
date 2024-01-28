package com.app.computerstore.models;

import com.app.computerstore.database.OrderItemRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderItemRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        OrderItemRepository result = OrderItemRepository.getDefaultImplementation();
        Assertions.assertEquals(new OrderItemRepositoryImplementation(), result);
    }
}

