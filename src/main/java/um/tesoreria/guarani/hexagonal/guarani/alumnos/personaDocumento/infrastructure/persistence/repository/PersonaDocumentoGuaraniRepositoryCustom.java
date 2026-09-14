package um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.repository;

import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.entity.PersonaDocumentoGuaraniEntity;

import java.util.List;

public interface PersonaDocumentoGuaraniRepositoryCustom {

    List<PersonaDocumentoGuaraniEntity> findAllByDigitosNroDocumento(String digitos);
}
