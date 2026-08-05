package um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.model.PropuestaOfertaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.domain.ports.out.PropuestaOfertaGuaraniRepository;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.mapper.PropuestaOfertaGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaOferta.infrastructure.persistence.repository.JpaPropuestaOfertaGuaraniRepository;

@Component
@RequiredArgsConstructor
public class JpaPropuestaOfertaGuaraniRepositoryAdapter implements PropuestaOfertaGuaraniRepository {
    private final JpaPropuestaOfertaGuaraniRepository repository;
    private final PropuestaOfertaGuaraniMapper mapper;

    @Override
    public List<PropuestaOfertaGuarani> findAllByUbicacion(Integer ubicacion) {
        return repository.findAllByUbicacion(ubicacion).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<PropuestaOfertaGuarani> findAllByUbicacionAndPropuestaTipo(Integer ubicacion, Integer propuestaTipo) {
        return repository.findAllByUbicacionAndPropuestaRel_PropuestaTipo(ubicacion, propuestaTipo).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
