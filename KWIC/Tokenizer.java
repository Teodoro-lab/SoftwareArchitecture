package KWIC;

import LayeredArchitecture.Layer;

public class Tokenizer extends Layer {
    private String phrase;

    public String[] tokenize() {
        String[] tokens = phrase.split(" ");
        return tokens;
    }

    @Override
    public Object execute(Object obj) {
        phrase = (String) obj;
        String[] tokens = tokenize();
        return super.executeNextLayer(tokens);
    }

}
