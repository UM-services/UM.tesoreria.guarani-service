package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_propuestas_aspira", schema = "negocio")
public class PropuestaAspiraGuaraniEntity {

    @Id
    private Integer propuestaAspira;

    private Integer persona;
    private Integer periodoInsc;
    private BigDecimal anioAcademico;
    private Integer propuesta;
    private Integer planVersion;

    @Column(columnDefinition = "bpchar")
    private String modalidad;

    private Integer ubicacion;
    private LocalDate fechaInscripcion;
    private OffsetDateTime fechaAlta;
    private LocalDate fechaRechazo;
    private Integer tipoIngreso;
    private Integer convenio;

    @Column(columnDefinition = "bpchar")
    private String inscFueraDeTermino;

    private Integer motivoInscripcion;
    private Integer situacionAsp;

    @Column(length = 200)
    private String observaciones;

    private Integer situacionAspAnterior;

    @Column(length = 200)
    private String observacionesRechazo;

    private Integer nroTransaccion;
    private Integer documentoIngreso;

    @Column(length = 36)
    private String sqToken;

    private Short sqSincronizado;
    private Integer datoEstudioAnterior;
    private Short interfaz;

}
