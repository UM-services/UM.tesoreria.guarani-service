package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model;

import lombok.*;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDocumentoGuarani {
    private Integer documento;
    private Integer persona;
    private Short paisDocumento;
    private Short tipoDocumento;
    private TipoDocumentoGuarani tipoDocumentoRel;
    private String nroDocumento;
    private LocalDate fechaOtorgamiento;
    private LocalDate fechaVencimiento;
    private String validadoConRenaper;
}
