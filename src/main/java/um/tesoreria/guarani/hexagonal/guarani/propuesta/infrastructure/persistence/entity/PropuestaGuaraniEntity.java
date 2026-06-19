package um.tesoreria.guarani.hexagonal.guarani.propuesta.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_propuestas", schema = "negocio")
public class PropuestaGuaraniEntity {

    @Id
    private Integer propuesta;

    private String nombre;
    private String nombreAbreviado;
    private String codigo;
    private Integer propuestaTipo;

    @Column(columnDefinition = "bpchar")
    private String publica;

    private Integer documentoAlta;
    private LocalDate fechaAlta;
    private Integer campoDisciplinar;
    private Integer escalaCumplimiento;
    private Integer documentoBaja;
    private LocalDate fechaBaja;
    private Short aTermino;
    private Integer entidad;

    @Column(columnDefinition = "bpchar")
    private String estado;

}
