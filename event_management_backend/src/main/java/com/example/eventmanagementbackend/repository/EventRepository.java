package com.example.eventmanagementbackend.repository;

import com.example.eventmanagementbackend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
