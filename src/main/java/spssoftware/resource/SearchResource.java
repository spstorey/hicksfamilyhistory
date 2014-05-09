package spssoftware.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResource {

    private Map<String,List<SummaryResource>> _embedded = new HashMap<String,List<SummaryResource>>();

    public Map<String, List<SummaryResource>> get_embedded() {
        return _embedded;
    }

    public void set_embedded(Map<String, List<SummaryResource>> _embedded) {
        this._embedded = _embedded;
    }
}
