# Changelog

## [5.1.1] - 2026-08-27

### Changed
- **Updated Spring Boot** from `4.1.0` to `4.1.1`.

### Added
- **GitHub Actions workflows** for automated deployment to `develop` and `staging` environments, including verification, Docker image build/push, and deployment steps.

## [5.1.0] - 2026-08-18

### Added
- **Restored preuniversitario creation by document**: the endpoint `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/create/{nroDocumento}` now sends each created personales response to `tesoreria-core-service` and returns the successfully created preuniversitarios.
- **Enriched personales responses** with the related `propuestaGuarani`, including its academic responsibility relationships.

### Changed
- **Updated** the Feign contract for `POST /api/tesoreria/core/guarani/alumno/create/preuniversitario` to receive `CreatePersonalesResponse`.

## [5.0.0] - 2026-08-11

### BREAKING
- **Changed** the response of `GET /api/tesoreria/guarani/alumno/generate/personales/create/{nroDocumento}` from `Boolean` to `List<CreatePersonalesResponse>`; each item carries `result`, the original `alumnoGuarani`, the `persona` and the `domicilio` created in the core service. Clients consuming the previous boolean response must update their integrations.
- **Changed** the internal Feign contract with `tesoreria-core-service`: `POST /api/tesoreria/core/guarani/alumno/create/personales` now returns `CreatePersonalesResponse` instead of `Boolean`, and `POST /api/tesoreria/core/guarani/alumno/desmarcar/enviadas` was removed; the core service must expose the new response contract.

### Added
- **New DTOs** `CreatePersonalesResponse`, `PersonaCoreResponse` and `DomicilioCoreResponse` to carry the personales, persona and domicilio created in the core service.
- **Logging** of the created personales list via `Jsonifier` in `CreatePersonalesByNroDocumentoUseCaseImpl`.

### Removed
- **CreatePreuniversitarioUseCase** and its implementation: the reusable preuniversitario creation flow (detection requests, chequera unmarking, batch processing) is no longer available.
- **CheckAllToUnmarkSendedUseCase** and its implementation: the Feign call to `POST /api/tesoreria/core/guarani/alumno/desmarcar/enviadas` was removed.
- **AlumnoDeteccionRequest** DTO, the request model used by the preuniversitario/desmarcar flow.
- **AlumnoGuaraniClient.desmarcarEnviados()** Feign operation.

### Changed
- **Preuniversitario creation by document** (`GET /api/tesoreria/guarani/alumno/generate/preuniversitario/create/{nroDocumento}`) now delegates to `CreatePersonalesByNroDocumentoUseCase`: it returns an empty list when no alumnos are found, and the preuniversitario creation loop is not yet restored (the non-empty path currently returns `null`).

## [4.0.0] - 2026-08-10

### BREAKING
- **Renamed** `GET /api/tesoreria/guarani/alumno/generate/personales/documento/{nroDocumento}` to `GET /api/tesoreria/guarani/alumno/generate/personales/create/{nroDocumento}`; clients using the previous path must update their integrations.
- **Renamed** `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/documento/{nroDocumento}` to `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/create/{nroDocumento}`; clients using the previous path must update their integrations.
- **Removed** `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/test`, the manual trigger of the preuniversitario scheduler.

### Removed
- **ProcessNextPreuniversitarioUseCase** and its implementation: the scheduled batch that selected alumnos by propuesta tipo `204` with inscription date within the last 15 days and created preuniversitarios via Feign is no longer available.
- **AlumnoGuaraniService.processNextInscripcion()**, the service method that delegated to the removed use case.
- **Scheduled preuniversitario processing**: `AlumnoGuaraniScheduler.generatePreuniversitarios()` no longer performs any work; its invocation of the service is commented out.

## [3.0.0] - 2026-08-05

### BREAKING
- **Changed** `GET /api/tesoreria/guarani/alumno/generate/preuniversitario/documento/{nroDocumento}` from `POST` to `GET`; clients using the previous HTTP method must update their integrations.

### Added
- **New endpoint** `GET /api/tesoreria/guarani/alumno/generate/personales/documento/{nroDocumento}` to create personales for all alumnos matching a document number.
- **New Feign operation** `POST /api/tesoreria/core/guarani/alumno/create/personales` delegated through `CreatePersonalesPort` and `CreatePersonalesByNroDocumentoUseCase`.

### Changed
- **Preuniversitario creation by document** now exposes the current `GET` contract and remains delegated through the existing reusable creation use case.

## [2.1.0] - 2026-08-04

### Added
- **New proposal-offer module** backed by `negocio.sga_propuestas_oferta`, including hexagonal domain, JPA persistence, mappings, and REST responses.
- **New endpoint** `GET /api/tesoreria/guarani/propuestaOferta/ubicacion/{ubicacion}` to query proposal offers by location.
- **New endpoint** `GET /api/tesoreria/guarani/propuestaOferta/ubicacion/{ubicacion}/propuestaTipo/204` to query proposal offers for proposal type `204` at a location.
- **Enriched alumno responses** with the related persona, principal document, contacts, and presented requirements of type `4`.

