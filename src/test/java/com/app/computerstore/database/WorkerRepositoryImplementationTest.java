package com.app.computerstore.database;

import com.app.computerstore.models.Worker;
import com.app.computerstore.models.WorkerRepository;
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

class WorkerRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    WorkerRepositoryImplementation workerRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWorkers() {
        ObservableList<Worker> result = workerRepositoryImplementation.getWorkers(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddWorker() {
        workerRepositoryImplementation.addWorker(new Worker(0, "name", "surname", 0, "email", "address", 0));
    }

    @Test
    void testEditWorker() {
        workerRepositoryImplementation.editWorker(null, null, null, null, null, null, null);
    }

    @Test
    void testUpdateWorker() {
        workerRepositoryImplementation.updateWorker(new Worker(0, "name", "surname", 0, "email", "address", 0));
    }

    @Test
    void testDeleteWorker() {
        workerRepositoryImplementation.deleteWorker(new Worker(0, "name", "surname", 0, "email", "address", 0));
    }

    @Test
    void testSortById() {
        workerRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        WorkerRepository result = WorkerRepository.getDefaultImplementation();
        Assertions.assertEquals(new WorkerRepositoryImplementation(), result);
    }
}

