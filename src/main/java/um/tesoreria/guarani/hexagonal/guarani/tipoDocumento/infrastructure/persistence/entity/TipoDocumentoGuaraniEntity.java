package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mdp_tipo_documento", schema = "negocio")
public class TipoDocumentoGuaraniEntity {

    @Id
    private Short tipoDocumento;

    private String descripcion;

    private String descAbreviada;

    @Column(name = "orden_principal")
    private Integer ordenPrincipal;

    @Column(columnDefinition = "bpchar")
    private String habilitaInscripcion;

    @Column(name = "tipo_de_dato", columnDefinition = "bpchar")
    private String tipoDeDato;

    @Column(columnDefinition = "bpchar")
    private String puedeEliminarse;

    private String expRegularValidacion;

    private String expRegularMensaje;

}
