package com.example.eventmanagementbackend.config;

import com.example.eventmanagementbackend.model.Event;
import com.example.eventmanagementbackend.model.EventCategory;
import com.example.eventmanagementbackend.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;

/**
 * Loads sample data into H2 database on startup for convenience.
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(EventRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Event kickoff = new Event(null, "Kickoff Meeting", "Project kickoff",
                        OffsetDateTime.now().plusDays(1),
                        OffsetDateTime.now().plusDays(1).plusHours(1),
                        "Room 101",
                        EventCategory.CORPORATE);
                repository.save(kickoff);

                Event demoDay = new Event(null, "Demo Day", "Product demo to stakeholders",
                        OffsetDateTime.now().plusDays(7),
                        OffsetDateTime.now().plusDays(7).plusHours(2),
                        "Auditorium",
                        EventCategory.CONFERENCE);
                repository.save(demoDay);
            }
        };
    }
}
