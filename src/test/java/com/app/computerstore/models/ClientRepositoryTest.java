package com.app.computerstore.models;

import com.app.computerstore.database.ClientRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        ClientRepository result = ClientRepository.getDefaultImplementation();
        Assertions.assertEquals(new ClientRepositoryImplementation(), result);
    }
}

