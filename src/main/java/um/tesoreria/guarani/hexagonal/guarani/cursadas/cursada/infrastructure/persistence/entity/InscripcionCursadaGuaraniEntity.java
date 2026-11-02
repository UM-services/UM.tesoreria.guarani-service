package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity.AlumnoGuaraniEntity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_insc_cursada", schema = "negocio")
public class InscripcionCursadaGuaraniEntity {

    @Id
    private Integer inscripcion;

    private Integer comision;
    private Integer alumno;
    private Integer planVersion;

    @Column(columnDefinition = "bpchar")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comision", insertable = false, updatable = false)
    private ComisionGuaraniEntity comisionRel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alumno", insertable = false, updatable = false)
    private AlumnoGuaraniEntity alumnoRel;

}
