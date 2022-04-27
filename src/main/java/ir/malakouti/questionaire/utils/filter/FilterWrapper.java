package ir.malakouti.questionaire.utils.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class FilterWrapper {
    private Set<Filter> filters = new HashSet<>();
    private Set<BinaryFilter> binaryFilters = new HashSet<>();

    public FilterWrapper() {
    }

    public FilterWrapper(String jsonFilters) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Iterator<JsonNode> it = mapper.readTree(jsonFilters).iterator();
        while (it.hasNext()) {
            String element = it.next().toString();
            try {
                Filter f = mapper.readValue(element, Filter.class);
                filters.add(f);
            } catch (IOException e) {
                BinaryFilter bf = mapper.readValue(element, BinaryFilter.class);
                binaryFilters.add(bf);
            }
        }
    }

    public static FilterWrapper createWrapperWithFilter(String property, Filter.Operator operator, String value) {
        Set<Filter> filters = new HashSet<>();
        Filter filter = new Filter();
        filter.setOperator(operator);
        filter.setProperty(property);
        filter.setValue(value);
        filters.add(filter);
        FilterWrapper fw = new FilterWrapper();
        fw.setFilters(filters);
        return fw;
    }

    public FilterWrapper createOperationfilter(String operation) {
        addFilter("operation", Filter.Operator.EQUAL, operation);
        return this;
    }

    public FilterWrapper addFilter(String property, Filter.Operator operator, String value) {
        Filter filter = new Filter();
        filter.setProperty(property);
        filter.setValue(value);
        filter.setOperator(operator);
        this.filters.add(filter);
        return this;
    }

    public FilterWrapper addBinaryFilter(Filter first, Filter second) {
        BinaryFilter binaryFilter = new BinaryFilter(first, second);
        this.binaryFilters.add(binaryFilter);
        return this;
    }

    public FilterWrapper removeFilter(String property, Filter.Operator operator) {
        Iterator<Filter> it1 = filters.iterator();
        while (it1.hasNext()) {
            Filter filter = it1.next();
            if (filter.getProperty().equals(property) && filter.getOperator().equals(operator))
                it1.remove();
        }

        return this;
    }

    public FilterWrapper removeFilter(Filter filter) {
        this.filters.remove(filter);
        return this;
    }

    public FilterWrapper removeBinaryFilter(String property, Filter.Operator operator) {
        Iterator<BinaryFilter> it2 = binaryFilters.iterator();
        while (it2.hasNext()) {
            BinaryFilter binaryFilter = it2.next();
            if (binaryFilter.getFirst().getProperty().equals(property) && binaryFilter.getFirst().getOperator().equals(operator)
                    || binaryFilter.getSecond().getProperty().equals(property) && binaryFilter.getSecond().getOperator().equals(operator)) {
                binaryFilters.remove(binaryFilter);
            }
        }

        return this;
    }

    public void removeOperation() {
        removeFilter("operation", Filter.Operator.EQUAL);
    }

    public boolean contains(String name) {
        for (Filter filter : filters) {
            if (filter.getProperty().equals(name))
                return true;
        }

        return false;
    }

    public boolean hasOperation() {
        return this.contains("operation");
    }

    public boolean hasDefaultOperation() {
        if(hasOperation()) {
            return getOperation().endsWith("." + OperationConstants.GET);
        }

        return false;
    }

    public boolean hasOperation(String operation) {
        return hasOperation() && operation.equalsIgnoreCase(this.toMap().get("operation"));
    }

    public Filter getFilter(String property) {
        for (Filter filter : filters) {
            if (filter.getProperty().equals(property))
                return filter;
        }

        return null;
    }

    public String getOperation() {
        if(hasOperation())
            return this.toMap().get("operation");
        else return "";
    }

    /**
     * Converts filters with <code>EQUAL</code> operator to a map object.
     *
     * @return a Map object
     */
    public Map<String, String> toMap() {
        Map<String, String> filterMap = new HashMap<>();
        for (Filter filter : filters) {
            if (filter.getOperator().equals(Filter.Operator.EQUAL) || filter.getOperator().equals(Filter.Operator._EQUAL))
                filterMap.put(filter.getProperty(), filter.getValue());
        }

        return filterMap;
    }

    public int size() {
        return filters.size() + binaryFilters.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        return "FilterWrapper{" +
                "filters=" + filters +
                ", binaryFilters=" + binaryFilters +
                '}';
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
    }

    public Set<BinaryFilter> getBinaryFilters() {
        return binaryFilters;
    }

    public void setBinaryFilters(Set<BinaryFilter> binaryFilters) {
        this.binaryFilters = binaryFilters;
    }
}
