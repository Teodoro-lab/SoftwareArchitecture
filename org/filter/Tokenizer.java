package org.filter;

import org.PipesAndFilters.Filter;

public class Tokenizer extends Filter {

    public String[] tokenize(String phrase) {
        String[] tokens = phrase.split(" ");
        return tokens;
    }

    @Override
    public void filter(Object obj) {
        String phrase = (String) obj;
        String[] tokens = tokenize(phrase);
        sendNextPipeInfo(tokens);
    }

}
