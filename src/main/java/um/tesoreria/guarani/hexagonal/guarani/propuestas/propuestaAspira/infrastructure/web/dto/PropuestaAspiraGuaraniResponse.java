package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaAspira.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.web.dto.PersonaGuaraniResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropuestaAspiraGuaraniResponse {
    private Integer propuestaAspira;
    private Integer persona;
    private PersonaGuaraniResponse personaRel;
    private Integer periodoInsc;
    private BigDecimal anioAcademico;
    private Integer propuesta;
    private Integer planVersion;
    private String modalidad;
    private Integer ubicacion;
    private LocalDate fechaInscripcion;
    private OffsetDateTime fechaAlta;
    private LocalDate fechaRechazo;
    private Integer tipoIngreso;
    private Integer convenio;
    private String inscFueraDeTermino;
    private Integer motivoInscripcion;
    private Integer situacionAsp;
    private String observaciones;
    private Integer situacionAspAnterior;
    private String observacionesRechazo;
    private Integer nroTransaccion;
    private Integer documentoIngreso;
    private String sqToken;
    private Short sqSincronizado;
    private Integer datoEstudioAnterior;
    private Short interfaz;
}
