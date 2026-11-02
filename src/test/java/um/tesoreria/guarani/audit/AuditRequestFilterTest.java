package um.tesoreria.guarani.audit;

import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AuditRequestFilterTest {

    private static final FilterChain NOOP = (request, response) -> {
    };

    private AuditProperties properties;
    private AuditQueue queue;
    private AuditRequestFilter filter;

    @BeforeEach
    void setUp() {
        properties = new AuditProperties();
        properties.setMode(AuditProperties.Mode.SEND);
        properties.setApiKey("test-key");
        properties.setAccessEvents(true);
        queue = new AuditQueue(properties);
        filter = new AuditRequestFilter(properties, queue);
    }

    @Test
    void getDentroDelPrefijoSeAuditaComoAccess() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET",
                "/api/tesoreria/guarani/persona/123");
        request.addHeader("X-User-Id", "42");
        request.addHeader("X-Usuario-Login", "dquinteros");
        request.addHeader("X-Request-Id", "corr-1");
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, NOOP);

        List<AuditQueue.QueuedEvent> events = queue.drain(10);
        assertThat(events).hasSize(1);
        AuditEventPayload payload = events.getFirst().payload();
        assertThat(payload.getService()).isEqualTo("tesoreria-guarani");
        assertThat(payload.getLevel()).isEqualTo("REQUEST");
        assertThat(payload.getOperation()).isEqualTo("ACCESS");
        assertThat(payload.getActorId()).isEqualTo(42L);
        assertThat(payload.getActorType()).isEqualTo("USER");
        assertThat(payload.getActorTrust()).isEqualTo("CLIENT");
        assertThat(payload.getCorrelationId()).isEqualTo("corr-1");
        assertThat(payload.getEndpoint()).isEqualTo("/api/tesoreria/guarani/persona/123");
        assertThat(payload.getOutcome()).isEqualTo("OK");
    }

    @Test
    void getSinIdentidadQuedaComoAnonymous() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET",
                "/api/tesoreria/guarani/alumno/1");

        filter.doFilter(request, new MockHttpServletResponse(), NOOP);

        AuditEventPayload payload = queue.drain(10).getFirst().payload();
        assertThat(payload.getActorType()).isEqualTo("ANONYMOUS");
        assertThat(payload.getActorId()).isNull();
    }

    @Test
    void pathFueraDelPrefijoNoSeAudita() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/haberes/core/algo");

        filter.doFilter(request, new MockHttpServletResponse(), NOOP);

        assertThat(queue.drain(10)).isEmpty();
    }

    @Test
    void swaggerNoSeAudita() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/swagger-ui/index.html");

        filter.doFilter(request, new MockHttpServletResponse(), NOOP);

        assertThat(queue.drain(10)).isEmpty();
    }

    @Test
    void modoOffNoCapturaNada() throws Exception {
        properties.setMode(AuditProperties.Mode.OFF);
        MockHttpServletRequest request = new MockHttpServletRequest("GET",
                "/api/tesoreria/guarani/persona/1");

        filter.doFilter(request, new MockHttpServletResponse(), NOOP);

        assertThat(queue.drain(10)).isEmpty();
    }

    @Test
    void errorHttpSeRegistraComoOutcomeError() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET",
                "/api/tesoreria/guarani/persona/1");
        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(404);

        filter.doFilter(request, response, NOOP);

        assertThat(queue.drain(10).getFirst().payload().getOutcome()).isEqualTo("ERROR");
    }
}
