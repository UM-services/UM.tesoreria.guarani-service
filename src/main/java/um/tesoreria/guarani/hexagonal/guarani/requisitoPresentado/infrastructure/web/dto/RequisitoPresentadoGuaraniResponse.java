package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.requisito.infrastructure.web.dto.RequisitoGuaraniResponse;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoPresentadoGuaraniResponse {
    private Integer requisitoPresentado;
    private Integer persona;
    private Integer requisito;
    private LocalDate fechaPresentacion;
    private LocalDate fechaVencimiento;
    private LocalDate fechaAlta;
    private Integer requisitoDigital;
    private String observaciones;
    private RequisitoGuaraniResponse requisitoRel;
}
