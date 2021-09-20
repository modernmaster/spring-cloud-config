package uk.co.jamesmcguigan.spring.cloud.configuration.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import uk.co.jamesmcguigan.spring.cloud.domain.ReferenceData;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTaskJob {
    public static final String LICENSEE_ID = "Licensee Id: ";
    public static final String LICENSEE = "Licensee: ";
    public static final String HOST_IDS = "Host Ids: ";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String THE_TIME_IS_NOW = "The time is now {}";
    private static final String PROPERTY_1 = "Property 1:";
    private static final String ERROR_HAS_BEEN_THROWN = "Error has been thrown";
    private static final String HTTP_LOCALHOST_8080 = "http://localhost:8080/";
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
            ReferenceData appConfig = client.get().retrieve().bodyToMono(ReferenceData.class).block();
            if (appConfig.getLicensee() != null) {
                log.info(LICENSEE_ID + appConfig.getLicensee().getId());
                log.info(LICENSEE + appConfig.getLicensee().getName());
                log.info(HOST_IDS + appConfig.getLicensee().getHostIds());
            }
        } catch (Exception e) {
            log.error(ERROR_HAS_BEEN_THROWN, e);
        }
    }

}
