package spssoftware.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spssoftware.jooq.tables.pojos.Media;

import java.util.List;

import static spssoftware.jooq.tables.Media.MEDIA;

@Repository
public class MediaDao {

    @Autowired
    DSLContext connection;

    public List<Media> getMediaForPersonId(String personId) {
        return connection.selectFrom(MEDIA).where(MEDIA.PERSON_ID.eq(personId)).fetchInto(Media.class);
    }
}
