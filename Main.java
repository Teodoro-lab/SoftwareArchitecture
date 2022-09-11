import java.util.Scanner;

import KWIC.Alphabetizer;
import KWIC.Combinator;
import KWIC.Tokenizer;
import PipesAndFilters.Filter;
import PipesAndFilters.Pipe;

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

        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();

        Filter filter1 = new Tokenizer();
        Filter filter2 = new Combinator();
        Filter filter3 = new Alphabetizer();

        pipe1.setOutFilter(filter1);
        filter1.setNextPipe(pipe2);

        pipe2.setOutFilter(filter2);
        filter2.setNextPipe(pipe3);

        pipe3.setOutFilter(filter3);
        filter3.setNextPipe(pipe4);

        System.out.println("Ingrese la frase");
        String input = readInputString();
        pipe1.setInformation(input);

        Object returnedObj = pipe4.getInformation();
        String[] phrases = (String[]) returnedObj;

        System.out.println("Imprimiendo combinaciones...");
        printPhrases(phrases);

    }
}