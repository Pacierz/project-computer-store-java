package com.app.computerstore.exceptions;

import java.sql.SQLException;

public class AddException extends RuntimeException {
    public AddException(String message, SQLException error) {
        super(message, error);
    }
}