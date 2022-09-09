import java.util.Scanner;

import KWIC.Alphabetizer;
import KWIC.Combinator;
import KWIC.Layer;
import KWIC.Tokenizer;

class Main {

    public static String readInputString() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            return input;
        }
    }

    public static void printPhrases(String[] phrases) {
        for (String phrase : phrases) {
            System.out.println(phrase);
        }
    }

    public static void main(String[] args) {

        Layer layer1 = new Tokenizer();
        Layer layer2 = new Combinator();
        Layer layer3 = new Alphabetizer();

        layer1.setNextLayer(layer2);
        layer2.setNextLayer(layer3);

        System.out.println("Ingrese la frase");
        String input = readInputString();

        Object returnedObject = layer1.execute(input);
        String[] phrases = (String[]) returnedObject;

        System.out.println("Imprimiendo combinaciones...");
        printPhrases(phrases);
    }
}