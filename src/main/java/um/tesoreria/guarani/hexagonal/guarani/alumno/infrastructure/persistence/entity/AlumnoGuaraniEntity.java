package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

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
