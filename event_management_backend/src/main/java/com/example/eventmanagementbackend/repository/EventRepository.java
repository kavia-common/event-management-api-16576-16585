package com.example.eventmanagementbackend.repository;

import com.example.eventmanagementbackend.model.Event;
import com.example.eventmanagementbackend.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    /**
     * Finds events by category.
     * @param category event category
     * @return list of events
     */
    List<Event> findByCategory(EventCategory category);
}
