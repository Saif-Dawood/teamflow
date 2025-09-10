package com.example.teamflow.exception;

public class InvalidTaskStatusException extends IllegalArgumentException {

    public InvalidTaskStatusException(String message) {
        super(message);
    }

}

