package com.app.computerstore.models;

import com.app.computerstore.database.WorkerRepositoryImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkerRepositoryTest {

    @Test
    void testGetDefaultImplementation() {
        WorkerRepository result = WorkerRepository.getDefaultImplementation();
        Assertions.assertEquals(new WorkerRepositoryImplementation(), result);
    }
}

