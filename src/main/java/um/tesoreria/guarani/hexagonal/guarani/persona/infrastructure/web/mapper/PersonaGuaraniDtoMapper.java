package um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.persona.domain.model.PersonaGuarani;
import um.tesoreria.guarani.hexagonal.guarani.persona.infrastructure.web.dto.PersonaGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.personaContacto.infrastructure.web.mapper.PersonaContactoGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.personaDocumento.infrastructure.web.mapper.PersonaDocumentoGuaraniDtoMapper;

import java.util.stream.Collectors;

@Component
public class PersonaGuaraniDtoMapper {

    private final PersonaDocumentoGuaraniDtoMapper personaDocumentoDtoMapper;
    private final PersonaContactoGuaraniDtoMapper personaContactoDtoMapper;

    public PersonaGuaraniDtoMapper(PersonaDocumentoGuaraniDtoMapper personaDocumentoDtoMapper, PersonaContactoGuaraniDtoMapper personaContactoDtoMapper) {
        this.personaDocumentoDtoMapper = personaDocumentoDtoMapper;
        this.personaContactoDtoMapper = personaContactoDtoMapper;
    }

    public PersonaGuaraniResponse toResponse(PersonaGuarani domain) {
        if (domain == null) return null;
        return PersonaGuaraniResponse.builder()
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
                .documentoPrincipalRel(personaDocumentoDtoMapper.toResponse(domain.getDocumentoPrincipalRel()))
                .contactos(domain.getContactos() == null ? null : domain.getContactos().stream().map(personaContactoDtoMapper::toResponse).collect(Collectors.toList()))
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
}
