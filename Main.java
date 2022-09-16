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

    public static Pipe[] createArchitectureStructure() {
        Pipe pipe1 = new Pipe();
        Filter filter1 = new Tokenizer();
        Pipe pipe2 = new Pipe();
        Filter filter2 = new Combinator();
        Pipe pipe3 = new Pipe();
        Filter filter3 = new Alphabetizer();
        Pipe pipe4 = new Pipe();

        pipe1.setNextFilter(filter1);
        filter1.setNextPipe(pipe2);

        pipe2.setNextFilter(filter2);
        filter2.setNextPipe(pipe3);

        pipe3.setNextFilter(filter3);
        filter3.setNextPipe(pipe4);

        return new Pipe[] { pipe1, pipe4 };
    }

    public static void main(String[] args) {

        Pipe[] InNOutPipes = createArchitectureStructure();
        Pipe inPipe = InNOutPipes[0];
        Pipe outPipe = InNOutPipes[1];

        System.out.println("Ingrese la frase");
        String input = readInputString();

        inPipe.sendInfoToFilter(input);

        Object returnedObj = outPipe.getInformation();
        String[] phrases = (String[]) returnedObj;

        System.out.println("\nImprimiendo combinaciones...");
        printPhrases(phrases);

    }
}