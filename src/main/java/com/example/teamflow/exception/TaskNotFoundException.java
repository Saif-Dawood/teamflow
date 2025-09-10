package com.example.teamflow.exception;

import java.util.NoSuchElementException;

public class TaskNotFoundException extends NoSuchElementException {

    public TaskNotFoundException(String message) {
        super(message);
    }

}

