package com.app.computerstore.exceptions;

import java.sql.SQLException;

public class DeleteException extends RuntimeException {
    public DeleteException(String message, SQLException error) {
        super(message, error);
    }
}