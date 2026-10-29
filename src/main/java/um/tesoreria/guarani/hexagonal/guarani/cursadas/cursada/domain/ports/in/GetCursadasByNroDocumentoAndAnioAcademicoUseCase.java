package um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.ports.in;

import um.tesoreria.guarani.hexagonal.guarani.cursadas.cursada.domain.model.CursadaGuarani;

import java.util.List;

public interface GetCursadasByNroDocumentoAndAnioAcademicoUseCase {
    List<CursadaGuarani> getByNroDocumentoAndAnioAcademico(String nroDocumento, Integer anioAcademico);
}
