package com.alek0m0m.papyrusbackend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User not found" + message);
    }
}
