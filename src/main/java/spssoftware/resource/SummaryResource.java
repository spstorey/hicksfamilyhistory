package spssoftware.resource;

import org.springframework.hateoas.ResourceSupport;

public class SummaryResource extends ResourceSupport {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
