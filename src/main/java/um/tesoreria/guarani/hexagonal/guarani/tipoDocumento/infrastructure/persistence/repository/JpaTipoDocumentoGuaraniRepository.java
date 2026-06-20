package um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.tesoreria.guarani.hexagonal.guarani.tipoDocumento.infrastructure.persistence.entity.TipoDocumentoGuaraniEntity;

@Repository
public interface JpaTipoDocumentoGuaraniRepository extends JpaRepository<TipoDocumentoGuaraniEntity, Short> {
}
