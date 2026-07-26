package um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.domain.model.PersonaContactoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.domain.model.RequisitoPresentadoGuarani;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaGuarani {
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
    private PersonaDocumentoGuarani documentoPrincipalRel;
    private List<PersonaContactoGuarani> contactos;
    private List<RequisitoPresentadoGuarani> requisitosPresentados;
    private String usuario;
    private String clave;
    private LocalDate fechaVencimientoClave;
    private String autenticacion;
    private Short bloqueado;
    private String token;
    private OffsetDateTime tokenAlta;
    private String emailTemporal;
    private Short emailValido;
    private String idImagen;
    private String tipoUsuarioInicial;
    private String pertenecePuebloOriginario;
    private Integer puebloOriginario;
    private String puebloOriginarioOtro;
    private String araiIdentificadorSso;
    private UUID araiUuid;
}
