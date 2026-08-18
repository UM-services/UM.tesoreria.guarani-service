package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.client.dto.CreatePersonalesResponse;

@FeignClient(name = "tesoreria-core-service")
public interface AlumnoGuaraniClient {

    @PostMapping("/api/tesoreria/core/guarani/alumno/create/preuniversitario")
    AlumnoGuarani createPreuniversitario(@RequestBody CreatePersonalesResponse alumno);

    @PostMapping("/api/tesoreria/core/guarani/alumno/create/personales")
    CreatePersonalesResponse createPersonales(@RequestBody AlumnoGuarani alumno);

}
