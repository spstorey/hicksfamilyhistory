package spssoftware.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class FolderResource extends ResourceSupport {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
