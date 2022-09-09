package KWIC;

public class Combinator extends Layer {
    private String[] tokens;

    private void shiftValues(String[] tokens) {
        String lastString = tokens[tokens.length - 1];
        for (int i = tokens.length - 1; i > 0; i--) {
            // el token actual es igual al token anterior
            tokens[i] = tokens[i - 1];
        }
        tokens[0] = lastString;
    }

    public String[][] combinations(String[] tokens) {
        String[][] wordCombinations = new String[tokens.length][];

        for (int i = 0; i < tokens.length; i++) {
            shiftValues(tokens);
            String[] copiedArray = new String[tokens.length];
            System.arraycopy(tokens, 0, copiedArray, 0, tokens.length);
            wordCombinations[i] = copiedArray;
        }

        return wordCombinations;
    }

    @Override
    public Object execute(Object obj) {
        tokens = (String[]) obj;
        String[][] combinations = combinations(tokens);
        return super.executeNextLayer(combinations);
    }
}
