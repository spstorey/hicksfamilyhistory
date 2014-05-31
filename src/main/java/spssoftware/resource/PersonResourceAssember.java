package spssoftware.resource;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import spssoftware.controller.PersonController;
import spssoftware.jooq.tables.pojos.Person;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static spssoftware.resource.PersonResource.Sex;

@Component
public class PersonResourceAssember {

    public SearchResource toSearchResource(List<Person> persons) {
        SearchResource searchResource = new SearchResource();
        List<SummaryResource> summaries = new LinkedList<>();
        for (Person person : persons) {
            SummaryResource summaryResource = new SummaryResource();
            summaryResource.setName(person.getFirstName());
            summaries.add(summaryResource);
            summaryResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPerson(person.getPersonId())).withSelfRel());
        }

        searchResource.get_embedded().put("persons", summaries);
        searchResource.add(ControllerLinkBuilder.linkTo(methodOn(PersonController.class).listPersons()).withSelfRel());
        return searchResource;
    }

    public PersonResource toPersonResource(Person person) {
        PersonResource personResource = new PersonResource();
        personResource.setFirstName(person.getFirstName());
        if (person.getMiddleNames() != null) {
            personResource.setMiddlesNames(person.getMiddleNames().split(" "));
        }
        personResource.setSurname(person.getSurname());
        personResource.setSex(person.getSex().equals("M") ? Sex.MALE : Sex.FEMALE);

//        personResource.setDisplayDate(album.getDisplayDate());
//        personResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).getAlbum(album.getName())).withSelfRel());
//        if (album.getCoverPhoto() != null) {
//            personResource.add(new Link(album.getCoverPhoto().getUrl()).withRel("coverPhoto"));
//        }
//        personResource.add(ControllerLinkBuilder.linkTo(methodOn(MediaController.class).listPhotos(album.getName())).withRel("photos"));

        return personResource;
    }
}
