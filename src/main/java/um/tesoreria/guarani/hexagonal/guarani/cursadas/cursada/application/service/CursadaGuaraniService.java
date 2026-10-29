package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.in.GetCursadasByNroDocumentoAndAnioAcademicoUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursadaGuaraniService {

    private final GetCursadasByNroDocumentoAndAnioAcademicoUseCase getCursadasByNroDocumentoAndAnioAcademicoUseCase;

    public List<CursadaGuarani> getAllByNroDocumentoAndAnioAcademico(String nroDocumento, Integer anioAcademico) {
        return getCursadasByNroDocumentoAndAnioAcademicoUseCase.getByNroDocumentoAndAnioAcademico(nroDocumento, anioAcademico);
    }
}
