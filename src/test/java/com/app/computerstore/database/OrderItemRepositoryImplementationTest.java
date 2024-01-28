package com.app.computerstore.database;

import com.app.computerstore.models.OrderItem;
import com.app.computerstore.models.OrderItemRepository;
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

class OrderItemRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    OrderItemRepositoryImplementation orderItemRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderItems() {
        ObservableList<OrderItem> result = orderItemRepositoryImplementation.getOrderItems(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddOrderItem() {
        orderItemRepositoryImplementation.addOrderItem(new OrderItem(0, 0, 0, 0, 0f));
    }

    @Test
    void testEditOrderItem() {
        orderItemRepositoryImplementation.editOrderItem(null, null, null, null, null);
    }

    @Test
    void testUpdateOrderItem() {
        orderItemRepositoryImplementation.updateOrderItem(new OrderItem(0, 0, 0, 0, 0f));
    }

    @Test
    void testDeleteOrderItem() {
        orderItemRepositoryImplementation.deleteOrderItem(new OrderItem(0, 0, 0, 0, 0f));
    }

    @Test
    void testSortById() {
        orderItemRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        OrderItemRepository result = OrderItemRepository.getDefaultImplementation();
        Assertions.assertEquals(new OrderItemRepositoryImplementation(), result);
    }
}

