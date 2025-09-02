package com.example.eventmanagementbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * OpenAPI configuration providing API metadata and tags.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Event Management API",
                version = "1.0.0",
                description = "REST API for creating, updating, deleting, and listing events. " +
                        "RBAC (mock): use header X-User-Role=ADMIN|ORGANIZER for create/update/delete. " +
                        "Categories supported for filtering: WORKSHOP, CONCERT, CORPORATE, MEETUP, CONFERENCE, OTHER.",
                contact = @Contact(name = "Event API Team", email = "support@example.com")
        ),
        tags = {
                @Tag(name = "Events", description = "Operations related to event resources"),
                @Tag(name = "Hello Controller", description = "Basic endpoints for eventmanagementbackend")
        }
)
public class OpenApiConfig {
    // No explicit beans required for springdoc starter basic setup.
}
