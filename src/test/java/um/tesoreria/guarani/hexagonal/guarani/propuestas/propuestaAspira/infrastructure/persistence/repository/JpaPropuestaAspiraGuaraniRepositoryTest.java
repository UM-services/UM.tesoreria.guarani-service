package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.entity.PropuestaAspiraGuaraniEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JpaPropuestaAspiraGuaraniRepositoryTest {

    private static final String QUERY =
            "findAllByPropuestaAndUbicacionAndFechaInscripcionGreaterThanEqualAndAnioAcademico";

    @Test
    void derivesFourCriteriaFromExistingEntityProperties() {
        PartTree tree = new PartTree(QUERY, PropuestaAspiraGuaraniEntity.class);
        List<Part> parts = tree.getParts().stream().toList();

        assertThat(parts).extracting(part -> part.getProperty().toDotPath())
                .containsExactly("propuesta", "ubicacion", "fechaInscripcion", "anioAcademico");
        assertThat(parts).extracting(Part::getType)
                .containsExactly(Part.Type.SIMPLE_PROPERTY, Part.Type.SIMPLE_PROPERTY,
                        Part.Type.GREATER_THAN_EQUAL, Part.Type.SIMPLE_PROPERTY);
    }

    @Test
    void criteriaTypesMatchJpaRepositorySignature() {
        List<String> types = new PartTree(QUERY, PropuestaAspiraGuaraniEntity.class)
                .getParts()
                .stream()
                .map(part -> part.getProperty().getType().getName())
                .toList();

        assertThat(types).containsExactly(
                Integer.class.getName(),
                Integer.class.getName(),
                LocalDate.class.getName(),
                BigDecimal.class.getName());
    }
}
