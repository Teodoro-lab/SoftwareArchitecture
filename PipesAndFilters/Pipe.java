package PipesAndFilters;

public class Pipe {
    private Filter outFilter;
    private Object information;

    public void sendInformation() {
        outFilter.filter(information);
    }

    public void setOutFilter(Filter outFilter) {
        this.outFilter = outFilter;
    }

    public Object getInformation() {
        return information;
    }

    public void setInformation(Object information) {
        this.information = information;
        if (outFilter != null)
            sendInformation();
    }

}
