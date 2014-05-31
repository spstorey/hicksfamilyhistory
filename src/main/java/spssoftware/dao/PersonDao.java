package spssoftware.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spssoftware.jooq.tables.pojos.Person;

import java.util.List;

import static spssoftware.jooq.tables.Person.PERSON;

@Repository
public class PersonDao {

    @Autowired
    DSLContext connection;

    public List<Person> listPersons() {
        return connection.selectFrom(PERSON).orderBy(PERSON.SURNAME, PERSON.FIRST_NAME, PERSON.BIRTH_YEAR, PERSON.BIRTH_MONTH, PERSON.BIRTH_DAY).fetchInto(Person.class);
    }

    public Person getPersonById(String personId) {
        return connection.selectFrom(PERSON).where(PERSON.PERSON_ID.eq(personId)).fetchOneInto(Person.class);
    }
}
