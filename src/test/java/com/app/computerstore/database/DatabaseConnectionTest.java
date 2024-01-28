package com.app.computerstore.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.sql.Connection;

import static org.mockito.Mockito.*;

class DatabaseConnectionTest {
    @Mock
    Connection connection;
    @Mock
    Logger log;
    @InjectMocks
    DatabaseConnection databaseConnection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConnect() {
        databaseConnection.connect();
    }

    @Test
    void testDisconnect() {
        DatabaseConnection.disconnect();
    }
}

