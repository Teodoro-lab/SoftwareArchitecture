package KWIC;

public abstract class Layer {
    private Layer nextLayer;

    public abstract Object execute(Object obj);

    public Object executeNextLayer(Object obj) {
        if (nextLayer != null)
            return nextLayer.execute(obj);
        return null;
    }

    public void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }

}
