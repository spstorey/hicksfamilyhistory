package spssoftware.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spssoftware.jooq.tables.pojos.Person;

import static spssoftware.jooq.tables.Person.*;

import java.util.List;

@Repository
public class PersonDao {

    //@Autowired
    DSLContext connection;

    public List<Person> getPersons() {
        return connection.select().from(PERSON).fetchInto(Person.class);
    }

    public Person getById(String id) {
        return connection.select().from(PERSON).where(PERSON.ID.eq(id)).fetchOneInto(Person.class);
    }
}
