package org;

import java.io.IOException;
import java.util.Scanner;

import org.PipesAndFilters.Structure;
import org.PipesAndFilters.exceptions.ConfigFileErrorException;
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

        Structure struct = new Structure();
        try {

            struct.parseConfig("org/config/config.json");
            struct.sendInput(input);
            Object returnedObj = struct.getOutput();
            String[] phrases = (String[]) returnedObj;
            System.out.println("\nImprimiendo combinaciones...");
            printPhrases(phrases);

        } catch (IOException | ParseException | ConfigFileErrorException | FilterCreationAbortedException
                | FilterExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}