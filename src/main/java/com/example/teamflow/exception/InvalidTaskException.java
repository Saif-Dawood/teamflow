package com.example.teamflow.exception;

import jakarta.validation.ValidationException;

public class InvalidTaskException extends ValidationException {

    public InvalidTaskException(String message) {
        super(message);
    }
}

