package spssoftware.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spssoftware.resource.PersonResource;

@RestController
public class PersonController {

    @RequestMapping(value = "/persons", produces = "application/hal+json")
    public
    @ResponseBody
    PersonResource listPersons() {
        PersonResource personResource = new PersonResource();
        personResource.setFirstName("Shaun");
        personResource.setSurname("Storey");
        return personResource;
    }
}
