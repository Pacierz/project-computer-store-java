package com.app.computerstore.database;

import com.app.computerstore.models.Order;
import com.app.computerstore.models.OrderRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.sql.Connection;

import static org.mockito.Mockito.*;

class OrderRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    OrderRepositoryImplementation orderRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrders() {
        ObservableList<Order> result = orderRepositoryImplementation.getOrders(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddOrder() {
        orderRepositoryImplementation.addOrder(new Order(0, 0, 0, 0, 0f, null, "status"));
    }

    @Test
    void testEditOrder() {
        orderRepositoryImplementation.editOrder(null, null, null, null, null, null, null);
    }

    @Test
    void testUpdateOrder() {
        orderRepositoryImplementation.updateOrder(new Order(0, 0, 0, 0, 0f, null, "status"));
    }

    @Test
    void testDeleteOrder() {
        orderRepositoryImplementation.deleteOrder(new Order(0, 0, 0, 0, 0f, null, "status"));
    }

    @Test
    void testSortById() {
        orderRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        OrderRepository result = OrderRepository.getDefaultImplementation();
        Assertions.assertEquals(new OrderRepositoryImplementation(), result);
    }
}

