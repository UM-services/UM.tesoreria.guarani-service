package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsableAcademicaGuaraniResponse {

    private Integer responsableAcademica;
    private String nombre;
    private String codigo;
    private Integer responsableAcademicaTipo;
    private Integer institucion;
    private Integer institucionAraucano;
    private String calle;
    private String numero;
    private Integer localidad;
    private String codigoPostal;
    private String telefono;
    private String fax;
    private String email;
    private Integer entidad;
}
