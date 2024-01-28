package com.app.computerstore.exceptions;

import java.sql.SQLException;

/**
 * Wyjątek rzucany w przypadku błędu podczas aktualizacji danych w bazie danych.
 */
public class UpdateException extends RuntimeException {

    /**
     * Konstruktor z dwoma argumentami.
     *
     * @param message Opis błędu.
     * @param error   Wyjątek SQL związany z błędem.
     */
    public UpdateException(String message, SQLException error) {
        super(message, error);
    }
}