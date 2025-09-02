package com.example.eventmanagementbackend.dto;

import com.example.eventmanagementbackend.model.EventCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

/**
 * DTO for creating or updating Event resources.
 */
public class EventRequest {

    @Schema(description = "Event name", example = "Team Meeting", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Event name is required")
    private String name;

    @Schema(description = "Event description", example = "Monthly team sync")
    private String description;

    @Schema(description = "Event start time in ISO-8601 format with offset", example = "2025-09-01T10:00:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Start time is required")
    private OffsetDateTime startTime;

    @Schema(description = "Event end time in ISO-8601 format with offset", example = "2025-09-01T11:00:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "End time is required")
    private OffsetDateTime endTime;

    @Schema(description = "Event location", example = "Conference Room A", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Location is required")
    private String location;

    @Schema(description = "Event category", example = "WORKSHOP", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = {"WORKSHOP","CONCERT","CORPORATE","MEETUP","CONFERENCE","OTHER"})
    private EventCategory category = EventCategory.OTHER;

    /**
     * Optional role field for request-body based authorization demonstration.
     * Alternatively, clients can pass header X-User-Role; controller will prioritize header when present.
     */
    @Schema(description = "Caller role for simple mock authorization. Allowed: ADMIN, ORGANIZER, USER", example = "ADMIN")
    private String userRole;

    public EventRequest() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public EventCategory getCategory() {
        return category;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
