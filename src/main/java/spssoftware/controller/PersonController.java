package spssoftware.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spssoftware.business.NameBuilder;
import spssoftware.dao.PersonDao;
import spssoftware.jooq.tables.pojos.Person;
import spssoftware.resource.PersonResource;
import spssoftware.resource.SearchResource;
import spssoftware.resource.SummaryResource;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "/persons", produces = "application/hal+json")
    public @ResponseBody SearchResource listPersons() {

        SearchResource searchResource = new SearchResource();

        List<SummaryResource> summaries = new LinkedList<SummaryResource>();
        for (Person person : personDao.getPersons()) {
            SummaryResource summaryResource  = new SummaryResource();
            summaryResource.setName(new NameBuilder().withTitle(person.getTitle()).withFirstName(person.getFirstname()).withSurname(person.getSurname()).build());
            summaryResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel());
            summaries.add(summaryResource);
        }

        searchResource.get_embedded().put("persons", summaries);

        return searchResource;
    }

    @RequestMapping(value = "/persons/{id}", produces = "application/hal+json")
    public @ResponseBody PersonResource getPerson(@RequestParam("id") String id) {
        Person person = personDao.getById(id);
        PersonResource personResource = new PersonResource();
        personResource.setTitle(person.getTitle());
        personResource.setFirstName(person.getFirstname());
        personResource.setSurname(person.getSurname());
        personResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel());
        return personResource;
    }
}
