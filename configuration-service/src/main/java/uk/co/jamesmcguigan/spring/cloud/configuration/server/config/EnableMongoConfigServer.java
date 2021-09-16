package uk.co.jamesmcguigan.spring.cloud.configuration.server.config;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import(MongoEnvironmentRepositoryConfiguration.class)
@EnableConfigServer
public @interface EnableMongoConfigServer {

}