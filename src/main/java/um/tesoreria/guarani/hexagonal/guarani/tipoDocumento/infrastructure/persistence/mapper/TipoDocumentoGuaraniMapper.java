package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.domain.model.TipoDocumentoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.entity.TipoDocumentoGuaraniEntity;

@Component
public class TipoDocumentoGuaraniMapper {

    public TipoDocumentoGuaraniEntity toEntity(TipoDocumentoGuarani domain) {
        if (domain == null) return null;
        return TipoDocumentoGuaraniEntity.builder()
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

    public TipoDocumentoGuarani toDomain(TipoDocumentoGuaraniEntity entity) {
        if (entity == null) return null;
        return TipoDocumentoGuarani.builder()
                .tipoDocumento(entity.getTipoDocumento())
                .descripcion(entity.getDescripcion())
                .descAbreviada(entity.getDescAbreviada())
                .ordenPrincipal(entity.getOrdenPrincipal())
                .habilitaInscripcion(entity.getHabilitaInscripcion())
                .tipoDeDato(entity.getTipoDeDato())
                .puedeEliminarse(entity.getPuedeEliminarse())
                .expRegularValidacion(entity.getExpRegularValidacion())
                .expRegularMensaje(entity.getExpRegularMensaje())
                .build();
    }
}
