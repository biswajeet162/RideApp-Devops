package com.driver_service.exceptions;

import com.driver_service.model.Driver;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String message) throws UserAlreadyExist {
        super(message);
    }
}

