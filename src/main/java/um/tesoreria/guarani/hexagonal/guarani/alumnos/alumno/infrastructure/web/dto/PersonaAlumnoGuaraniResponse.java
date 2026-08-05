package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.infrastructure.web.dto.PersonaContactoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.web.dto.PersonaDocumentoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.infrastructure.web.dto.RequisitoPresentadoGuaraniResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaAlumnoGuaraniResponse {
    private Integer persona;
    private String apellido;
    private String nombres;
    private String apellidoElegido;
    private String nombresElegido;
    private String sexo;
    private Integer identidadGenero;
    private String identidadGeneroOtro;
    private LocalDate fechaNacimiento;
    private Integer localidadNacimiento;
    private Short nacionalidad;
    private LocalDate fechaIngresoPais;
    private Integer paisOrigen;
    private Integer documentoPrincipal;
    private PersonaDocumentoGuaraniResponse documentoPrincipalRel;
    private List<PersonaContactoGuaraniResponse> contactos;
    private List<RequisitoPresentadoGuaraniResponse> requisitosPresentados;
    private String emailTemporal;
    private Short emailValido;
    private String idImagen;
    private String tipoUsuarioInicial;
    private String pertenecePuebloOriginario;
    private Integer puebloOriginario;
    private String puebloOriginarioOtro;
    private String araiIdentificadorSso;
}
