package um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mdp_personas", schema = "negocio")
public class PersonaGuaraniEntity {

    @Id
    private Integer persona;

    private String apellido;
    private String nombres;
    private String apellidoElegido;
    private String nombresElegido;

    @Column(columnDefinition = "bpchar")
    private String sexo;

    private Integer identidadGenero;
    private String identidadGeneroOtro;
    private LocalDate fechaNacimiento;
    private Integer localidadNacimiento;
    private Short nacionalidad;
    private LocalDate fechaIngresoPais;
    private Integer paisOrigen;
    private Integer documentoPrincipal;
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

    @Column(columnDefinition = "bpchar")
    private String pertenecePuebloOriginario;

    private Integer puebloOriginario;
    private String puebloOriginarioOtro;
    private String araiIdentificadorSso;
    private UUID araiUuid;

}
