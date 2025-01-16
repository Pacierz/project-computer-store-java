package com.app.computerstore.exceptions;

import java.sql.SQLException;


public class GetException extends RuntimeException {
    public GetException(String message, SQLException error) {
        super(message, error);
    }
}