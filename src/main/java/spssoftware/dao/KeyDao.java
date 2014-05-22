package spssoftware.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spssoftware.jooq.tables.Key;
import spssoftware.jooq.tables.pojos.Person;
import spssoftware.jooq.tables.records.KeyRecord;

import java.util.List;
import java.util.UUID;

import static spssoftware.jooq.tables.Key.KEY;
import static spssoftware.jooq.tables.Person.*;

@Repository
public class KeyDao {

    @Autowired
    DSLContext connection;

    public String getByKey(String name) {

        List<KeyRecord> keys =  connection.selectFrom(KEY).fetch();

        return connection.selectFrom(KEY).where(KEY.NAME.eq(name)).fetchOne().getValue();
    }
}
