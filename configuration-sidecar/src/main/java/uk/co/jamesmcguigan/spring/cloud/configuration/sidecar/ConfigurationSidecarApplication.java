package uk.co.jamesmcguigan.spring.cloud.configuration.sidecar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uk.co.jamesmcguigan.spring.cloud.domain.AppConfig;

@SpringBootApplication
@EnableConfigurationProperties({AppConfig.class})
@Slf4j
public class ConfigurationSidecarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationSidecarApplication.class, args);
    }

}
