package com.app.computerstore.exceptions;

import java.sql.SQLException;

/**
 * Wyjątek rzucany w przypadku błędu podczas usuwania danych z bazy danych.
 */
public class DeleteException extends RuntimeException {

    /**
     * Konstruktor z dwoma argumentami.
     *
     * @param message Opis błędu.
     * @param error   Wyjątek SQL związany z błędem.
     */
    public DeleteException(String message, SQLException error) {
        super(message, error);
    }
}