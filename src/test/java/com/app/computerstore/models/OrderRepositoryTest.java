package com.app.computerstore.models;

import com.app.computerstore.database.OrderRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        OrderRepository result = OrderRepository.getDefaultImplementation();
        Assertions.assertEquals(new OrderRepositoryImplementation(), result);
    }
}

