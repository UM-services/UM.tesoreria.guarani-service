# Changelog

## [0.6.0] - 2026-06-25

### BREAKING
- **Renamed fields in domain models, DTOs, and API responses:**
  - `AlumnoGuarani.alumnoId` → `alumno` (affects JSON response key)
  - `PersonaGuarani.personaId` → `persona`
  - `PropuestaGuarani.propuestaId` → `propuesta`
  - `PropuestaTipoGuarani.propuestaTipoId` → `propuestaTipo`
  - `UbicacionGuarani.ubicacionId` → `ubicacion`
  - All corresponding mappers, response DTOs, and entity converters updated
- **Removed `CheckAllToUnmarkSendedUseCase` from `AlumnoGuaraniService`** — the use case is now used only internally by `ProcessNextPreuniversitarioUseCaseImpl`; removed `checkAllAlumnosWithoutChequera()` public method from the service layer

### Added
- **New test endpoint** `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/test` in `AlumnoGuaraniController` for manual scheduler trigger
- **Batch preuniversitario processing:** `ProcessNextPreuniversitarioUseCaseImpl` now processes up to 10 pending alumnos per cycle (previously only the first one), with per-alumno error handling via try-catch
- **`alumno` field to `AlumnoDeteccionRequest`** for tracking the original alumno ID through the pipeline
- **`@ConditionalOnProperty` on `AlumnoGuaraniScheduler`** controlled by `app.testing` property (default: enabled)
- **`app.testing` property** in `bootstrap.yml` (env var `APP_TESTING`, default `false`) to disable scheduler during integration tests

### Changed
- Version bumped from `0.5.0` to `0.6.0`
- **Scheduler interval reduced** from `60000ms` (1 min) to `600000ms` (10 min) to reduce load on `tesoreria-core-service`
- **Preuniversitario processing flow:** now filters out already-processed alumnos after desmarcar call, then iterates over first 10 pending ones calling `createPreuniversitario` individually
- `JpaAlumnoGuaraniRepositoryAdapter.findAllByPropuestaTipo()` returns mutable list (`Collectors.toList()` instead of `.toList()`)
- Updated architecture and sequence diagrams in `docs/diagrams/` to reflect new field names, batch processing, and scheduler interval

## [0.5.0] - 2026-06-23

### Added
- **Scheduled preuniversitario processing:**
  - `@EnableScheduling` in `GuaraniConfiguration` to enable task scheduling
  - New `AlumnoGuaraniScheduler` with `@Scheduled(fixedRate = 60000)` for periodic preuniversitario processing
  - `ProcessNextPreuniversitarioUseCase` + implementation: queries alumnos by propuesta tipo 204, builds detection requests, filters already-processed ones, and processes first pending alumno
  - `CheckAllToUnmarkSendedUseCase` + implementation: delegates to Feign client to unmark already-sent alumnos
- **New Feign client `AlumnoGuaraniClient`** targeting `tesoreria-core-service` with endpoints:
  - `POST /api/tesoreria/core/guarani/alumno/create/preuniversitario`
  - `POST /api/tesoreria/core/guarani/alumno/desmarcar/enviadas`
- **New DTO `AlumnoDeteccionRequest`** with fields: ubicacion, propuesta, nroDocumento, tipoDocumento, pendiente
- **New utility class `Jsonifier`** for JSON serialization with pretty-print support and fluent builder API
- **`AlumnoGuarani.jsonify()`** method using Jsonifier for domain model debugging
- **`@Transactional(readOnly = true)`** on `JpaAlumnoGuaraniRepositoryAdapter.findAllByPropuestaTipo()`
- New documentation diagram `scheduler-preuniversitario.mmd` describing the scheduler flow

### Changed
- Version bumped from `0.4.0` to `0.5.0`
- Restored default Spring Boot logging (removed exclusion of `spring-boot-starter-logging`)
- Updated architecture diagrams (context, container, project structure) to reflect new components (`tesoreria-core-service`, scheduler, Feign client)
- Documentation pipeline (`generate-docs.yml`): added injection for the new scheduler sequence diagram
- Updated `README.md` with new scheduler section, updated diagrams, and project structure tree

## [0.4.0] - 2026-06-20

### Added
- **New hexagonal module `PersonaContactoGuarani`:**
  - Domain model, use case, service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/personaContacto/{personaContacto}`)
  - Custom exception `PersonaContactoGuaraniException`
- **New hexagonal module `PersonaDocumentoGuarani`:**
  - Domain model, use case, service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/personaDocumento/{documento}`)
  - Custom exception `PersonaDocumentoGuaraniException`
