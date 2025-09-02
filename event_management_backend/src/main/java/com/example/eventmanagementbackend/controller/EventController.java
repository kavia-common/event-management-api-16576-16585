package com.example.eventmanagementbackend.controller;

import com.example.eventmanagementbackend.dto.EventRequest;
import com.example.eventmanagementbackend.model.Event;
import com.example.eventmanagementbackend.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing events.
 */
@RestController
@RequestMapping(path = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Events", description = "CRUD operations for events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    // PUBLIC_INTERFACE
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create event",
            description = "Creates a new event",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Event created",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
            }
    )
    public Event createEvent(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Event creation payload",
                    content = @Content(schema = @Schema(implementation = EventRequest.class))
            )
            EventRequest request) {
        return service.create(request);
    }

    // PUBLIC_INTERFACE
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update event",
            description = "Updates an existing event by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event updated",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
            }
    )
    public Event updateEvent(
            @Parameter(description = "Event id", required = true) @PathVariable Long id,
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Event update payload",
                    content = @Content(schema = @Schema(implementation = EventRequest.class))
            )
            EventRequest request) {
        return service.update(id, request);
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get event",
            description = "Retrieves an event by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event found",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
            }
    )
    public Event getEvent(@Parameter(description = "Event id", required = true) @PathVariable Long id) {
        return service.getById(id);
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(
            summary = "List events",
            description = "Lists all events",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of events",
                            content = @Content)
            }
    )
    public List<Event> listEvents() {
        return service.listAll();
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete event",
            description = "Deletes an event by id",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Event deleted"),
                    @ApiResponse(responseCode = "404", description = "Event not found", content = @Content)
            }
    )
    public void deleteEvent(@Parameter(description = "Event id", required = true) @PathVariable Long id) {
        service.delete(id);
    }
}
