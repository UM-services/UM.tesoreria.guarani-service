package um.tesoreria.guarani.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Configuración de la auditoría de guarani.
 *
 * <p>Hoy el servicio sólo lee de la base PostgreSQL de Guaraní, así que el evento principal es
 * la <b>consulta</b> ({@code ACCESS}). Cuando empiece a escribir tablas, los listeners de
 * Hibernate emiten además un {@code CHANGE} por entidad con el diff enmascarado.</p>
 *
 * <p>Sin base propia no hay outbox: los eventos van a una cola acotada en memoria. Si el proceso
 * muere con eventos en vuelo, se pierden. Está documentado en
 * PLANIFICACION_AUDITORIA_TESORERIA.md.</p>
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "tesoreria.audit")
public class AuditProperties {

    public enum Mode {
        /** No captura nada. */
        OFF,
        /** Captura y loguea, sin enviar. */
        LOG,
        /** Encola y envía al servicio de auditoría. */
        SEND
    }

    private Mode mode = Mode.SEND;

    private boolean requestEvents = true;

    /**
     * Auditar también lecturas (GET). Acá es el caso principal: guarani no escribe, sólo
     * expone personas, alumnos, documentos y propuestas. Viene encendido por defecto.
     */
    private boolean accessEvents = true;

    /** Eventos {@code CHANGE} por escritura de entidad (listeners de Hibernate). */
    private boolean changeEvents = true;

    private String url = "http://localhost:8211";

    private String apiKey = "";

    /** Tope de la cola en memoria: al llenarse se descartan eventos y se cuenta. */
    private int queueCapacity = 2000;

    private int batchSize = 200;

    private int maxAttempts = 10;

    private long senderDelayMs = 2000;

    private long senderInitialDelayMs = 5000;

    /** Payload máximo (before+after) antes de descartar el diff y marcar la truncación. */
    private int maxPayloadChars = 32768;

    /**
     * Campos que se enmascaran en el diff. Guaraní maneja datos personales: documento,
     * contacto y credenciales nunca pueden quedar en texto plano en la auditoría.
     */
    private List<String> maskFields = new ArrayList<>(List.of(
            "password", "clave", "autenticacion", "token", "tokenAlta", "email", "emailTemporal",
            "cbu", "cuenta", "documento", "nroDocumento", "telefono", "telefonoCodigoArea",
            "telefonoNumero", "celular"));

    /** Entidades que nunca se auditan (tablas técnicas). */
    private Set<String> excludedEntities = new HashSet<>();

    /** Sólo se auditan paths que empiecen con alguno de estos prefijos. */
    private List<String> includePrefixes = new ArrayList<>(List.of("/api/tesoreria/guarani"));

    /** Fragments de path que nunca se auditan (health, error, swagger). */
    private List<String> excludeFragments = new ArrayList<>(
            List.of("/actuator", "/error", "/swagger-ui", "/v3/api-docs"));
}
