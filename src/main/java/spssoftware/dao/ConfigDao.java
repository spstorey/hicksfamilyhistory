package spssoftware.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spssoftware.jooq.tables.records.ConfigRecord;

import java.util.List;

import static spssoftware.jooq.tables.Config.CONFIG;

@Repository
public class ConfigDao {

    @Autowired
    DSLContext connection;

    public String getByKey(String name) {

        List<ConfigRecord> keys = connection.selectFrom(CONFIG).fetch();

        return connection.selectFrom(CONFIG).where(CONFIG.NAME.eq(name)).fetchOne().getValue();
    }
}
