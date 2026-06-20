package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.web.dto;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.dto.TipoDocumentoGuaraniResponse;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDocumentoGuaraniResponse {
    private Integer documento;
    private Integer persona;
    private Short paisDocumento;
    private Short tipoDocumento;
    private TipoDocumentoGuaraniResponse tipoDocumentoRel;
    private String nroDocumento;
    private LocalDate fechaOtorgamiento;
    private LocalDate fechaVencimiento;
    private String validadoConRenaper;
}
