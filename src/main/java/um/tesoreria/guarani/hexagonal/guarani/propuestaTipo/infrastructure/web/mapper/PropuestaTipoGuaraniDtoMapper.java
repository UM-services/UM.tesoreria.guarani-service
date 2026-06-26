package um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.domain.model.PropuestaTipoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestaTipo.infrastructure.web.dto.PropuestaTipoGuaraniResponse;

@Component
public class PropuestaTipoGuaraniDtoMapper {

    public PropuestaTipoGuaraniResponse toResponse(PropuestaTipoGuarani domain) {
        if (domain == null) return null;
        return PropuestaTipoGuaraniResponse.builder()
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
}
