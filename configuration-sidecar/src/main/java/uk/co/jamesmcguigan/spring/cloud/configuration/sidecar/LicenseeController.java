package uk.co.jamesmcguigan.spring.cloud.configuration.sidecar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.jamesmcguigan.spring.cloud.domain.AppConfig;

@RestController
public class LicenseeController {

    @Autowired
    private AppConfig appConfig;

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public AppConfig get() {
        return appConfig;
    }
}

