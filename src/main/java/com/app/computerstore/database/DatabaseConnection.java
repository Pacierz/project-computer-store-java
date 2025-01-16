package com.app.computerstore.database;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseConnection {
    private final String username;
    private final String password;
    private static final String URL = "jdbc:postgresql://localhost:5432/sklepKomputerowy";
    @Getter private static Connection connection;

    public DatabaseConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            log.info("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
            log.info("Disconnected from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}