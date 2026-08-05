package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.entity.PersonaGuaraniEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_alumnos", schema = "negocio")
public class AlumnoGuaraniEntity {

    @Id
    private Integer alumno;

    private String legajo;
    private Integer persona;
    private Integer propuesta;
    private Integer planVersion;
    private Integer ubicacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "persona", referencedColumnName = "persona", insertable = false, updatable = false)
    private PersonaGuaraniEntity personaRel;

    @Column(columnDefinition = "bpchar")
    private String modalidad;
    private Integer division;
    private Short anioCursada;

    @Column(name = "cnt_readmisiones")
    private Short cantidadReadmisiones;

    @Column(columnDefinition = "bpchar")
    private String regular;
    @Column(columnDefinition = "bpchar")
    private String calidad;
    private BigDecimal coeficiente;

}
