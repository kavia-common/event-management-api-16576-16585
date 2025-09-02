package com.example.eventmanagementbackend.service;

import com.example.eventmanagementbackend.dto.EventRequest;
import com.example.eventmanagementbackend.exception.EventNotFoundException;
import com.example.eventmanagementbackend.exception.InvalidEventTimeException;
import com.example.eventmanagementbackend.model.Event;
import com.example.eventmanagementbackend.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer handling business logic for Event operations.
 */
@Service
@Transactional
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    // PUBLIC_INTERFACE
    public Event create(EventRequest request) {
        validateTimes(request);
        Event event = new Event();
        apply(request, event);
        return repository.save(event);
    }

    // PUBLIC_INTERFACE
    public Event update(Long id, EventRequest request) {
        validateTimes(request);
        Event existing = repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        apply(request, existing);
        return repository.save(existing);
    }

    // PUBLIC_INTERFACE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        repository.deleteById(id);
    }

    // PUBLIC_INTERFACE
    public Event getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }

    // PUBLIC_INTERFACE
    public List<Event> listAll() {
        return repository.findAll();
    }

    private void validateTimes(EventRequest request) {
        if (request.getStartTime() != null && request.getEndTime() != null) {
            if (request.getEndTime().isBefore(request.getStartTime())
                    || request.getEndTime().isEqual(request.getStartTime())) {
                throw new InvalidEventTimeException("End time must be after start time");
            }
        }
    }

    private void apply(EventRequest request, Event target) {
        target.setName(request.getName());
        target.setDescription(request.getDescription());
        target.setStartTime(request.getStartTime());
        target.setEndTime(request.getEndTime());
        target.setLocation(request.getLocation());
    }
}
