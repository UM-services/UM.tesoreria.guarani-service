package um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.persistence.entity.PersonaContactoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
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

    @OneToOne(optional = true)
    @JoinColumn(name = "documentoPrincipal", referencedColumnName = "documento", insertable = false, updatable = false)
    private PersonaDocumentoGuaraniEntity documentoPrincipalRel;

    @OneToMany
    @JoinColumn(name = "persona", referencedColumnName = "persona")
    private List<PersonaContactoGuaraniEntity> contactos;

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
