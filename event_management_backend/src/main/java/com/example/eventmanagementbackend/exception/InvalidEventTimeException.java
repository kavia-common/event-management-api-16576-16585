package com.example.eventmanagementbackend.exception;

/**
 * Exception thrown when start/end time validation fails.
 */
public class InvalidEventTimeException extends RuntimeException {
    public InvalidEventTimeException(String message) {
        super(message);
    }
}
