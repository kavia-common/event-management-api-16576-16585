package com.example.eventmanagementbackend.exception;

/**
 * Exception thrown when an event is not found.
 */
public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Event not found with id: " + id);
    }
}
