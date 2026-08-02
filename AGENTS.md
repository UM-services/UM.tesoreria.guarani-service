# Agent Instructions

## Commands

- Use JDK 25 and the Maven wrapper (`./mvnw`); the POM targets Java 25.
- `./mvnw test` is the standard verification command. The only current Spring context test is disabled because it requires PostgreSQL; run a focused test with `./mvnw -Dtest=ClassName test`.
- `./mvnw clean package` builds the executable JAR and generates the JaCoCo report. There is no repository-configured lint, formatter, typecheck, or code-generation task.
- Run locally with `./mvnw spring-boot:run`; the Docker build uses the same Java 25/Maven toolchain and produces `target/um.tesoreria.guarani-service.jar`.

## Runtime Requirements

- Startup expects PostgreSQL settings from `APP_SERVER`, `APP_DATABASE`, `APP_USERNAME`, and `APP_PASSWORD`; JPA uses `ddl-auto: validate`, so the existing `negocio` schema/tables must be present and are not created by the service.
- Consul is configured at `consul-service:8500`; OpenFeign discovers `tesoreria-core-service` through Consul. Calls that create preuniversitarios therefore require the core service as well.
- Set `APP_TESTING=true` when running tests or local tooling that must not execute the scheduled job. It disables the scheduler only; it does not remove the PostgreSQL requirement for Spring context startup.
- The scheduler runs every 10 minutes from 21:00 through 07:59 in `America/Argentina/Mendoza`. The `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/test` endpoint also triggers processing immediately and is not a harmless health check.

## Structure

- The application entrypoint is `src/main/java/um/tesoreria/guarani/GuaraniApplication.java`; cross-cutting activation is in `configuration/GuaraniConfiguration.java` (`@EnableFeignClients` and `@EnableScheduling`).
- Feature modules live under `hexagonal/guarani/` and follow `domain` (models and ports), `application` (services/use cases), and `infrastructure` (web, persistence, clients, schedulers) boundaries.
- Keep domain/application code independent of HTTP and JPA. Controllers map DTOs, persistence adapters map JPA entities, and repository interfaces in `domain/ports/out` are implemented under infrastructure.
- Existing REST routes are rooted at `/api/tesoreria/guarani`; API documentation is exposed at `/swagger-ui/index.html` and `/v3/api-docs` when the service is running.
