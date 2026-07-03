package um.tesoreria.guarani.hexagonal.guarani.requisitoPresentado.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.requisito.domain.model.RequisitoGuarani;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequisitoPresentadoGuarani {
    private Integer requisitoPresentado;
    private Integer persona;
    private Integer requisito;
    private LocalDate fechaPresentacion;
    private LocalDate fechaVencimiento;
    private LocalDate fechaAlta;
    private Integer requisitoDigital;
    private String observaciones;
    private RequisitoGuarani requisitoRel;
}
