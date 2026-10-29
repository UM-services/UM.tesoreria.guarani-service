package um.tesoreria.guarani.audit;

import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * El diff de guarani toca datos personales: el enmascaramiento no es opcional.
 */
class AuditDiffBuilderTest {

    private AuditProperties properties;
    private AuditDiffBuilder builder;

    @BeforeEach
    void setUp() {
        properties = new AuditProperties();
        builder = new AuditDiffBuilder(properties, JsonMapper.builder().build());
    }

    @Test
    void enmascaraEmailCompletoYDocumentoParcial() {
        var persister = persister("PersonaGuaraniEntity",
                new String[]{"nombres", "email", "nroDocumento"},
                types(3));

        var diff = builder.build(persister,
                new Object[]{"Ana", "ana@um.edu.ar", "30123456"},
                new Object[]{"Ana Maria", "nueva@um.edu.ar", "30123456"},
                AuditDiffBuilder.Operation.UPDATE);

        assertThat(diff.changedFields()).containsExactly("nombres", "email");
        assertThat(diff.after()).containsEntry("nombres", "Ana Maria");
        assertThat(diff.after()).containsEntry("email", "****");
        assertThat(diff.before()).containsEntry("email", "****");
    }

    @Test
    void enmascaraDocumentoConUltimosCuatroDigitos() {
        var persister = persister("PersonaDocumentoGuaraniEntity",
                new String[]{"nroDocumento"},
                types(1));

        var diff = builder.build(persister, new Object[]{"30123456"}, new Object[]{"30123457"},
                AuditDiffBuilder.Operation.UPDATE);

        assertThat(diff.changedFields()).containsExactly("nroDocumento");
        assertThat(diff.before()).containsEntry("nroDocumento", "****3456");
        assertThat(diff.after()).containsEntry("nroDocumento", "****3457");
    }

    @Test
    void enInsertSoloGuardaElEstadoNuevo() {
        var persister = persister("AlumnoGuaraniEntity",
                new String[]{"clave", "legajo"},
                types(2));

        var diff = builder.build(persister, null, new Object[]{"secreta", 123}, AuditDiffBuilder.Operation.INSERT);

        assertThat(diff.changedFields()).containsExactly("clave", "legajo");
        assertThat(diff.before()).isEmpty();
        assertThat(diff.after()).containsEntry("clave", "****");
        assertThat(diff.after()).containsEntry("legajo", 123);
    }

    @Test
    void siElPayloadSuperaElMaximoSeDescartaElContenido() {
        properties.setMaxPayloadChars(20);
        var persister = persister("PersonaGuaraniEntity",
                new String[]{"nombres"},
                types(1));

        var diff = builder.build(persister, new Object[]{"Ana"}, new Object[]{"Ana Maria de los Angeles"},
                AuditDiffBuilder.Operation.UPDATE);

        assertThat(diff.truncated()).isTrue();
        assertThat(diff.changedFields()).containsExactly("nombres");
        assertThat(diff.before()).isEmpty();
        assertThat(diff.after()).isEmpty();
    }

    @Test
    void unUpdateSinCambiosNoGeneraEvento() {
        var persister = persister("PersonaGuaraniEntity",
                new String[]{"nombres"},
                types(1));

        var diff = builder.build(persister, new Object[]{"Ana"}, new Object[]{"Ana"},
                AuditDiffBuilder.Operation.UPDATE);

        assertThat(diff.changedFields()).isEmpty();
    }

    private Type[] types(int count) {
        return IntStream.range(0, count).mapToObj(index -> {
            Type type = mock(Type.class);
            when(type.isAssociationType()).thenReturn(false);
            return type;
        }).toArray(Type[]::new);
    }

    private EntityPersister persister(String entityName, String[] names, Type[] types) {
        EntityPersister persister = mock(EntityPersister.class);
        when(persister.getEntityName()).thenReturn("um.tesoreria.guarani." + entityName);
        when(persister.getPropertyNames()).thenReturn(names);
        when(persister.getPropertyTypes()).thenReturn(types);
        return persister;
    }
}
