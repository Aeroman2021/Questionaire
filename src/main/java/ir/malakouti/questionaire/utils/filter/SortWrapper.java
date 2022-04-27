package ir.malakouti.questionaire.utils.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class SortWrapper {
    private Set<Sort> sortSet = new LinkedHashSet<>();

    public SortWrapper() {
    }

    public SortWrapper(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        sortSet = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(LinkedHashSet.class, Sort.class));
    }

    public Set<Sort> getSortSet() {
        return sortSet;
    }

    public void setSortSet(Set<Sort> sortSet) {
        this.sortSet = sortSet;
    }

}
