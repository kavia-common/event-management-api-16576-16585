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

- POST /api/events
- PUT /api/events/{id}
- GET /api/events/{id}
- GET /api/events
- DELETE /api/events/{id}

Payload example:
{
  "name": "Team Meeting",
  "description": "Monthly sync",
  "startTime": "2025-09-01T10:00:00Z",
  "endTime": "2025-09-01T11:00:00Z",
  "location": "Room A"
}
