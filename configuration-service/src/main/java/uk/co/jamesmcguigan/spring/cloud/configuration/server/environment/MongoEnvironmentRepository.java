package uk.co.jamesmcguigan.spring.cloud.configuration.server.environment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.*;

public class MongoEnvironmentRepository implements EnvironmentRepository {

    private static final String LABEL = "label";
    private static final String PROFILE = "profile";
    private static final String DEFAULT = "default";
    private static final String DEFAULT_PROFILE = null;
    private static final String DEFAULT_LABEL = null;
    private static final String S_S = "%s-%s";
    private static final String S_S_S = "%s-%s-%s";
    private static final String CANNOT_LOAD_ENVIRONMENT = "Cannot load environment";

    private final MongoTemplate mongoTemplate;
    private final MapFlattener mapFlattener;

    public MongoEnvironmentRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.mapFlattener = new MapFlattener();
    }

    @Override
    public Environment findOne(String name, String profile, String label) {
        String[] profilesArr = StringUtils.commaDelimitedListToStringArray(profile);
        List<String> profiles = new ArrayList<String>(Arrays.asList(profilesArr.clone()));
        for (int i = 0; i < profiles.size(); i++) {
            if (DEFAULT.equals(profiles.get(i))) {
                profiles.set(i, DEFAULT_PROFILE);
            }
        }
        profiles.add(DEFAULT_PROFILE); // Default configuration will have 'null' profile
        profiles = sortedUnique(profiles);

        List<String> labels = Arrays.asList(label, DEFAULT_LABEL); // Default configuration will have 'null' label
        labels = sortedUnique(labels);

        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(profiles.toArray()));
        query.addCriteria(Criteria.where(LABEL).in(labels.toArray()));

        Environment environment;
        try {
            List<MongoPropertySource> sources = mongoTemplate.find(query, MongoPropertySource.class, name);
            sortSourcesByLabel(sources, labels);
            sortSourcesByProfile(sources, profiles);
            environment = new Environment(name, profilesArr, label, null, null);
            for (MongoPropertySource propertySource : sources) {
                String sourceName = generateSourceName(name, propertySource);
                Map<String, Object> flatSource = mapFlattener.flatten(propertySource.getSource());
                PropertySource propSource = new PropertySource(sourceName, flatSource);
                environment.add(propSource);
            }
        } catch (Exception e) {
            throw new IllegalStateException(CANNOT_LOAD_ENVIRONMENT, e);
        }

        return environment;
    }

    private ArrayList<String> sortedUnique(List<String> values) {
        return new ArrayList<String>(new LinkedHashSet<String>(values));
    }

    private void sortSourcesByLabel(List<MongoPropertySource> sources,
                                    final List<String> labels) {
        sources.sort((s1, s2) -> {
            int i1 = labels.indexOf(s1.getLabel());
            int i2 = labels.indexOf(s2.getLabel());
            return Integer.compare(i1, i2);
        });
    }

    private void sortSourcesByProfile(List<MongoPropertySource> sources,
                                      final List<String> profiles) {
        sources.sort((s1, s2) -> {
            int i1 = profiles.indexOf(s1.getProfile());
            int i2 = profiles.indexOf(s2.getProfile());
            return Integer.compare(i1, i2);
        });
    }

    private String generateSourceName(String environmentName, MongoPropertySource source) {
        String sourceName;
        String profile = source.getProfile() != null ? source.getProfile() : DEFAULT;
        String label = source.getLabel();
        if (label != null) {
            sourceName = String.format(S_S_S, environmentName, profile, label);
        } else {
            sourceName = String.format(S_S, environmentName, profile);
        }
        return sourceName;
    }

    @Getter
    @Setter
    public static class MongoPropertySource {
        private String profile;
        private String label;
        private final LinkedHashMap<String, Object> source = new LinkedHashMap<>();
    }

    private static class MapFlattener extends YamlProcessor {

        public Map<String, Object> flatten(Map<String, Object> source) {
            return getFlattenedMap(source);
        }

    }

}
