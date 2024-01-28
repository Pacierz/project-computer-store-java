package com.app.computerstore.controllers;

import com.app.computerstore.database.DatabaseConnection;
import com.app.computerstore.utils.SceneSwitch;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Klasa kontrolera obsługująca proces logowania do aplikacji sklepu komputerowego.
 * Zarządza interakcjami i zdarzeniami związanymi z widokiem logowania.
 */

@Slf4j
public class LoginController {
    @FXML private Label usernameLabel;
    @FXML private TextField usernameField;
    @FXML private Label passwordLabel;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    /**
     * Obsługa zdarzenia kliknięcia przycisku "Sign in".
     * Próbuje zestawić połączenie z bazą danych na podstawie wprowadzonych danych logowania.
     * Przełącza scenę na główny widok aplikacji w przypadku poprawnych danych.
     * @param event Zdarzenie związane z kliknięciem przycisku "Sign in".
     * @throws IOException Wyjątek rzucany w przypadku problemów z przełączeniem sceny.
     */
    @FXML
    private void onLoginButtonClick(Event event) throws IOException {
        if (null != usernameField.getText() || usernameField.getText().isEmpty()) {
            new DatabaseConnection(usernameField.getText(), passwordField.getText()).connect();
            SceneSwitch.switchScene(event, "/app-view.fxml");
        } else {
            log.info("Puste pole z nazwą użytkownika");
        }
    }
}