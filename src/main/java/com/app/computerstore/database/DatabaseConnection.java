package com.app.computerstore.database;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa do zarządzania połączeniem z bazą danych.
 * Umożliwia nawiązywanie i zamykanie połączenia z bazą danych PostgreSQL.
 */

@Slf4j
public class DatabaseConnection {
    private final String username;
    private final String password;
    private static final String URL = "jdbc:postgresql://localhost:5432/sklepKomputerowy";
    @Getter private static Connection connection;

    /**
     * Konstruktor klasy DatabaseConnection.
     *
     * @param username Nazwa użytkownika do logowania do bazy danych.
     * @param password Hasło użytkownika do logowania do bazy danych.
     */
    public DatabaseConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Metoda nawiązująca połączenie z bazą danych.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            log.info("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zamykająca połączenie z bazą danych.
     */
    public static void disconnect() {
        try {
            connection.close();
            log.info("Disconnected from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}