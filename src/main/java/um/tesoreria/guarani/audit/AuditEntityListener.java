package um.tesoreria.guarani.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Captura las escrituras de guarani a través del ciclo de vida de JPA.
 *
 * <p>Guarani todavía no escribe, pero el circuito queda armado: cuando alguna operación haga
 * {@code save}/{@code delete} sobre una entidad, se emite un {@code CHANGE} con el diff
 * enmascarado.</p>
 *
 * <p>Sin outbox, la única forma de no auditar cambios que terminan en rollback es publicar en
 * {@code afterCommit}. Si no hay transacción activa (caso raro), se publica en el acto.</p>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditEntityListener implements PostInsertEventListener,
        PostUpdateEventListener, PostDeleteEventListener {

    private final AuditProperties properties;
    private final AuditDiffBuilder diffBuilder;
    private final AuditEventPublisher publisher;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        handle(event.getEntity(), event.getId(), event.getPersister(), null, event.getState(),
                AuditDiffBuilder.Operation.INSERT);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        handle(event.getEntity(), event.getId(), event.getPersister(), event.getOldState(),
                event.getState(), AuditDiffBuilder.Operation.UPDATE);
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        handle(event.getEntity(), event.getId(), event.getPersister(), event.getDeletedState(),
                null, AuditDiffBuilder.Operation.DELETE);
    }

    private void handle(Object entity, Object id, EntityPersister persister,
                        Object[] beforeState, Object[] afterState,
                        AuditDiffBuilder.Operation operation) {
        if (properties.getMode() == AuditProperties.Mode.OFF || !properties.isChangeEvents()) {
            return;
        }

        String entityName = simpleName(persister.getEntityName());
        if (properties.getExcludedEntities().contains(entityName)) {
            return;
        }

        AuditDiffBuilder.DiffResult diff = diffBuilder.build(persister, beforeState, afterState, operation);
        if (diff.changedFields().isEmpty()) {
            return;
        }

        Map<String, Object> metadata = new LinkedHashMap<>();
        metadata.put("entityName", persister.getEntityName());
        metadata.put("table", safeTable(persister));
        if (diff.truncated()) {
            metadata.put("truncated", true);
        }

        AuditEventPayload payload = publisher.changePayload(
                entityName, id == null ? null : id.toString(), operation, diff, metadata);

        AuditContext.incrementChanges();
        publishAfterCommit(payload);
    }

    private void publishAfterCommit(AuditEventPayload payload) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    publisher.publish(payload);
                }
            });
        } else {
            publisher.publish(payload);
        }
    }

    private String safeTable(EntityPersister persister) {
        try {
            return persister.getTableName();
        } catch (RuntimeException e) {
            return persister.getEntityName();
        }
    }

    private String simpleName(String entityName) {
        int index = entityName.lastIndexOf('.');
        return index < 0 ? entityName : entityName.substring(index + 1);
    }
}
