package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonaDocumentoGuaraniRepositoryCustomImplTest {

    private static final String DIGITOS = "1234567";

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<PersonaDocumentoGuaraniEntity> criteriaQuery;

    @Mock
    private Root<PersonaDocumentoGuaraniEntity> root;

    @Mock
    private Path<String> nroDocumentoPath;

    @Mock
    private Expression<String> patronExpression;

    @Mock
    private Expression<String> vacioExpression;

    @Mock
    private Expression<String> globalExpression;

    @Mock
    private Expression<String> funcionExpression;

    @Mock
    private ParameterExpression<String> digitosParameter;

    @Mock
    private Predicate predicate;

    @Mock
    private TypedQuery<PersonaDocumentoGuaraniEntity> typedQuery;

    @InjectMocks
    private PersonaDocumentoGuaraniRepositoryCustomImpl repository;

    @Test
    void comparesTheDocumentWithoutItsNonDigitCharacters() {
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(PersonaDocumentoGuaraniEntity.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(PersonaDocumentoGuaraniEntity.class)).thenReturn(root);
        when(root.<String>get("nroDocumento")).thenReturn(nroDocumentoPath);
        when(criteriaBuilder.literal("[^0-9]")).thenReturn(patronExpression);
        when(criteriaBuilder.literal("")).thenReturn(vacioExpression);
        when(criteriaBuilder.literal("g")).thenReturn(globalExpression);
        when(criteriaBuilder.function("regexp_replace", String.class, nroDocumentoPath, patronExpression, vacioExpression, globalExpression))
                .thenReturn(funcionExpression);
        when(criteriaBuilder.parameter(String.class, "digitos")).thenReturn(digitosParameter);
        when(criteriaBuilder.equal(funcionExpression, digitosParameter)).thenReturn(predicate);
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.setParameter("digitos", DIGITOS)).thenReturn(typedQuery);
        var esperado = List.of(PersonaDocumentoGuaraniEntity.builder().documento(10).nroDocumento("AA" + DIGITOS).build());
        when(typedQuery.getResultList()).thenReturn(esperado);

        assertThat(repository.findAllByDigitosNroDocumento(DIGITOS)).isEqualTo(esperado);

        verify(criteriaBuilder).function(eq("regexp_replace"), eq(String.class), eq(nroDocumentoPath),
                eq(patronExpression), eq(vacioExpression), eq(globalExpression));
        verify(typedQuery).setParameter("digitos", DIGITOS);
    }

    @Test
    void doesNotQueryWhenThereAreNoDigits() {
        assertThat(repository.findAllByDigitosNroDocumento("")).isEmpty();
        assertThat(repository.findAllByDigitosNroDocumento(null)).isEmpty();

        verifyNoInteractions(entityManager);
    }
}
