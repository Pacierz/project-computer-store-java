package com.app.computerstore.database;

import com.app.computerstore.models.Delivery;
import com.app.computerstore.models.DeliveryRepository;
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

class DeliveryRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    DeliveryRepositoryImplementation deliveryRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDelivery() {
        ObservableList<Delivery> result = deliveryRepositoryImplementation.getDelivery(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddDelivery() {
        deliveryRepositoryImplementation.addDelivery(new Delivery(0, "name", 0f));
    }

    @Test
    void testEditDelivery() {
        deliveryRepositoryImplementation.editDelivery(null, null, null);
    }

    @Test
    void testUpdateDelivery() {
        deliveryRepositoryImplementation.updateDelivery(new Delivery(0, "name", 0f));
    }

    @Test
    void testDeleteDelivery() {
        deliveryRepositoryImplementation.deleteDelivery(new Delivery(0, "name", 0f));
    }

    @Test
    void testSortById() {
        deliveryRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        DeliveryRepository result = DeliveryRepository.getDefaultImplementation();
        Assertions.assertEquals(new DeliveryRepositoryImplementation(), result);
    }
}

