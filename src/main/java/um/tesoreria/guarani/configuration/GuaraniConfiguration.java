package um.tesoreria.guarani.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients(basePackages = "um.tesoreria.guarani")
@EnableScheduling
public class GuaraniConfiguration {
}
