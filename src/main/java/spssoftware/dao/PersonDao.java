package spssoftware.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spssoftware.jooq.tables.pojos.Person;

import static spssoftware.jooq.tables.Person.*;

import java.util.List;
import java.util.UUID;

@Repository
public class PersonDao {

    @Autowired
    DSLContext connection;

    public List<Person> getPersons() {
        return connection.select().from(PERSON).fetchInto(Person.class);
    }

    public Person getByPersonId(String personId) {
        return connection.select().from(PERSON).where(PERSON.PERSON_ID.eq(personId)).fetchOneInto(Person.class);
    }

    public Person createPerson(Person person) {
        person.setPersonId(UUID.randomUUID().toString());
        connection.insertInto(PERSON)
                .set(PERSON.PERSON_ID, person.getPersonId())
                .set(PERSON.TITLE, person.getTitle())
                .set(PERSON.FIRST_NAME, person.getFirstName())
                .set(PERSON.MIDDLE_NAMES, person.getMiddleNames())
                .set(PERSON.SURNAME, person.getSurname())
                .set(PERSON.MAIDEN_NAME, person.getMaidenName())
                .set(PERSON.BIRTH_DATE, person.getBirthDate())
                .set(PERSON.DEATH_DATE, person.getDeathDate()).execute();
        return person;
    }

    public Person replacePerson(String personId, Person person) {
        connection.update(PERSON)
                .set(PERSON.TITLE, person.getTitle())
                .set(PERSON.FIRST_NAME, person.getFirstName())
                .set(PERSON.MIDDLE_NAMES, person.getMiddleNames())
                .set(PERSON.SURNAME, person.getSurname())
                .set(PERSON.MAIDEN_NAME, person.getMaidenName())
                .set(PERSON.BIRTH_DATE, person.getBirthDate())
                .set(PERSON.DEATH_DATE, person.getDeathDate())
                .where(PERSON.PERSON_ID.equal(personId)).execute();
        return person;
    }
}
