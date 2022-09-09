package KWIC;

import java.util.Arrays;

import LayeredArchitecture.Layer;

public class Alphabetizer extends Layer {
    private String[][] combinations;

    private String[] matrixOfWordsToArray() {
        String[] resultantPhrases = new String[combinations.length];

        int i = 0;
        for (String[] combination : combinations) {
            String currentPhrase = String.join(" ", combination);
            resultantPhrases[i++] = currentPhrase;
        }

        return resultantPhrases;
    }

    public String[] alphabetize() {
        String[] phrases = matrixOfWordsToArray();
        Arrays.sort(phrases, String.CASE_INSENSITIVE_ORDER);
        return phrases;
    }

    @Override
    public Object execute(Object obj) {
        combinations = (String[][]) obj;
        return alphabetize();
    }

}
