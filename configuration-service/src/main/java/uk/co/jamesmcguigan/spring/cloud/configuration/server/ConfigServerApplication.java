package uk.co.jamesmcguigan.spring.cloud.configuration.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import uk.co.jamesmcguigan.spring.cloud.configuration.server.config.EnableMongoConfigServer;
import uk.co.jamesmcguigan.spring.cloud.configuration.server.config.MongoEnvironmentRepositoryConfiguration;


@Import(MongoEnvironmentRepositoryConfiguration.class)
@EnableMongoConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
