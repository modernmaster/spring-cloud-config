package uk.co.jamesmcguigan.spring.cloud.configuration.server.environment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedHashMap;

@Getter
@AllArgsConstructor
class MongoPropertySource {
    private String profile;
    private String label;
    private LinkedHashMap<String, Object> source;
}
