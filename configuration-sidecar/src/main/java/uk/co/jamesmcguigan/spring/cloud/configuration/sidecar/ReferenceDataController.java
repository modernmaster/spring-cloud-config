package uk.co.jamesmcguigan.spring.cloud.configuration.sidecar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.jamesmcguigan.spring.cloud.domain.ReferenceData;

@RestController
public class ReferenceDataController {

    @Autowired
    private ReferenceData referenceData;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ReferenceData get() {
        return referenceData;
    }
}

