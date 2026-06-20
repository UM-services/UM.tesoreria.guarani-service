package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.web.dto.TipoDocumentoGuaraniResponse;

@Component
public class TipoDocumentoGuaraniDtoMapper {

    public TipoDocumentoGuaraniResponse toResponse(TipoDocumentoGuarani domain) {
        if (domain == null) return null;
        return TipoDocumentoGuaraniResponse.builder()
                .tipoDocumento(domain.getTipoDocumento())
                .descripcion(domain.getDescripcion())
                .descAbreviada(domain.getDescAbreviada())
                .ordenPrincipal(domain.getOrdenPrincipal())
                .habilitaInscripcion(domain.getHabilitaInscripcion())
                .tipoDeDato(domain.getTipoDeDato())
                .puedeEliminarse(domain.getPuedeEliminarse())
                .expRegularValidacion(domain.getExpRegularValidacion())
                .expRegularMensaje(domain.getExpRegularMensaje())
                .build();
    }
}
