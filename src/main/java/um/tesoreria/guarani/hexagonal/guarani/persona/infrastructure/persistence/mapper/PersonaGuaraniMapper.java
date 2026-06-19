package um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.persistence.entity.PersonaGuaraniEntity;

@Component
public class PersonaGuaraniMapper {

    public PersonaGuaraniEntity toEntity(PersonaGuarani domain) {
        if (domain == null) return null;
        return PersonaGuaraniEntity.builder()
                .persona(domain.getPersonaId())
                .apellido(domain.getApellido())
                .nombres(domain.getNombres())
                .apellidoElegido(domain.getApellidoElegido())
                .nombresElegido(domain.getNombresElegido())
                .sexo(domain.getSexo())
                .identidadGenero(domain.getIdentidadGenero())
                .identidadGeneroOtro(domain.getIdentidadGeneroOtro())
                .fechaNacimiento(domain.getFechaNacimiento())
                .localidadNacimiento(domain.getLocalidadNacimiento())
                .nacionalidad(domain.getNacionalidad())
                .fechaIngresoPais(domain.getFechaIngresoPais())
                .paisOrigen(domain.getPaisOrigen())
                .documentoPrincipal(domain.getDocumentoPrincipal())
                .usuario(domain.getUsuario())
                .clave(domain.getClave())
                .fechaVencimientoClave(domain.getFechaVencimientoClave())
                .autenticacion(domain.getAutenticacion())
                .bloqueado(domain.getBloqueado())
                .token(domain.getToken())
                .tokenAlta(domain.getTokenAlta())
                .emailTemporal(domain.getEmailTemporal())
                .emailValido(domain.getEmailValido())
                .idImagen(domain.getIdImagen())
                .tipoUsuarioInicial(domain.getTipoUsuarioInicial())
                .pertenecePuebloOriginario(domain.getPertenecePuebloOriginario())
                .puebloOriginario(domain.getPuebloOriginario())
                .puebloOriginarioOtro(domain.getPuebloOriginarioOtro())
                .araiIdentificadorSso(domain.getAraiIdentificadorSso())
                .araiUuid(domain.getAraiUuid())
                .build();
    }

    public PersonaGuarani toDomain(PersonaGuaraniEntity entity) {
        if (entity == null) return null;
        return PersonaGuarani.builder()
                .personaId(entity.getPersona())
                .apellido(entity.getApellido())
                .nombres(entity.getNombres())
                .apellidoElegido(entity.getApellidoElegido())
                .nombresElegido(entity.getNombresElegido())
                .sexo(entity.getSexo())
                .identidadGenero(entity.getIdentidadGenero())
                .identidadGeneroOtro(entity.getIdentidadGeneroOtro())
                .fechaNacimiento(entity.getFechaNacimiento())
                .localidadNacimiento(entity.getLocalidadNacimiento())
                .nacionalidad(entity.getNacionalidad())
                .fechaIngresoPais(entity.getFechaIngresoPais())
                .paisOrigen(entity.getPaisOrigen())
                .documentoPrincipal(entity.getDocumentoPrincipal())
                .usuario(entity.getUsuario())
                .clave(entity.getClave())
                .fechaVencimientoClave(entity.getFechaVencimientoClave())
                .autenticacion(entity.getAutenticacion())
                .bloqueado(entity.getBloqueado())
                .token(entity.getToken())
                .tokenAlta(entity.getTokenAlta())
                .emailTemporal(entity.getEmailTemporal())
                .emailValido(entity.getEmailValido())
                .idImagen(entity.getIdImagen())
                .tipoUsuarioInicial(entity.getTipoUsuarioInicial())
                .pertenecePuebloOriginario(entity.getPertenecePuebloOriginario())
                .puebloOriginario(entity.getPuebloOriginario())
                .puebloOriginarioOtro(entity.getPuebloOriginarioOtro())
                .araiIdentificadorSso(entity.getAraiIdentificadorSso())
                .araiUuid(entity.getAraiUuid())
                .build();
    }
}