- **New hexagonal module `PropuestaTipoGuarani`:**
  - Domain model, dual use cases (getAll + getById), service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/propuestaTipo/`, `GET /api/tesoreria/guarani/propuestaTipo/{propuestaTipo}`)
  - Custom exception `PropuestaTipoGuaraniException`
- **New hexagonal module `TipoDocumentoGuarani`:**
  - Domain model, dual use cases (getAll + getById), service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/tipoDocumento/`, `GET /api/tesoreria/guarani/tipoDocumento/{tipoDocumento}`)
  - Custom exception `TipoDocumentoGuaraniException`
- **AlumnoGuarani**: new endpoint `GET /api/tesoreria/guarani/alumno/propuestaTipo/{propuestaTipo}` to query alumnos by propuesta tipo, with `GetAlumnosByPropuestaTipoUseCase`
- **AlumnoGuaraniEntity**: added JPA `@OneToOne` relationships to `PersonaGuaraniEntity`, `PropuestaGuaraniEntity`, `UbicacionGuaraniEntity`
- **AlumnoGuarani domain model**: added `personaRel`, `propuestaRel`, `ubicacionRel` fields
- **PersonaGuarani domain model**: extended with `personaContactos`, `personaDocumentos` collections
- **PropuestaGuarani**: new collection endpoint `GET /api/tesoreria/guarani/propuesta/` with `GetAllPropuestasUseCase`
- **UbicacionGuarani**: new collection endpoint `GET /api/tesoreria/guarani/ubicacion/` with `GetAllUbicacionesUseCase`
- New documentation diagram `hexagonal-collection-endpoint.mmd` describing the collection endpoint flow

### Changed
- Version bumped from `0.3.0` to `0.4.0`
- Documentation pipeline (`generate-docs.yml`): added injection for the new collection sequence diagram
- Updated `project-structure.mmd`, `container.mmd`, and endpoint notes in sequence diagrams to reflect new modules

## [0.3.0] - 2026-06-18

### Added
- New hexagonal architecture modules:
  - **AlumnoGuarani**: Domain model, use case, service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/alumno/{alumno}`)
  - **PersonaGuarani**: Domain model, use case, service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/persona/{persona}`)
  - **PropuestaGuarani**: Domain model, use case, service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/propuesta/{propuesta}`)
  - **UbicacionGuarani**: Domain model, use case, service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/ubicacion/{ubicacion}`)
- Spring Data JPA with PostgreSQL support (`spring-boot-starter-data-jpa`, `postgresql`)
- Lombok dependency for boilerplate reduction

### Changed
- Upgraded Spring Boot from `4.0.7` to `4.1.0`
- Upgraded Spring Cloud from `2025.1.1` to `2025.1.2`
- Upgraded SpringDoc OpenAPI from `3.0.2` to `3.0.3`
- Updated Consul discovery tags from `tesoreria,report` to `tesoreria,guarani`
- Added PostgreSQL datasource configuration in `bootstrap.yml`
- Added JPA/Hibernate configuration with `ddl-auto: validate`
- Disabled context load test (`@Disabled`) as it now requires PostgreSQL

## [0.2.0] - 2026-06-12

### Added
- New REST controller `HelloTest` with endpoint `GET /api/tesoreria/guarani/hello/test` for service health verification
- GitHub Actions CI/CD pipeline (`maven.yml`) with SonarCloud analysis, Docker image build and push
- GitHub Actions documentation pipeline (`generate-docs.yml`) with Mermaid diagram injection and GitHub Pages deployment

## [0.1.0] - 2026-06-12

### Added
- Initial project scaffold with Spring Boot 4.0.7 and Java 25
- Spring Cloud 2025.1.1 integration with Consul service discovery (`tesoreria-guarani-service`)
- OpenFeign declarative HTTP client support for inter-service communication
- Caffeine caching abstraction for high-performance in-memory caching
- OpenAPI 3.0 / Swagger UI documentation via SpringDoc 3.0.2
- Spring Boot Actuator for health checks and monitoring
- Bean Validation (`spring-boot-starter-validation`)
- Bootstrap configuration support with `bootstrap.yml`
- Custom ASCII banner (`banner.txt`)
- Docker multi-stage build (Maven 3 + Eclipse Temurin JDK 25) with non-root user
- Maven Wrapper (`mvnw`) for build reproducibility
- AGPL v3 license
- Context load integration test
