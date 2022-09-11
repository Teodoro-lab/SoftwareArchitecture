package KWIC;

import java.util.Arrays;

import PipesAndFilters.Filter;

public class Alphabetizer extends Filter {

    private String[] matrixOfWordsToArray(String[][] combinations) {
        String[] resultantPhrases = new String[combinations.length];

        int i = 0;
        for (String[] combination : combinations) {
            String currentPhrase = String.join(" ", combination);
            resultantPhrases[i++] = currentPhrase;
        }

        return resultantPhrases;
    }

    public String[] alphabetize(String[][] combinations) {
        String[] phrases = matrixOfWordsToArray(combinations);
        Arrays.sort(phrases, String.CASE_INSENSITIVE_ORDER);
        return phrases;
    }

    @Override
    public void filter(Object obj) {
        String[][] combinations = (String[][]) obj;
        String[] phrases = alphabetize(combinations);
        sendInformation(phrases);
    }

}
