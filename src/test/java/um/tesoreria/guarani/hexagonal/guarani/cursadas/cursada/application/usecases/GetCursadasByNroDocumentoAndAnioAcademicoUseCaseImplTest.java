package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.application.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.ports.in.GetAlumnosByNroDocumentoUseCase;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.out.CursadaGuaraniRepository;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCursadasByNroDocumentoAndAnioAcademicoUseCaseImplTest {

    private static final String DOCUMENTO = "99999999";
    private static final Integer ANIO = 2026;

    @Mock
    private GetAlumnosByNroDocumentoUseCase getAlumnosByNroDocumentoUseCase;

    @Mock
    private CursadaGuaraniRepository repository;

    @InjectMocks
    private GetCursadasByNroDocumentoAndAnioAcademicoUseCaseImpl useCase;

    /*
     * protects: un documento sin alumnos da lista vacía y no consulta cursadas.
     * fails_when: el use case lanza excepción, devuelve null o llama al repositorio con un conjunto vacío.
     * no_claim: no prueba la normalización del documento (es del use case de alumno).
     */
    @Test
    void returnsEmptyWhenTheDocumentHasNoAlumnos() {
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO)).thenReturn(List.of());

        assertThat(useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO)).isEmpty();

        verifyNoInteractions(repository);
    }

    /*
     * protects: una persona sin alumno (alumno == null) se descarta y no llega al repositorio.
     * fails_when: se consulta con un id nulo, o se devuelve algo para esa persona.
     * no_claim: no prueba qué personas sin alumno devuelve el use case de alumno.
     */
    @Test
    void discardsPersonasWithoutAlumno() {
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO))
                .thenReturn(List.of(AlumnoGuarani.builder().persona(4500).build()));

        assertThat(useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO)).isEmpty();

        verifyNoInteractions(repository);
    }

    /*
     * protects: un alumno sin cursadas en el año da 200 [] (lista vacía), no un error.
     * fails_when: el use case lanza excepción o devuelve null cuando el repositorio devuelve vacío.
     * no_claim: no prueba el filtro por año ni por estado (es del adaptador).
     */
    @Test
    void returnsEmptyWhenTheAlumnoHasNoCursadasInTheYear() {
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO))
                .thenReturn(List.of(AlumnoGuarani.builder().alumno(1).persona(4500).build()));
        when(repository.findAllByAlumnoInAndAnioAcademico(Set.of(1), ANIO)).thenReturn(List.of());

        assertThat(useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO)).isEmpty();
    }

    /*
     * protects: se consultan todos los alumnos de la persona, sin repetir ids, y el año pedido.
     * fails_when: se consulta solo el primer alumno, o se pasan ids repetidos o nulos.
     * no_claim: no prueba qué devuelve el repositorio.
     */
    @Test
    void queriesEveryAlumnoOnce() {
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO)).thenReturn(List.of(
                AlumnoGuarani.builder().alumno(1).persona(4500).build(),
                AlumnoGuarani.builder().alumno(2).persona(4500).build(),
                AlumnoGuarani.builder().alumno(2).persona(4500).build(),
                AlumnoGuarani.builder().persona(4600).build()));
        when(repository.findAllByAlumnoInAndAnioAcademico(any(), any())).thenReturn(List.of());

        useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO);

        verify(repository).findAllByAlumnoInAndAnioAcademico(Set.of(1, 2), ANIO);
    }

    /*
     * protects: una sola fila por (propuesta, planVersion, actividadCodigo, periodoLectivo); gana la inscripción de menor id.
     * fails_when: se devuelven las dos filas, o gana la de mayor id según el orden de entrada.
     * no_claim: no prueba el WARN ni que la base produzca claves repetidas (en develop no hay).
     */
    @Test
    void keepsTheLowestInscripcionWhenTheKeyRepeats() {
        stubAlumno();
        when(repository.findAllByAlumnoInAndAnioAcademico(any(), any())).thenReturn(List.of(
                cursada(30, 10, 1, "ACT-1", 5),
                cursada(20, 10, 1, "ACT-1", 5),
                cursada(40, 10, 1, "ACT-1", 5)));

        var resultado = useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO);

        assertThat(resultado).extracting(CursadaGuarani::getInscripcion).containsExactly(20);
    }

    /*
     * protects: claves que difieren en un solo componente (plan, período lectivo, propuesta) se conservan todas.
     * fails_when: la clave omite un componente y se pierde una cursada válida.
     * no_claim: no prueba cursadas de un mismo código en distintas propuestas con la misma versión de plan.
     */
    @Test
    void keepsRowsThatDifferInAnyKeyComponent() {
        stubAlumno();
        when(repository.findAllByAlumnoInAndAnioAcademico(any(), any())).thenReturn(List.of(
                cursada(1, 10, 1, "ACT-1", 5),
                cursada(2, 10, 2, "ACT-1", 5),
                cursada(3, 10, 1, "ACT-1", 6),
                cursada(4, 11, 1, "ACT-1", 5)));

        assertThat(useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO)).hasSize(4);
    }

    /*
     * protects: el orden es propuesta, planVersion, actividadCodigo, periodoLectivo, con nulos al final.
     * fails_when: el orden depende del orden de entrada del repositorio, o un código nulo rompe el orden.
     * no_claim: no prueba el orden con más de un alumno.
     */
    @Test
    void ordersByPropuestaPlanVersionCodigoAndPeriodoLectivo() {
        stubAlumno();
        when(repository.findAllByAlumnoInAndAnioAcademico(any(), any())).thenReturn(List.of(
                cursada(1, 11, 1, "ACT-1", 5),
                cursada(2, 10, 2, "ACT-1", 5),
                cursada(3, 10, 1, null, 5),
                cursada(4, 10, 1, "ACT-2", 5),
                cursada(5, 10, 1, "ACT-1", 6),
                cursada(6, 10, 1, "ACT-1", 5)));

        var resultado = useCase.getByNroDocumentoAndAnioAcademico(DOCUMENTO, ANIO);

        assertThat(resultado).extracting(CursadaGuarani::getInscripcion).containsExactly(6, 5, 4, 3, 2, 1);
    }

    private void stubAlumno() {
        when(getAlumnosByNroDocumentoUseCase.getByNroDocumento(DOCUMENTO))
                .thenReturn(List.of(AlumnoGuarani.builder().alumno(1).persona(4500).build()));
    }

    private static CursadaGuarani cursada(int inscripcion, int propuesta, int planVersion, String codigo, int periodoLectivo) {
        return CursadaGuarani.builder().inscripcion(inscripcion).propuesta(propuesta).planVersion(planVersion)
                .actividadCodigo(codigo).periodoLectivo(periodoLectivo).build();
    }
}
