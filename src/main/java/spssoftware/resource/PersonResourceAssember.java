package spssoftware.resource;


import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import spssoftware.business.NameBuilder;
import spssoftware.controller.PersonController;
import spssoftware.jooq.tables.pojos.Person;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PersonResourceAssember {

    public SearchResource toSearchResource(List<Person> persons) {
        SearchResource searchResource = new SearchResource();

        List<SummaryResource> summaries = new LinkedList<SummaryResource>();
        for (Person person : persons) {
            SummaryResource summaryResource = new SummaryResource();
            summaries.add(toSummaryResource(person));
        }

        searchResource.get_embedded().put("persons", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).listPersons()).withSelfRel());
        return searchResource;
    }

    public SummaryResource toSummaryResource(Person person) {
        SummaryResource summaryResource = new SummaryResource();
        NameBuilder nameBuilder = new NameBuilder().withTitle(person.getTitle()).withFirstName(person.getFirstName()).withSurname(person.getSurname());
        summaryResource.setName(nameBuilder.build());
        summaryResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        return summaryResource;
    }

    public PersonResource toResource(Person person) {
        PersonResource personResource = new PersonResource();
        BeanUtils.copyProperties(person, personResource);
        personResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        return personResource;
    }

    public Person fromResource(PersonResource personResource) {
        Person person = new Person();
        BeanUtils.copyProperties(personResource, person);
        return person;
    }


}
