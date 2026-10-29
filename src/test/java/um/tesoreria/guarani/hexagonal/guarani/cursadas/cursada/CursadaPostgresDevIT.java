package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.application.service.CursadaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.out.CursadaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity.InscripcionCursadaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.repository.JpaInscripcionCursadaGuaraniRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Prueba de mapeo y consultas contra el Postgres de desarrollo de Guaraní. Solo lectura y opt-in.
 *
 * <p>Corrida: {@code GUARANI_DEV_IT=true ./mvnw -Dtest=CursadaPostgresDevIT test}, con {@code APP_SERVER},
 * {@code APP_DATABASE}, {@code APP_USERNAME} y {@code APP_PASSWORD} en el entorno. Para una guarda del lado
 * del servidor, agregar {@code options=-c default_transaction_read_only=on} a la URL con
 * {@code SPRING_DATASOURCE_URL}. No corre en CI: surefire solo toma {@code *Test}.
 *
 * <p>Los mensajes de aserción no incluyen documentos ni nombres.
 */
@SpringBootTest(properties = {
        "app.testing=true",
        "spring.datasource.hikari.read-only=true",
        "spring.cloud.consul.enabled=false",
        "spring.cloud.discovery.enabled=false",
        "tesoreria.audit.mode=OFF"
})
@EnabledIfEnvironmentVariable(named = "GUARANI_DEV_IT", matches = "true")
@Transactional(readOnly = true)
class CursadaPostgresDevIT {

    private static final int PAGINA = 500;

    @Autowired
    private JpaInscripcionCursadaGuaraniRepository jpaInscripcionRepository;

    @Autowired
    private CursadaGuaraniRepository repository;

    @Autowired
    private CursadaGuaraniService service;

    /*
     * protects: las 8 entidades validan contra el esquema (ddl-auto: validate), y la consulta del adaptador devuelve,
     *            para un alumno con una inscripción aceptada elegido en la misma base, esa cursada (resultado no vacío).
     * fails_when: falta una columna o tabla, el filtro de estado o de año académico no coincide con Postgres,
     *             o la inscripción elegida no aparece en el resultado.
     * no_claim: no prueba cursadas de grado (no existen en develop), ni estado P, ni bajas (no se puede escribir),
     *           ni otros ambientes.
     */
    @Test
    void adapterReturnsTheChosenAcceptedInscripcion() {
        var elegida = elegirInscripcionAceptada();

        List<CursadaGuarani> cursadas = repository.findAllByAlumnoInAndAnioAcademico(
                Set.of(elegida.getAlumno()), anioAcademicoDe(elegida));

        assertThat(cursadas).isNotEmpty();
        assertThat(cursadas).anyMatch(c -> elegida.getInscripcion().equals(c.getInscripcion()));
        assertThat(cursadas).allMatch(c -> c.getPeriodo() != null && c.getPeriodoLectivo() != null);
    }

    /*
     * protects: de punta a punta (sin core ni gateway), un documento real devuelve cursadas sin claves repetidas,
     *            un año sin cursadas devuelve [] y un documento inexistente devuelve [].
     * fails_when: el resultado viene vacío para un documento con cursadas, hay dos filas con la misma clave,
     *             o los casos sin coincidencia lanzan excepción o devuelven filas.
     * no_claim: no prueba el controller ni el JSON (lo cubren el test MockMvc y el curl), ni la normalización
     *           por dígitos con documentos que mezclan personas.
     */
    @Test
    void serviceReturnsRowsWithoutRepeatedKeysAndEmptyListsWhenThereIsNoMatch() {
        var elegida = elegirInscripcionAceptada();
        String documento = documentoDe(elegida);
        int anio = anioAcademicoDe(elegida);

        var cursadas = service.getAllByNroDocumentoAndAnioAcademico(documento, anio);

        assertThat(cursadas).isNotEmpty();
        assertThat(cursadas).anyMatch(c -> elegida.getComisionRel().getPeriodoLectivo().equals(c.getPeriodoLectivo()));
        var claves = cursadas.stream()
                .map(c -> List.of(String.valueOf(c.getPropuesta()), String.valueOf(c.getPlanVersion()),
                        String.valueOf(c.getActividadCodigo()), String.valueOf(c.getPeriodoLectivo())))
                .collect(Collectors.toSet());
        assertThat(claves).hasSameSizeAs(cursadas);

        assertThat(service.getAllByNroDocumentoAndAnioAcademico(documento, 1900)).isEmpty();
        assertThat(service.getAllByNroDocumentoAndAnioAcademico("DOCUMENTO-INEXISTENTE", anio)).isEmpty();
    }

    // Elige, en la misma base, una inscripción aceptada reciente cuyo alumno tiene documento.
    private InscripcionCursadaGuaraniEntity elegirInscripcionAceptada() {
        var pagina = jpaInscripcionRepository.findAll(PageRequest.of(0, PAGINA, Sort.by(Sort.Direction.DESC, "inscripcion")));
        return pagina.stream()
                .filter(i -> "A".equals(i.getEstado()))
                .filter(i -> i.getAlumnoRel().getPersonaRel() != null
                        && i.getAlumnoRel().getPersonaRel().getDocumentoPrincipalRel() != null
                        && i.getAlumnoRel().getPersonaRel().getDocumentoPrincipalRel().getNroDocumento() != null)
                .findFirst()
                .orElseThrow(() -> new AssertionError("No hay una inscripción aceptada con documento en las últimas " + PAGINA));
    }

    private static int anioAcademicoDe(InscripcionCursadaGuaraniEntity inscripcion) {
        return inscripcion.getComisionRel().getPeriodoLectivoRel().getPeriodoRel().getAnioAcademico().intValueExact();
    }

    private static String documentoDe(InscripcionCursadaGuaraniEntity inscripcion) {
        return inscripcion.getAlumnoRel().getPersonaRel().getDocumentoPrincipalRel().getNroDocumento();
    }
}
