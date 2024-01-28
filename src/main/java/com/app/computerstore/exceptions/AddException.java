package com.app.computerstore.exceptions;

import java.sql.SQLException;

/**
 * Wyjątek rzucany w przypadku błędu podczas dodawania danych do bazy danych.
 */
public class AddException extends RuntimeException {

    /**
     * Konstruktor z dwoma argumentami.
     *
     * @param message Opis błędu.
     * @param error   Wyjątek SQL związany z błędem.
     */
    public AddException(String message, SQLException error) {
        super(message, error);
    }
}