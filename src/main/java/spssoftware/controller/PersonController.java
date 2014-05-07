package spssoftware.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spssoftware.domain.Person;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PersonController {

    @RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Person> listPersons() {


        List<Person> persons =  new LinkedList<Person>();

        Person person = new Person();
        person.setTitle("Mr");
        person.setFirstName("Shaun");
        person.setSurname("Storey");
        person.setBirthDate(DateTime.parse("13/07/1977", DateTimeFormat.forPattern("dd/MM/yyyy")).toDate());
        persons.add(person);

        return persons;
    }
}
