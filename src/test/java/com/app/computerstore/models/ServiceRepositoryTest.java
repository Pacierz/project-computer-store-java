package com.app.computerstore.models;

import com.app.computerstore.database.ServiceRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServiceRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        ServiceRepository result = ServiceRepository.getDefaultImplementation();
        Assertions.assertEquals(new ServiceRepositoryImplementation(), result);
    }
}