### Changed
- **Alumno lookup by document** now queries matching person documents directly and loads the related person graph with an entity graph and batched collections.
- **Proposal-offer responses** include the related proposal data.

## [2.0.0] - 2026-08-02

### BREAKING
- **Removed** `GET /api/tesoreria/guarani/propuestaAspira/`, the unfiltered collection endpoint; filtered queries must use the proposal, location, and inscription-date endpoint.
- **Moved** the `ubicacion` implementation under the `academicas` package and changed its repository contract to support filtering by location type.

### Added
- **New academic reference modules** for `ResponsableAcademica` and `UbicacionTipo`, including JPA persistence, domain mappings, and REST endpoints.
- **New endpoint** `GET /api/tesoreria/guarani/ubicacion/tipo/{ubicacionTipo}` to query locations by type.
- **New proposal/responsible-academic module** with:
  - `GET /api/tesoreria/guarani/propuestaResponsableAcademica/responsableAcademica/{responsableAcademica}`
  - `GET /api/tesoreria/guarani/propuestaResponsableAcademica/responsableAcademica/preuniversitario/{responsableAcademica}`
- **New filtered proposal-aspira endpoint** `GET /api/tesoreria/guarani/propuestaAspira/propuesta/{propuesta}/ubicacion/{ubicacion}/fechaInscripcionDesde/{fechaDesde}`.
- **Expanded response relationships** for alumno, propuesta, propuesta aspira, ubicación, and propuesta-responsable-académica data.

### Changed
- **Preuniversitario creation** now carries the alumno identifier in detection requests and removes redundant persistence fields from the alumno mapping path.
- **Application packages** now group academic and proposal modules consistently under `academicas` and `propuestas`.

## [1.1.0] - 2026-07-28

### Added
- **New endpoint `GET /api/tesoreria/guarani/requisito/tipo/{requisitoTipo}`** to query requisitos by tipo:
  - New `GetRequisitosByTipoUseCase` interface + implementation
  - New repository method `findByRequisitoTipo` in `RequisitoGuaraniRepository` and adapter
  - Wired through `RequisitoGuaraniService.getByRequisitoTipo()` and controller method
- **Expanded preuniversitario filter range**: requisito filter expanded from single value `1024` to range `>= 1024 && <= 1028` in `CreatePreuniversitarioUseCaseImpl`

### Changed
- **Preuniversitario scheduler now time-restricted**: changed from `@Scheduled(fixedRate = 600000)` to `@Scheduled(cron = "0 0/10 21-23,0-7 * * *", zone = "America/Argentina/Mendoza")` — runs every 10 minutes only during nighttime hours (21:00-07:59 Mendoza time) to reduce load during peak hours

## [1.0.0] - 2026-07-26

### BREAKING
- **Major package restructuring of all hexagonal modules** into grouped namespaces:
  - `alumno/` → `alumnos/alumno/`
  - `persona/` → `alumnos/persona/`
  - `personaContacto/` → `alumnos/personaContacto/`
  - `personaDocumento/` → `alumnos/personaDocumento/`
  - `propuesta/` → `propuestas/propuesta/`
  - `propuestaTipo/` → `propuestas/propuestaTipo/`
  - `propuestaAspira/` → `propuestas/propuestaAspira/`
  - `requisito/` → `requisitos/requisito/`
  - `requisitoPresentado/` → `requisitos/requisitoPresentado/`
  - `ubicacion/` remains at `ubicacion/`

### Added
- **New hexagonal module `RequisitoTipoGuarani`:**
  - Domain model (`RequisitoTipoGuarani`), dual use cases (getAll + getById), service, JPA repository adapter, REST controller (`GET /api/tesoreria/guarani/requisitoTipo/`, `GET /api/tesoreria/guarani/requisitoTipo/{requisitoTipo}`)
  - Custom exception `RequisitoTipoGuaraniException`
  - Entity mapped to `negocio.sga_requisitos_tipos` with fields: `requisitoTipo`, `nombre`, `descripcion`, `regla`, `publico`, `icono`
- **New use case `GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase`:**
  - Queries alumnos by propuesta tipo and fecha inscripcion after a given date
  - New repository `JpaAlumnoGuaraniByFechaRepository` with derived query `findByPropuestaRel_PropuestaTipoAndPropuestaAspiraRel_FechaInscripcionAfter`
- **New endpoint `GET /api/tesoreria/guarani/alumno/propuestaTipo/{propuestaTipo}/fechaLimite/{fechaLimite}`** to query alumnos by propuesta tipo and inscription date limit
- **Added `requisitoTipoRel` field** to `RequisitoGuarani` domain model and `RequisitoGuaraniEntity` (`@OneToOne` to `RequisitoTipoGuaraniEntity`)
- Enhanced `RequisitoGuaraniDtoMapper` and `RequisitoGuaraniResponse` with `requisitoTipoRel` mapping

