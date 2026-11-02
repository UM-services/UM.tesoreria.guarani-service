package um.tesoreria.guarani.audit;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.stereotype.Component;

/**
 * Registra los listeners de escritura en la SessionFactory ya construida.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditEventListenerRegistrar {

    private final EntityManagerFactory entityManagerFactory;
    private final AuditEntityListener listener;
    private final AuditProperties properties;

    @PostConstruct
    void register() {
        SessionFactoryImplementor factory = entityManagerFactory.unwrap(SessionFactoryImplementor.class);
        EventListenerRegistry registry = factory.getEventListenerRegistry();
        registry.appendListeners(EventType.POST_INSERT, listener);
        registry.appendListeners(EventType.POST_UPDATE, listener);
        registry.appendListeners(EventType.POST_DELETE, listener);
        log.info("Auditoría Guaraní: listeners de escritura registrados (mode={}, level change={})",
                properties.getMode(), properties.isChangeEvents());
    }
}
