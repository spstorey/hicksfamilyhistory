package spssoftware.resource;

import org.springframework.hateoas.ResourceSupport;

public class AlbumResource extends ResourceSupport {

    private String name;
    private String displayDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }
}
