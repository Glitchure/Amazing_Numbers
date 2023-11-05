package numbers;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] input;
    static boolean shouldBreak = false;

    public static void main(String[] args) {
//        write your code here
        System.out.println();
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
        while (true) {
            System.out.println("Enter a request: ");
            // Input is split into an array. This is more versatile because the program can take in more numbers and process them at a time
            input = scanner.nextLine().split(" ");
            System.out.println();
            // If there's only one number, analyze the single number, and if there's two numbers, generate and analyze a list
            if (input.length == 1) {
                NumberAnalyzer.analyzeSingleNumber(input);
                if (shouldBreak) {
                    shouldBreak = false;
                    System.out.println("Goodbye!");
                    break;
                }
            } else if (input.length == 2) {
                NumberAnalyzer.analyzeList(input);
            } else {
                NumberAnalyzer.findNumbersWithProperties(input);
            }
        }
    }

}