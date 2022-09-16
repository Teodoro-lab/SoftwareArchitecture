package PipesAndFilters;

public abstract class Filter {
    private Pipe nextPipe;

    public abstract void filter(Object information);

    public void setNextPipe(Pipe nextPipe) {
        this.nextPipe = nextPipe;
    }

    public void sendNextPipeInfo(Object information) {
        nextPipe.sendInfoToFilter(information);
    }

}
