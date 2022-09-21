package org;

import java.io.IOException;
import java.util.Scanner;

import org.PipesAndFilters.FilterPipeline;
import org.PipesAndFilters.exceptions.FilterCreationAbortedException;
import org.PipesAndFilters.exceptions.FilterExecutionException;
import org.json.simple.parser.ParseException;

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

        System.out.println("Ingrese la frase");
        String input = readInputString();

        FilterPipeline pipeline = new FilterPipeline();
        try {

            pipeline.parseConfig("org/config/config.json");
            pipeline.sendInput(input);
            Object returnedObj = pipeline.getOutput();
            String[] phrases = (String[]) returnedObj;
            System.out.println("\nImprimiendo combinaciones...");
            printPhrases(phrases);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException | FilterCreationAbortedException
                | FilterExecutionException e) {
            e.printStackTrace();
        }
    }
}