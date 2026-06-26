package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.persistence.entity.PropuestaTipoGuaraniEntity;

@Component
public class PropuestaTipoGuaraniMapper {

    public PropuestaTipoGuaraniEntity toEntity(PropuestaTipoGuarani domain) {
        if (domain == null) return null;
        return PropuestaTipoGuaraniEntity.builder()
                .propuestaTipo(domain.getPropuestaTipo())
                .descripcion(domain.getDescripcion())
                .otorgaTitulo(domain.getOtorgaTitulo())
                .reportaAraucano(domain.getReportaAraucano())
                .permiteMatricular(domain.getPermiteMatricular())
                .permiteInscribir(domain.getPermiteInscribir())
                .controlRegularidad(domain.getControlRegularidad())
                .disponibleEnAutogestion(domain.getDisponibleEnAutogestion())
                .informarMovilidadInternacional(domain.getInformarMovilidadInternacional())
                .build();
    }

    public PropuestaTipoGuarani toDomain(PropuestaTipoGuaraniEntity entity) {
        if (entity == null) return null;
        return PropuestaTipoGuarani.builder()
                .propuestaTipo(entity.getPropuestaTipo())
                .descripcion(entity.getDescripcion())
                .otorgaTitulo(entity.getOtorgaTitulo())
                .reportaAraucano(entity.getReportaAraucano())
                .permiteMatricular(entity.getPermiteMatricular())
                .permiteInscribir(entity.getPermiteInscribir())
                .controlRegularidad(entity.getControlRegularidad())
                .disponibleEnAutogestion(entity.getDisponibleEnAutogestion())
                .informarMovilidadInternacional(entity.getInformarMovilidadInternacional())
                .build();
    }
}
