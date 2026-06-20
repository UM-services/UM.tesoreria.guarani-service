package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_propuestas_tipos", schema = "negocio")
public class PropuestaTipoGuaraniEntity {

    @Id
    private Integer propuestaTipo;

    private String descripcion;

    @Column(columnDefinition = "bpchar")
    private String otorgaTitulo;

    @Column(columnDefinition = "bpchar")
    private String reportaAraucano;

    @Column(columnDefinition = "bpchar")
    private String permiteMatricular;

    @Column(columnDefinition = "bpchar")
    private String permiteInscribir;

    @Column(columnDefinition = "bpchar")
    private String controlRegularidad;

    @Column(columnDefinition = "bpchar")
    private String disponibleEnAutogestion;

    @Column(columnDefinition = "bpchar")
    private String informarMovilidadInternacional;

}
