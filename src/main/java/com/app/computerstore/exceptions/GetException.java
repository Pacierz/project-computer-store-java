package com.app.computerstore.exceptions;

import java.sql.SQLException;

/**
 * Wyjątek rzucany w przypadku błędu podczas pobierania danych z bazy danych.
 */
public class GetException extends RuntimeException {

    /**
     * Konstruktor z dwoma argumentami.
     *
     * @param message Opis błędu.
     * @param error   Wyjątek SQL związany z błędem.
     */
    public GetException(String message, SQLException error) {
        super(message, error);
    }
}