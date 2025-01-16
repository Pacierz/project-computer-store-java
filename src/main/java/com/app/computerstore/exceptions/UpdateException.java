package com.app.computerstore.exceptions;

import java.sql.SQLException;

public class UpdateException extends RuntimeException {
    public UpdateException(String message, SQLException error) {
        super(message, error);
    }
}