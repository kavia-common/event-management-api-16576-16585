package com.example.eventmanagementbackend.controller;

import com.example.eventmanagementbackend.dto.EventRequest;
import com.example.eventmanagementbackend.model.Event;
import com.example.eventmanagementbackend.model.EventCategory;
import com.example.eventmanagementbackend.service.AuthUtil;
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
            description = "Creates a new event. Requires role ADMIN or ORGANIZER. Role may be provided via header X-User-Role or in request body field userRole.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Event created",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    public Event createEvent(
            @RequestHeader(value = "X-User-Role", required = false)
            @Parameter(description = "Caller role. Allowed values: ADMIN, ORGANIZER, USER", example = "ADMIN")
            String headerRole,
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Event creation payload",
                    content = @Content(schema = @Schema(implementation = EventRequest.class))
            )
            EventRequest request) {
        String role = headerRole != null ? headerRole : request.getUserRole();
        AuthUtil.requireOrganizerOrAdmin(role);
        return service.create(request);
    }

    // PUBLIC_INTERFACE
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update event",
            description = "Updates an existing event by id. Requires role ADMIN or ORGANIZER. Role may be provided via header X-User-Role or in request body field userRole.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Event updated",
                            content = @Content(schema = @Schema(implementation = Event.class))),
                    @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    public Event updateEvent(
            @Parameter(description = "Event id", required = true) @PathVariable Long id,
            @RequestHeader(value = "X-User-Role", required = false)
            @Parameter(description = "Caller role. Allowed values: ADMIN, ORGANIZER, USER", example = "ORGANIZER")
            String headerRole,
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Event update payload",
                    content = @Content(schema = @Schema(implementation = EventRequest.class))
            )
            EventRequest request) {
        String role = headerRole != null ? headerRole : request.getUserRole();
        AuthUtil.requireOrganizerOrAdmin(role);
        return service.update(id, request);
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(
            summary = "Get event",
            description = "Retrieves an event by id. Accessible by all roles.",
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
            description = "Lists events with optional filtering by category via query param 'category'. Accessible by all roles.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of events",
                            content = @Content)
            }
    )
    public List<Event> listEvents(
            @Parameter(description = "Optional category filter. Allowed values: WORKSHOP, CONCERT, CORPORATE, MEETUP, CONFERENCE, OTHER")
            @RequestParam(value = "category", required = false) EventCategory category
    ) {
        return service.listByCategory(category);
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete event",
            description = "Deletes an event by id. Requires role ADMIN or ORGANIZER. Role may be provided via header X-User-Role.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Event deleted"),
                    @ApiResponse(responseCode = "404", description = "Event not found", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    public void deleteEvent(
            @Parameter(description = "Event id", required = true) @PathVariable Long id,
            @RequestHeader(value = "X-User-Role", required = false)
            @Parameter(description = "Caller role. Allowed values: ADMIN, ORGANIZER, USER", example = "ADMIN")
            String headerRole
    ) {
        AuthUtil.requireOrganizerOrAdmin(headerRole);
        service.delete(id);
    }
}
