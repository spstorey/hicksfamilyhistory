package spssoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spssoftware.dao.PersonDao;
import spssoftware.resource.PersonResource;
import spssoftware.resource.PersonResourceAssember;
import spssoftware.resource.SearchResource;

@RestController
public class PersonController {

    @Autowired
    private PersonResourceAssember personResourceAssember;

    @Autowired
    private PersonDao personDao;

    @RequestMapping(value = "/persons", produces = "application/hal+json")
    public
    @ResponseBody
    SearchResource listPersons() {
        return personResourceAssember.toSearchResource(personDao.listPersons());
    }

    @RequestMapping(value = "/persons/{id}", produces = "application/hal+json")
    public
    @ResponseBody
    PersonResource getPerson(@PathVariable("id") String personId) {
        return personResourceAssember.toPersonResource(personDao.getPersonById(personId));
    }
}
