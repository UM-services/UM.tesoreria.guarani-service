package um.tesoreria.guarani.hexagonal.guarani.alumno.infrastructure.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import um.tesoreria.guarani.hexagonal.guarani.alumno.application.service.AlumnoGuaraniService;

@Component
@ConditionalOnProperty(name = "app.testing", havingValue = "false", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class AlumnoGuaraniScheduler {

    private final AlumnoGuaraniService service;

    @Scheduled(fixedRate = 600000)
    public void generatePreuniversitarios() {
        log.debug("\n\nProcessing AlumnoGuaraniScheduler.generatePreuniversitarios\n\n");
        service.processNextInscripcion();
    }

}
