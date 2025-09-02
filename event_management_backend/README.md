# Event Management Backend

Spring Boot REST API for managing events. Includes CRUD endpoints, layered architecture (Controller, Service, Repository), H2 in-memory DB, and OpenAPI docs.

## Run

- Using Gradle wrapper:
  ./gradlew bootRun

App starts on http://localhost:8080

## API Docs

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/api-docs

## Endpoints

- POST /api/events (requires role ADMIN or ORGANIZER)
- PUT /api/events/{id} (requires role ADMIN or ORGANIZER)
- GET /api/events/{id} (public)
- GET /api/events?category=WORKSHOP (public; category optional)
- DELETE /api/events/{id} (requires role ADMIN or ORGANIZER)

### Authorization (Mocked)

Provide user role via one of:
- HTTP header: X-User-Role: ADMIN | ORGANIZER | USER
- Request body field: userRole (only on create/update). If both are provided, the header takes precedence.

Only ADMIN and ORGANIZER can create, update, and delete events. All roles (including USER) can view/list events.

### Event Categories

Supported categories: WORKSHOP, CONCERT, CORPORATE, MEETUP, CONFERENCE, OTHER

You can filter the list endpoint by category using the query parameter:
GET /api/events?category=WORKSHOP

### Payload example
POST /api/events

Headers:
X-User-Role: ADMIN

Body:
{
  "name": "Team Meeting",
  "description": "Monthly sync",
  "startTime": "2025-09-01T10:00:00Z",
  "endTime": "2025-09-01T11:00:00Z",
  "location": "Room A",
  "category": "CORPORATE"
}
