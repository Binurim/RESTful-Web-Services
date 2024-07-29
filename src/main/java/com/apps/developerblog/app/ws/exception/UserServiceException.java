package com.apps.developerblog.app.ws.exception;

import java.io.Serial;

public class UserServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3294748604115112985L;

    public UserServiceException(String message) {
        super(message);
    }

}
