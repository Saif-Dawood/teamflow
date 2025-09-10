package com.example.teamflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    @ExceptionHandler({InvalidSortFieldException.class})
    public ResponseEntity<Object> handleInvalidSortFieldException(InvalidSortFieldException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    @ExceptionHandler({InvalidPageNumberException.class})
    public ResponseEntity<Object> handleInvalidPageNumberException(InvalidPageNumberException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    @ExceptionHandler({InvalidTaskStatusException.class})
    public ResponseEntity<Object> handleInvalidTaskStatusException(InvalidTaskStatusException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        if ("page".compareTo(exception.getName()) == 0) {
            return handleInvalidPageNumberException(
                new InvalidPageNumberException("Page Number is non-integer")
            );
        }

        if ("id".compareTo(exception.getName()) == 0) {
            return handleInvalidTaskIdException(
                new InvalidTaskIdException("Task Id is non-integer. please provide it like this: /api/tasks/{id}.")
            );
        }
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    @ExceptionHandler({InvalidTaskIdException.class})
    public ResponseEntity<Object> handleInvalidTaskIdException(InvalidTaskIdException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    @ExceptionHandler({InvalidTaskException.class})
    public ResponseEntity<Object> handleInvalidTaskException(InvalidTaskException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

    // For 404 exceptions
    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException exception) {
        
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.toString());
    }

    // For other unexpected errors
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.toString());
    }

}
