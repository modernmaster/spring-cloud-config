package uk.co.jamesmcguigan.spring.cloud.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Getter
@Setter
public class ReferenceData {

    private Licensee licensee;
    private String prop1;

}
