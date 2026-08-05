package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.web.mapper;

import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.web.dto.AlumnoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.web.dto.PersonaAlumnoGuaraniResponse;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaContacto.infrastructure.web.mapper.PersonaContactoGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.personaDocumento.infrastructure.web.mapper.PersonaDocumentoGuaraniDtoMapper;
import um.tesoreria.guarani.hexagonal.guarani.requisitos.requisitoPresentado.infrastructure.web.mapper.RequisitoPresentadoGuaraniDtoMapper;

@Component
public class AlumnoGuaraniDtoMapper {

    private final PersonaDocumentoGuaraniDtoMapper documentoMapper;
    private final PersonaContactoGuaraniDtoMapper contactoMapper;
    private final RequisitoPresentadoGuaraniDtoMapper requisitoMapper;

    public AlumnoGuaraniDtoMapper(PersonaDocumentoGuaraniDtoMapper documentoMapper,
                                  PersonaContactoGuaraniDtoMapper contactoMapper,
                                  RequisitoPresentadoGuaraniDtoMapper requisitoMapper) {
        this.documentoMapper = documentoMapper;
        this.contactoMapper = contactoMapper;
        this.requisitoMapper = requisitoMapper;
    }

    public AlumnoGuaraniResponse toResponse(AlumnoGuarani domain) {
        if (domain == null) return null;
        return AlumnoGuaraniResponse.builder()
                .alumno(domain.getAlumno())
                .legajo(domain.getLegajo())
                .persona(domain.getPersona())
                .personaRel(toPersonaResponse(domain))
                .propuesta(domain.getPropuesta())
                .planVersion(domain.getPlanVersion())
                .ubicacion(domain.getUbicacion())
                .modalidad(domain.getModalidad())
                .division(domain.getDivision())
                .anioCursada(domain.getAnioCursada())
                .cantidadReadmisiones(domain.getCantidadReadmisiones())
                .regular(domain.getRegular())
                .calidad(domain.getCalidad())
                .coeficiente(domain.getCoeficiente())
                .build();
    }

    private PersonaAlumnoGuaraniResponse toPersonaResponse(AlumnoGuarani alumno) {
        var persona = alumno.getPersonaRel();
        if (persona == null) return null;

        return PersonaAlumnoGuaraniResponse.builder()
                .persona(persona.getPersona())
                .apellido(persona.getApellido())
                .nombres(persona.getNombres())
                .apellidoElegido(persona.getApellidoElegido())
                .nombresElegido(persona.getNombresElegido())
                .sexo(persona.getSexo())
                .identidadGenero(persona.getIdentidadGenero())
                .identidadGeneroOtro(persona.getIdentidadGeneroOtro())
                .fechaNacimiento(persona.getFechaNacimiento())
                .localidadNacimiento(persona.getLocalidadNacimiento())
                .nacionalidad(persona.getNacionalidad())
                .fechaIngresoPais(persona.getFechaIngresoPais())
                .paisOrigen(persona.getPaisOrigen())
                .documentoPrincipal(persona.getDocumentoPrincipal())
                .documentoPrincipalRel(documentoMapper.toResponse(persona.getDocumentoPrincipalRel()))
                .contactos(persona.getContactos() == null ? null : persona.getContactos().stream()
                        .map(contactoMapper::toResponse)
                        .toList())
                .requisitosPresentados(persona.getRequisitosPresentados() == null ? null : persona.getRequisitosPresentados().stream()
                        .map(requisitoMapper::toResponse)
                        .filter(requisito -> requisito.getRequisitoRel() != null
                                && requisito.getRequisitoRel().getRequisitoTipo() != null
                                && requisito.getRequisitoRel().getRequisitoTipo() == 4)
                        .toList())
                .emailTemporal(persona.getEmailTemporal())
                .emailValido(persona.getEmailValido())
                .idImagen(persona.getIdImagen())
                .tipoUsuarioInicial(persona.getTipoUsuarioInicial())
                .pertenecePuebloOriginario(persona.getPertenecePuebloOriginario())
                .puebloOriginario(persona.getPuebloOriginario())
                .puebloOriginarioOtro(persona.getPuebloOriginarioOtro())
                .araiIdentificadorSso(persona.getAraiIdentificadorSso())
                .build();
    }
}
