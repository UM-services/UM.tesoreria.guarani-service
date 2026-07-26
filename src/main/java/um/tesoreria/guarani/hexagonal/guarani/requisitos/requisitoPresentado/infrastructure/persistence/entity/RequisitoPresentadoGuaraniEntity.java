package um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisito.infrastructure.persistence.entity.RequisitoGuaraniEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_requisitos_presentados", schema = "negocio")
public class RequisitoPresentadoGuaraniEntity {

    @Id
    private Integer requisitoPresentado;

    private Integer persona;
    private Integer requisito;
    private LocalDate fechaPresentacion;
    private LocalDate fechaVencimiento;
    private LocalDate fechaAlta;
    private Integer requisitoDigital;
    private String observaciones;

    @OneToOne(optional = true)
    @JoinColumn(name = "requisito", updatable = false, insertable = false)
    private RequisitoGuaraniEntity requisitoRel;

}
