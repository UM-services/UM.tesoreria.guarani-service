package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import um.tesoreria.guarani.hexagonal.guarani.alumno.domain.model.AlumnoGuarani;
import um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.client.dto.AlumnoDeteccionRequest;

import java.util.List;

@FeignClient(name = "tesoreria-core-service")
public interface AlumnoGuaraniClient {

    @PostMapping("/api/tesoreria/core/guarani/alumno/create/preuniversitario")
    AlumnoGuarani createPreuniversitario(@RequestBody AlumnoGuarani alumno);

    @PostMapping("/api/tesoreria/core/guarani/alumno/desmarcar/enviadas")
    List<AlumnoDeteccionRequest> desmarcarEnviados(@RequestBody List<AlumnoDeteccionRequest> encontrados);

}
