package uk.co.jamesmcguigan.spring.cloud.configuration.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import uk.co.jamesmcguigan.spring.cloud.domain.AppConfig;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTaskJob {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String THE_TIME_IS_NOW = "The time is now {}";
    private static final String PROPERTY_1 = "Property 1:";
    private static final String ERROR_HAS_BEEN_THROWN = "Error has been thrown";
    private static final String HTTP_LOCALHOST_8080 = "http://localhost:8080/config";
    private static final String URL = "url";

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info(THE_TIME_IS_NOW, dateFormat.format(new Date()));
        try {
            WebClient client = WebClient.builder()
                    .filters(exchangeFilterFunctions -> {
                    })
                    .baseUrl(HTTP_LOCALHOST_8080)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap(URL, HTTP_LOCALHOST_8080))
                    .build();
            AppConfig appConfig = client.get().retrieve().bodyToMono(AppConfig.class).block();
            log.info(PROPERTY_1 + appConfig.getProp1());
        } catch (Exception e) {
            log.error(ERROR_HAS_BEEN_THROWN, e);
        }
    }

}
