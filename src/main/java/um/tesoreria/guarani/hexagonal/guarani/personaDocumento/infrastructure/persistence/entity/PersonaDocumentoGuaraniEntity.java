package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.entity.TipoDocumentoGuaraniEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mdp_personas_documentos", schema = "negocio")
public class PersonaDocumentoGuaraniEntity {

    @Id
    private Integer documento;

    private Integer persona;
    private Short paisDocumento;
    private Short tipoDocumento;

    @OneToOne(optional = true)
    @JoinColumn(name = "tipoDocumento", referencedColumnName = "tipoDocumento", insertable = false, updatable = false)
    private TipoDocumentoGuaraniEntity tipoDocumentoRel;

    private String nroDocumento;
    private LocalDate fechaOtorgamiento;
    private LocalDate fechaVencimiento;

    @Column(columnDefinition = "bpchar")
    private String validadoConRenaper;

}
