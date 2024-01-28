package com.app.computerstore.database;

import com.app.computerstore.models.Client;
import com.app.computerstore.models.ClientRepository;
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

class ClientRepositoryImplementationTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    ClientRepositoryImplementation clientRepositoryImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClients() {
        ObservableList<Client> result = clientRepositoryImplementation.getClients(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddClient() {
        clientRepositoryImplementation.addClient(new Client(0, "name", "surname", 0, "email", "address"));
    }

    @Test
    void testEditClient() {
        clientRepositoryImplementation.editClient(null, null, null, null, null, null);
    }

    @Test
    void testUpdateClient() {
        clientRepositoryImplementation.updateClient(new Client(0, "name", "surname", 0, "email", "address"));
    }

    @Test
    void testDeleteClient() {
        clientRepositoryImplementation.deleteClient(new Client(0, "name", "surname", 0, "email", "address"));
    }

    @Test
    void testSortById() {
        clientRepositoryImplementation.sortById(null, null);
    }

    @Test
    void testGetDefaultImplementation() {
        ClientRepository result = ClientRepository.getDefaultImplementation();
        Assertions.assertEquals(new ClientRepositoryImplementation(), result);
    }
}

