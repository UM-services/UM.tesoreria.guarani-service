# Changelog

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
