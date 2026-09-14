package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonaDocumentoGuaraniRepositoryCustomImpl implements PersonaDocumentoGuaraniRepositoryCustom {

    private static final String PATRON_NO_DIGITO = "[^0-9]";

    private final EntityManager entityManager;

    @Override
    public List<PersonaDocumentoGuaraniEntity> findAllByDigitosNroDocumento(String digitos) {
        if (digitos == null || digitos.isEmpty()) {
            return List.of();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersonaDocumentoGuaraniEntity> query = criteriaBuilder.createQuery(PersonaDocumentoGuaraniEntity.class);
        Root<PersonaDocumentoGuaraniEntity> root = query.from(PersonaDocumentoGuaraniEntity.class);

        query.select(root).where(criteriaBuilder.equal(
                criteriaBuilder.function("regexp_replace", String.class,
                        root.<String>get("nroDocumento"),
                        criteriaBuilder.literal(PATRON_NO_DIGITO),
                        criteriaBuilder.literal(""),
                        criteriaBuilder.literal("g")),
                criteriaBuilder.parameter(String.class, "digitos")));

        return entityManager.createQuery(query).setParameter("digitos", digitos).getResultList();
    }
}