### Changed
- **Preuniversitario scheduler now filters by fecha limite**: `ProcessNextPreuniversitarioUseCaseImpl` queries alumnos using `GetAlumnosByPropuestaTipoAndFechaInscripcionUseCase` with a 15-day limit (`LocalDate.now().minusDays(15)`)
- **PersonaGuaraniDtoMapper filters `requisitosPresentados`** to only include requisitos where `requisitoRel.requisitoTipo == 4`
- All package declarations and imports updated across 219 files to reflect new namespace structure

## [0.9.0] - 2026-07-06

### Added
- **New hexagonal module `PropuestaAspiraGuarani`:**
  - Domain model, dual use cases (getById + getAll), service, JPA repository adapter, REST controller
  - New endpoints: `GET /api/tesoreria/guarani/propuestaAspira/` and `GET /api/tesoreria/guarani/propuestaAspira/{propuestaAspira}`
  - Custom exception `PropuestaAspiraGuaraniException`
  - Entity mapped to `negocio.sga_propuestas_aspira`
- **New endpoint `POST /api/tesoreria/guarani/alumno/generate/preuniversitario/documento/{nroDocumento}`** to create preuniversitarios by document number:
  - New `CreatePreuniversitarioByNroDocumentoUseCase` interface + implementation filters alumnos by propuesta tipo 204 and delegates creation
- **New `CreatePreuniversitarioUseCase`** extracted preuniversitario creation logic from `ProcessNextPreuniversitarioUseCaseImpl` for reuse
- **Added `propuestaAspiraRel` field** to `AlumnoGuarani` domain model and `AlumnoGuaraniEntity` (`@OneToOne` to `PropuestaAspiraGuaraniEntity`)
- New documentation diagram `create-preuniversitario-documento.mmd` describing the preuniversitario-by-document flow

### Changed
- **Refactored `ProcessNextPreuniversitarioUseCaseImpl`** to delegate to `CreatePreuniversitarioUseCase` instead of inlining the creation logic
- Removed redundant debug log from `JpaAlumnoGuaraniRepositoryAdapter.findAllByPropuestaTipo()`
- Updated architecture diagrams, project structure, and endpoint tables to reflect new module

## [0.8.0] - 2026-07-03

### Added
- **New endpoint `GET /api/tesoreria/guarani/alumno/documento/{nroDocumento}`** to query alumnos by document number:
  - New `GetAlumnosByNroDocumentoUseCase` interface + implementation
  - New repository method `findAllByNroDocumento` with JPA query joining `personaRel.documentoPrincipalRel`
  - New `JpaAlumnoGuaraniRepositoryAdapter.findAllByNroDocumento()`
  - Wired through `AlumnoGuaraniService.getAllByNroDocumento()` and controller method
  - Returns `List<AlumnoGuaraniResponse>` via `AlumnoGuaraniDtoMapper`
- Added `GetAlumnosByNroDocumentoUseCase` dependency to `AlumnoGuaraniService`

### Changed
- Version bumped from `0.7.1` to `0.8.0`
- Updated project structure diagram to include the new controller method

## [0.7.1] - 2026-07-03

### Changed
- **Preuniversitario processing now logs alumnos with requisito 1024** before filtering: `ProcessNextPreuniversitarioUseCaseImpl` separates alumnos whose `personaRel.requisitosPresentados` contain `RequisitoGuarani` id `1024`, logs them via `Jsonifier`, then removes them from the processing queue (replaced `removeIf` with `filter` + `removeAll`)
- Version bumped from `0.7.0` to `0.7.1`
- Updated scheduler preuniversitario diagram (`scheduler-preuniversitario.mmd`) to reflect the new logging step

## [0.7.0] - 2026-07-02

### Added
- **New hexagonal module `RequisitoGuarani`:**
  - Domain model, dual use cases (getAll + getById), service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/requisito/`, `GET /api/tesoreria/guarani/requisito/{requisito}`)
  - Custom exception `RequisitoGuaraniException`
- **New hexagonal module `RequisitoPresentadoGuarani`:**
  - Domain model with `@OneToOne` relationship to `RequisitoGuarani`, dual use cases (getAll + getById), service, JPA adapter, REST controller (`GET /api/tesoreria/guarani/requisitoPresentado/`, `GET /api/tesoreria/guarani/requisitoPresentado/{requisitoPresentado}`)
  - Custom exception `RequisitoPresentadoGuaraniException`
- **Extended `PersonaGuarani`** with `requisitosPresentados` relationship: domain model, entity (`@OneToMany`), mappers, and response DTO now include `requisitosPresentados` collection

### Changed
- **Batch preuniversitario processing increased** from 10 to 50 alumnos per cycle in `ProcessNextPreuniversitarioUseCaseImpl`
- **New requisito filter in preuniversitario pipeline:** alumnos whose `personaRel.requisitosPresentados` contain a `RequisitoGuarani` with id `1024` (promedio >= 8 en secundaria) are now excluded from processing

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
