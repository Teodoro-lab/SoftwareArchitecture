package org.PipesAndFilters;

public class Pipe {
    private Filter nextFilter;
    private Object information;

    public void setNextFilter(Filter nextFilter) {
        this.nextFilter = nextFilter;
    }

    public Object getInformation() {
        return information;
    }

    public void sendInfoToFilter(Object information) {
        this.information = information;
        if (nextFilter != null)
            nextFilter.filter(information);
    }

}
