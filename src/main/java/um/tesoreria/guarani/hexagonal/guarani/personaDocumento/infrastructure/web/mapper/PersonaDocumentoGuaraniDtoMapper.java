package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.web.dto.PersonaDocumentoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.mapper.TipoDocumentoGuaraniDtoMapper;

@Component
public class PersonaDocumentoGuaraniDtoMapper {

    private final TipoDocumentoGuaraniDtoMapper tipoDocumentoDtoMapper;

    public PersonaDocumentoGuaraniDtoMapper(TipoDocumentoGuaraniDtoMapper tipoDocumentoDtoMapper) {
        this.tipoDocumentoDtoMapper = tipoDocumentoDtoMapper;
    }

    public PersonaDocumentoGuaraniResponse toResponse(PersonaDocumentoGuarani domain) {
        if (domain == null) return null;
        return PersonaDocumentoGuaraniResponse.builder()
                .documento(domain.getDocumento())
                .persona(domain.getPersona())
                .paisDocumento(domain.getPaisDocumento())
                .tipoDocumento(domain.getTipoDocumento())
                .tipoDocumentoRel(tipoDocumentoDtoMapper.toResponse(domain.getTipoDocumentoRel()))
                .nroDocumento(domain.getNroDocumento())
                .fechaOtorgamiento(domain.getFechaOtorgamiento())
                .fechaVencimiento(domain.getFechaVencimiento())
                .validadoConRenaper(domain.getValidadoConRenaper())
                .build();
    }
}
