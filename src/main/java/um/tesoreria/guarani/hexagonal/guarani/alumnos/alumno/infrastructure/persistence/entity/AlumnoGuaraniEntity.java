package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.entity.PersonaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuesta.infrastructure.persistence.entity.PropuestaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.entity.PropuestaAspiraGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.ubicacion.infrastructure.persistence.entity.UbicacionGuaraniEntity;

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

    @OneToOne(optional = true)
    @JoinColumn(name = "persona", referencedColumnName = "persona", insertable = false, updatable = false)
    private PersonaGuaraniEntity personaRel;

    @OneToOne(optional = true)
    @JoinColumn(name = "propuesta", referencedColumnName = "propuesta", insertable = false, updatable = false)
    private PropuestaGuaraniEntity propuestaRel;

    @OneToOne(optional = true)
    @JoinColumn(name = "ubicacion", referencedColumnName = "ubicacion", insertable = false, updatable = false)
    private UbicacionGuaraniEntity ubicacionRel;

    @OneToOne(optional = true)
    @JoinColumns({
            @JoinColumn(name = "persona", referencedColumnName = "persona", insertable = false, updatable = false),
            @JoinColumn(name = "propuesta", referencedColumnName = "propuesta", insertable = false, updatable = false)
    })
    private PropuestaAspiraGuaraniEntity propuestaAspiraRel;

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
