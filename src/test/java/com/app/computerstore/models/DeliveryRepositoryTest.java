package com.app.computerstore.models;

import com.app.computerstore.database.DeliveryRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeliveryRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        DeliveryRepository result = DeliveryRepository.getDefaultImplementation();
        Assertions.assertEquals(new DeliveryRepositoryImplementation(), result);
    }
}

