package um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.persona.infrastructure.persistence.entity.PersonaGuaraniEntity;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.infrastructure.persistence.mapper.PersonaContactoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.persistence.mapper.PersonaDocumentoGuaraniMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.infrastructure.persistence.mapper.RequisitoPresentadoMapper;

import java.util.stream.Collectors;

@Component
public class PersonaGuaraniMapper {

    private final PersonaDocumentoGuaraniMapper personaDocumentoMapper;
    private final PersonaContactoGuaraniMapper personaContactoMapper;
    private final RequisitoPresentadoMapper requisitoPresentadoMapper;

    public PersonaGuaraniMapper(PersonaDocumentoGuaraniMapper personaDocumentoMapper, PersonaContactoGuaraniMapper personaContactoMapper, RequisitoPresentadoMapper requisitoPresentadoMapper) {
        this.personaDocumentoMapper = personaDocumentoMapper;
        this.personaContactoMapper = personaContactoMapper;
        this.requisitoPresentadoMapper = requisitoPresentadoMapper;
    }

    public PersonaGuaraniEntity toEntity(PersonaGuarani domain) {
        if (domain == null) return null;
        return PersonaGuaraniEntity.builder()
                .persona(domain.getPersona())
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
                .documentoPrincipalRel(personaDocumentoMapper.toEntity(domain.getDocumentoPrincipalRel()))
                .contactos(domain.getContactos() == null ? null : domain.getContactos().stream().map(personaContactoMapper::toEntity).collect(Collectors.toList()))
                .requisitosPresentados(domain.getRequisitosPresentados() == null ? null : domain.getRequisitosPresentados().stream().map(requisitoPresentadoMapper::toEntity).collect(Collectors.toList()))
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
                .persona(entity.getPersona())
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
                .documentoPrincipalRel(personaDocumentoMapper.toDomain(entity.getDocumentoPrincipalRel()))
                .contactos(entity.getContactos() == null ? null : entity.getContactos().stream().map(personaContactoMapper::toDomain).collect(Collectors.toList()))
                .requisitosPresentados(entity.getRequisitosPresentados() == null ? null : entity.getRequisitosPresentados().stream().map(requisitoPresentadoMapper::toDomain).collect(Collectors.toList()))
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
