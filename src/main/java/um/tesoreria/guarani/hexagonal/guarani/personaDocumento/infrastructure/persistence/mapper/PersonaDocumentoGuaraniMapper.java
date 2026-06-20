package um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.domain.model.PersonaDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.mapper.TipoDocumentoGuaraniMapper;

@Component
public class PersonaDocumentoGuaraniMapper {

    private final TipoDocumentoGuaraniMapper tipoDocumentoMapper;

    public PersonaDocumentoGuaraniMapper(TipoDocumentoGuaraniMapper tipoDocumentoMapper) {
        this.tipoDocumentoMapper = tipoDocumentoMapper;
    }

    public PersonaDocumentoGuaraniEntity toEntity(PersonaDocumentoGuarani domain) {
        if (domain == null) return null;
        return PersonaDocumentoGuaraniEntity.builder()
                .documento(domain.getDocumento())
                .persona(domain.getPersona())
                .paisDocumento(domain.getPaisDocumento())
                .tipoDocumento(domain.getTipoDocumento())
                .tipoDocumentoRel(tipoDocumentoMapper.toEntity(domain.getTipoDocumentoRel()))
                .nroDocumento(domain.getNroDocumento())
                .fechaOtorgamiento(domain.getFechaOtorgamiento())
                .fechaVencimiento(domain.getFechaVencimiento())
                .validadoConRenaper(domain.getValidadoConRenaper())
                .build();
    }

    public PersonaDocumentoGuarani toDomain(PersonaDocumentoGuaraniEntity entity) {
        if (entity == null) return null;
        return PersonaDocumentoGuarani.builder()
                .documento(entity.getDocumento())
                .persona(entity.getPersona())
                .paisDocumento(entity.getPaisDocumento())
                .tipoDocumento(entity.getTipoDocumento())
                .tipoDocumentoRel(tipoDocumentoMapper.toDomain(entity.getTipoDocumentoRel()))
                .nroDocumento(entity.getNroDocumento())
                .fechaOtorgamiento(entity.getFechaOtorgamiento())
                .fechaVencimiento(entity.getFechaVencimiento())
                .validadoConRenaper(entity.getValidadoConRenaper())
                .build();
    }
}
