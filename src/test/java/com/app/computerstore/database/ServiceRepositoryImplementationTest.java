package com.app.computerstore.database;

import com.app.computerstore.models.Service;
import com.app.computerstore.models.ServiceRepository;
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

class ServiceRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    ServiceRepositoryImplementation serviceRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetServices() {
        ObservableList<Service> result = serviceRepositoryImplementation.getServices(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddService() {
        serviceRepositoryImplementation.addService(new Service(0, "name", 0f));
    }

    @Test
    void testEditService() {
        serviceRepositoryImplementation.editService(null, null, null);
    }

    @Test
    void testUpdateService() {
        serviceRepositoryImplementation.updateService(new Service(0, "name", 0f));
    }

    @Test
    void testDeleteService() {
        serviceRepositoryImplementation.deleteService(new Service(0, "name", 0f));
    }

    @Test
    void testSortById() {
        serviceRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        ServiceRepository result = ServiceRepository.getDefaultImplementation();
        Assertions.assertEquals(new ServiceRepositoryImplementation(), result);
    }
}

