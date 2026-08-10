package um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.infrastructure.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumnos.alumno.application.service.AlumnoGuaraniService;

@Component
@ConditionalOnProperty(name = "app.testing", havingValue = "false", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class AlumnoGuaraniScheduler {

    private final AlumnoGuaraniService service;

    @Scheduled(cron = "0 0/10 21-23,0-7 * * *", zone = "America/Argentina/Mendoza")
    public void generatePreuniversitarios() {
        log.debug("\n\nProcessing AlumnoGuaraniScheduler.generatePreuniversitarios\n\n");
//        service.processNextInscripcion();
    }

}
