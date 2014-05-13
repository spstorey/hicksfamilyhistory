package spssoftware.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spssoftware.business.NameBuilder;
import spssoftware.dao.PersonDao;
import spssoftware.jooq.tables.pojos.Person;
import spssoftware.resource.PersonResource;
import spssoftware.resource.PersonResourceAssember;
import spssoftware.resource.SearchResource;
import spssoftware.resource.SummaryResource;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonResourceAssember personResourceAssember;

    @RequestMapping(value = "/persons", produces = "application/hal+json")
    public @ResponseBody SearchResource listPersons() {
        return personResourceAssember.toSearchResource(personDao.getPersons());
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/hal+json")
    public @ResponseBody PersonResource createPerson(@RequestBody PersonResource personResource) {
        return personResourceAssember.toResource(personDao.createPerson(personResourceAssember.fromResource(personResource)));
    }

    @RequestMapping(value = "/persons/{personId}", produces = "application/hal+json")
    public @ResponseBody PersonResource getPerson(@PathVariable("personId") String personId) {
        return personResourceAssember.toResource(personDao.getByPersonId(personId));
    }

    @RequestMapping(value = "/persons/{personId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/hal+json")
    public @ResponseBody PersonResource replacePerson(@PathVariable("personId") String personId, @RequestBody PersonResource personResource) {
        return personResourceAssember.toResource(personDao.replacePerson(personId, personResourceAssember.fromResource(personResource)));
    }
}
