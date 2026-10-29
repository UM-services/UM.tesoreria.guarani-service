package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.application.service.CursadaGuaraniService;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.PeriodoCursada;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.web.mapper.CursadaGuaraniDtoMapper;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CursadaGuaraniControllerTest {

    private static final String RUTA = "/api/tesoreria/guarani/cursada/documento/{nroDocumento}/anioAcademico/{anioAcademico}";

    @Mock
    private CursadaGuaraniService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CursadaGuaraniController(service, new CursadaGuaraniDtoMapper())).build();
    }

    /*
     * protects: la ruta del contrato de #63 y el paso de documento y año al service.
     * fails_when: se cambia la ruta, o el documento y el año llegan cruzados o con otro tipo.
     * no_claim: no prueba el filtro de auditoría ni el gateway.
     */
    @Test
    void routesDocumentAndYearToTheService() throws Exception {
        when(service.getAllByNroDocumentoAndAnioAcademico("99999999", 2026)).thenReturn(List.of());

        mockMvc.perform(get(RUTA, "99999999", 2026)).andExpect(status().isOk());

        verify(service).getAllByNroDocumentoAndAnioAcademico("99999999", 2026);
    }

    /*
     * protects: sin cursadas la respuesta es 200 con [] y no 404.
     * fails_when: el controller devuelve 404, 204 o un cuerpo distinto de [].
     * no_claim: no prueba el caso de documento inexistente contra la base.
     */
    @Test
    void returnsOkWithAnEmptyArrayWhenThereAreNoCursadas() throws Exception {
        when(service.getAllByNroDocumentoAndAnioAcademico("99999999", 1900)).thenReturn(List.of());

        mockMvc.perform(get(RUTA, "99999999", 1900))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
    }

    /*
     * protects: los 11 nombres de campo del contrato y que periodo sale como texto del enum; inscripcion no sale.
     * fails_when: se renombra un campo, periodo sale como número, o se filtra el id de inscripción.
     * no_claim: no prueba valores reales de Guaraní (los datos son sintéticos).
     */
    @Test
    void serializesTheContractShape() throws Exception {
        var cursada = CursadaGuarani.builder().inscripcion(11).responsableAcademica(12).propuesta(345)
                .propuestaNombre("Propuesta sintética").planVersion(7).actividadCodigo("ACT-1").actividadNombre("Actividad sintética")
                .anioPlan(1).periodo(PeriodoCursada.SEGUNDO_SEMESTRE).periodoLectivo(4321).periodoNombre("2do Cuatrimestre")
                .anioCursada((short) 2).build();
        when(service.getAllByNroDocumentoAndAnioAcademico("99999999", 2026)).thenReturn(List.of(cursada));

        mockMvc.perform(get(RUTA, "99999999", 2026))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"responsableAcademica":12,"propuesta":345,"propuestaNombre":"Propuesta sintética","planVersion":7,
                          "actividadCodigo":"ACT-1","actividadNombre":"Actividad sintética","anioPlan":1,"periodo":"SEGUNDO_SEMESTRE",
                          "periodoLectivo":4321,"periodoNombre":"2do Cuatrimestre","anioCursada":2}]
                        """, true))
                .andExpect(jsonPath("$[0].inscripcion").doesNotExist());
    }

    /*
     * protects: anioPlan y anioCursada nulos salen como null en el JSON, no se omiten ni fallan.
     * fails_when: el campo falta del JSON o el mapeo lanza NullPointerException.
     * no_claim: no define si el consumidor B2 acepta null (está en el contrato de #63).
     */
    @Test
    void serializesNullAnioPlanAndAnioCursadaAsNull() throws Exception {
        var cursada = CursadaGuarani.builder().inscripcion(11).propuesta(345).planVersion(7).actividadCodigo("ACT-1")
                .periodo(PeriodoCursada.OTRO).periodoLectivo(4321).build();
        when(service.getAllByNroDocumentoAndAnioAcademico("99999999", 2026)).thenReturn(List.of(cursada));

        mockMvc.perform(get(RUTA, "99999999", 2026))
                .andExpect(jsonPath("$[0].anioPlan").value((Object) null))
                .andExpect(jsonPath("$[0].anioCursada").value((Object) null))
                .andExpect(jsonPath("$[0].responsableAcademica").value((Object) null));
    }
}
