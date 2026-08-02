package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.model.ResponsableAcademicaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.domain.ports.in.GetAllResponsablesAcademicasUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsableAcademicaGuaraniService {

    private final GetAllResponsablesAcademicasUseCase getAllResponsablesAcademicasUseCase;

    public List<ResponsableAcademicaGuarani> getAllResponsablesAcademicas() {
        return getAllResponsablesAcademicasUseCase.getAll();
    }
}
