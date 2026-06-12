package um.tesoreria.guarani.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "um.tesoreria.guarani")
public class GuaraniConfiguration {
}
