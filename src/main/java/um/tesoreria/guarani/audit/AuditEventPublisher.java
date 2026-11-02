package um.tesoreria.guarani.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Punto de entrada de los eventos de auditoría de guarani.
 *
 * <ul>
 *   <li><b>Consultas</b> ({@code REQUEST/ACCESS}): las emite el filtro HTTP.</li>
 *   <li><b>Cambios</b> ({@code CHANGE/INSERT|UPDATE|DELETE}): los emiten los listeners de
 *       Hibernate con el diff enmascarado. Hoy no hay escrituras, pero la captura queda armada
 *       para cuando empiecen.</li>
 *   <li><b>Jobs</b> ({@code REQUEST/ACCESS}): ejecución de procesos programados.</li>
 * </ul>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditEventPublisher {

    static final String SERVICE = "tesoreria-guarani";

    private final AuditProperties properties;
    private final AuditQueue queue;

    /**
     * Construye el evento {@code CHANGE} de una entidad. No lo encola: el listener lo publica
     * en {@code afterCommit} para no auditar cambios que terminan en rollback.
     */
    public AuditEventPayload changePayload(String entity, String entityId,
                                           AuditDiffBuilder.Operation operation,
                                           AuditDiffBuilder.DiffResult diff,
                                           Map<String, Object> metadata) {
        return AuditEventPayload.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(OffsetDateTime.now(ZoneOffset.UTC))
                .correlationId(correlation())
                .service(SERVICE)
                .level("CHANGE")
                .operation(operation.name())
                .entity(entity)
                .entityId(entityId)
                .actorType(AuditContext.actorType())
                .actorId(AuditContext.actorId())
                .actorLogin(AuditContext.actorLogin())
                .actorTrust(AuditContext.actorTrust())
                .clientIp(AuditContext.clientIp())
                .httpMethod(AuditContext.httpMethod())
                .endpoint(AuditContext.endpoint())
                .outcome("OK")
                .affectedRows(1)
                .changedFields(diff.changedFields())
                .beforeData(diff.before())
                .afterData(diff.after())
                .metadata(metadata)
                .build();
    }

    /** Encola (o loguea) un evento ya construido. */
    public void publish(AuditEventPayload payload) {
        if (properties.getMode() == AuditProperties.Mode.OFF) {
            return;
        }
        if (properties.getMode() == AuditProperties.Mode.SEND) {
            queue.offer(payload);
        } else {
            log.info("auditoría (modo LOG): {} {} {} -> {}",
                    payload.getLevel(), payload.getOperation(), payload.getEntity(),
                    payload.getOutcome());
        }
    }

    /**
     * Registra la ejecución de un job: nombre, duración y resultado.
     *
     * <p>Se emite como {@code REQUEST} (no {@code CHANGE}) porque no hay entidad de negocio ni
     * diff: lo que importa es que el proceso corrió, cuándo y cómo terminó.</p>
     */
    public void publishScheduled(String job, String outcome, long durationMs, Throwable error) {
        if (properties.getMode() == AuditProperties.Mode.OFF || !properties.isRequestEvents()) {
            return;
        }
        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("job", job);
        metadata.put("durationMs", durationMs);
        if (error != null) {
            metadata.put("error", error.getClass().getSimpleName());
        }

        publish(AuditEventPayload.builder()
                .eventId(UUID.randomUUID().toString())
                .occurredAt(OffsetDateTime.now(ZoneOffset.UTC))
                .correlationId(UUID.randomUUID().toString())
                .service(SERVICE)
                .level("REQUEST")
                .operation("ACCESS")
                .actorType("SYSTEM")
                .actorTrust("SYSTEM")
                .endpoint("scheduler:" + job)
                .outcome(outcome)
                .affectedRows(null)
                .metadata(metadata)
                .build());
    }

    /** Si el evento nace dentro de un request, se correlaciona con su REQUEST. */
    private String correlation() {
        String current = AuditContext.correlationId();
        return current == null || current.isBlank() ? UUID.randomUUID().toString() : current;
    }
}
