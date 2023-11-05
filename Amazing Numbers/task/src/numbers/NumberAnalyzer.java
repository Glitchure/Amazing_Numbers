package numbers;

import java.util.Arrays;
import java.util.Objects;

public class NumberAnalyzer {
    public static void analyzeSingleNumber(String[] input) {
        // If the input is completely empty, then the instructions will be printed
        if (Objects.equals(input[0], "")) {
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
            return;
        }
        // A lot of parsing is used. Hopefully that's not a bad thing
        long number = Long.parseLong(input[0]);
        // Checking special conditions
        if (number == 0) {
            // shouldBreak is used to break the while loop in main that contains this function
            Main.shouldBreak = true;
        } else if (number < 1) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        } else {
            System.out.println("Properties of " + number);
            System.out.println("\tbuzz: " + checkIfBuzz(number));
            System.out.println("\tduck: " + checkIfDuck(number));
            System.out.println("\tpalindromic: " + checkIfPalindrome(number));
            System.out.println("\tgapful: " + checkIfGapful(number));
            System.out.println("\tspy: " + checkIfSpy(number));
            System.out.println("\tsquare: " + checkIfSquare(number));
            System.out.println("\tsunny: " + checkIfSunny(number));
            System.out.println("\tjumping: " + checkIfJumping(number));
            System.out.println("\thappy: " + checkIfHappy(number));
            System.out.println("\tsad: " + !checkIfHappy(number));
            System.out.println("\teven: " + checkIfEven(number));
            System.out.println("\todd: " + !checkIfEven(number));
            System.out.println();
        }
    }

    public static void analyzeList(String[] input) {
        // Checking special conditions
        if (Long.parseLong(input[1]) < 1) {
            System.out.println("The second parameter should be a natural number.\n");
            return;
        }
        if (Long.parseLong(input[0]) < 1) {
            System.out.println("The first parameter should be a natural number.\n");
            return;
        }
        // Generates a list of numbers where the first item is the number itself and every item after that is the previous item plus one
        long[] list = new long[Integer.parseInt(input[1])];
        for (int i = 0; i < list.length; i++) {
            list[i] = Long.parseLong(input[0]) + i;
        }
        // Goes through each number in the list and analyzes each one
        for (long l: list) {
            System.out.println(l + " is " + getPropertiesOfNumber(l));
        }
        System.out.println();
    }

    public static void findNumbersWithProperties(String[] input) {
        final String[] properties = {"EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"};
        final String propertiesString = "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";
        if (Long.parseLong(input[1]) < 1) {
            System.out.println("The second parameter should be a natural number.\n");
            return;
        }
        if (Long.parseLong(input[0]) < 1) {
            System.out.println("The first parameter should be a natural number.\n");
            return;
        }
        StringBuilder negativeQueries = new StringBuilder();
        boolean isNegativeQuery = false;
        StringBuilder invalidProperties = new StringBuilder();
        for (int i = 2; i < input.length; i++) {
            if (input[i].charAt(0) == '-') {
                isNegativeQuery = true;
                input[i] = input[i].substring(1);
            }
            for (int j = 0; j < properties.length; j++) {
                if (properties[j].equalsIgnoreCase(input[i])) {
                    if (isNegativeQuery) {
                        negativeQueries.append(input[i]);
                        negativeQueries.append(", ");
                        input[i] = "-" + input[i];
                        isNegativeQuery = false;
                    }
                    break;
                } else if (j == properties.length - 1) {
                    if (isNegativeQuery) {
                        invalidProperties.append("-");
                    }
                    invalidProperties.append(input[i].toUpperCase());
                    invalidProperties.append(", ");
                    break;
                }
            }
        }
        if (!invalidProperties.isEmpty() && invalidProperties.length() > 2) {
            if (", ".equals(invalidProperties.substring(invalidProperties.length() - 2, invalidProperties.length()))) {
                invalidProperties.delete(invalidProperties.length() - 2, invalidProperties.length());
            }
        }
        String[] negativeQueriesArray = new String[0];
        if (!negativeQueries.isEmpty() && negativeQueries.length() > 2) {
            if (", ".equals(negativeQueries.substring(negativeQueries.length() - 2, negativeQueries.length()))) {
                negativeQueries.delete(negativeQueries.length() - 2, negativeQueries.length());
            }
            negativeQueriesArray = negativeQueries.toString().toLowerCase().split(", ");
        }
        if (!invalidProperties.isEmpty()) {
            System.out.println("The properties [" + invalidProperties + "] are wrong.");
            System.out.println("Available properties: " + propertiesString);
            return;
        }

        String[] chosenProperties = new String[input.length - 2 - negativeQueriesArray.length];
        int chosenPropertiesIndex = 0;
        for (int i = 2; i < input.length; i++) {
            if (input[i].charAt(0) != '-') {
                chosenProperties[chosenPropertiesIndex] = input[i].toLowerCase();
                ++chosenPropertiesIndex;
            }
        }

        /*
            Check for direct opposites
         */

        if (Arrays.asList(chosenProperties).contains("even") && Arrays.asList(chosenProperties).contains("odd")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(chosenProperties).contains("duck") && Arrays.asList(chosenProperties).contains("spy")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(chosenProperties).contains("sunny") && Arrays.asList(chosenProperties).contains("square")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(chosenProperties).contains("happy") && Arrays.asList(chosenProperties).contains("sad")) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(negativeQueriesArray).contains("even") && Arrays.asList(negativeQueriesArray).contains("odd")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(negativeQueriesArray).contains("duck") && Arrays.asList(negativeQueriesArray).contains("spy")) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(negativeQueriesArray).contains("sunny") && Arrays.asList(negativeQueriesArray).contains("square")) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        if (Arrays.asList(negativeQueriesArray).contains("happy") && Arrays.asList(negativeQueriesArray).contains("sad")) {
            System.out.println("The request contains mutually exclusive properties: [HAPPY, SAD]");
            System.out.println("There are no numbers with these properties.");
            return;
        }
        for (String s : negativeQueriesArray) {
            for (String t : chosenProperties) {
                if (s.equals(t)) {
                    System.out.println("The request contains mutually exclusive properties: [" + t.toUpperCase() + ", -" + s.toUpperCase() + "]");
                    return;
                }
            }
        }


        long startingNumber = Long.parseLong(input[0]);
        long maxNumbers = Long.parseLong(input[1]);
        int validNumbersCounter = 0;
        for (long i = startingNumber; validNumbersCounter < maxNumbers; i++) {
            String propertiesOfNumber = getPropertiesOfNumber(i);
            int chosenPropertiesCounter = 0;

            for (String s : chosenProperties) {
                if (propertiesOfNumber.contains(s)) {
                    ++chosenPropertiesCounter;
                }
            }
            boolean shouldContinue = false;
            for (String s : negativeQueriesArray) {
                if (propertiesOfNumber.contains(s)) {
                    shouldContinue = true;
                    break;
                }
            }
            if (shouldContinue) {
                continue;
            }
            if (chosenPropertiesCounter == input.length - 2 - negativeQueriesArray.length) {
                System.out.println(i + " is " + propertiesOfNumber);
                ++validNumbersCounter;
            }

        }
    }

    public static String getPropertiesOfNumber(long number) {
        StringBuilder properties = new StringBuilder();
        int propertiesTracker = 0;
        int numOfProperties = checkNumberOfProperties(number);

        if (checkIfBuzz(number)) {
            properties.append("buzz");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfDuck(number)) {
            properties.append("duck");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfPalindrome(number)) {
            properties.append("palindromic");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfGapful(number)) {
            properties.append("gapful");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfSpy(number)) {
            properties.append("spy");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfSquare(number)) {
            properties.append("square");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfSunny(number)) {
            properties.append("sunny");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfJumping(number)) {
            properties.append("jumping");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfHappy(number)) {
            properties.append("happy");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (!checkIfHappy(number)) {
            properties.append("sad");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (checkIfEven(number)) {
            properties.append("even");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        if (!checkIfEven(number)) {
            properties.append("odd");
            ++propertiesTracker;
            if (propertiesTracker < numOfProperties) {
                properties.append(", ");
            }
        }

        return properties.toString();
    }

    public static int checkNumberOfProperties(long number) {
        int numOfProperties = 0;
        if (checkIfEven(number)) {
            ++numOfProperties;
        }
        if (!checkIfEven(number)) {
            ++numOfProperties;
        }
        if (checkIfBuzz(number)) {
            ++numOfProperties;
        }
        if (checkIfDuck(number)) {
            ++numOfProperties;
        }
        if (checkIfPalindrome(number)) {
            ++numOfProperties;
        }
        if (checkIfGapful(number)) {
            ++numOfProperties;
        }
        if (checkIfSpy(number)) {
            ++numOfProperties;
        }
        if (checkIfSquare(number)) {
            ++numOfProperties;
        }
        if (checkIfSunny(number)) {
            ++numOfProperties;
        }
        if (checkIfJumping(number)) {
            ++numOfProperties;
        }
        if (checkIfHappy(number)) {
            ++numOfProperties;
        }
        if (!checkIfHappy(number)) {
            ++numOfProperties;
        }
        return numOfProperties;
    }

    public static boolean checkIfEven(long number) {
        return number % 2 == 0;
    }

    public static boolean checkIfBuzz(long number) {
        return number % 7 == 0 || (number - 7) % 10 == 0;

    }

    public static boolean checkIfDuck(long number) {
        String temp = Long.toString(number);
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfPalindrome(long number) {
        String numberTemp = Long.toString(number);
        char[] arrayTemp = new char[numberTemp.length()];
        char[] reversedArrayTemp = new char[numberTemp.length()];

        for (int i = 0; i < arrayTemp.length; i++) {
            arrayTemp[i] = numberTemp.charAt(i);
        }
        int indexTemp = reversedArrayTemp.length - 1;
        for (char c : arrayTemp) {
            reversedArrayTemp[indexTemp] = c;
            indexTemp--;
        }
        return Arrays.equals(arrayTemp, reversedArrayTemp);
    }

    public static boolean checkIfGapful(long number) {
        String stringNumber = Long.toString(number);
        if (stringNumber.length() < 3) {
            return false;
        } else {
            return number % Integer.parseInt(Character.toString(stringNumber.charAt(0)) + stringNumber.charAt(stringNumber.length() - 1)) == 0;
        }
    }

    public static boolean checkIfSpy(long number) {
        int sum = 0;
        int product = 1;
        String stringNumber = Long.toString(number);

        for (int i = 0; i < Long.toString(number).length(); i++) {
            sum += Integer.parseInt(Character.toString(stringNumber.charAt(i)));
        }

        for (int i = 0; i < Long.toString(number).length(); i++) {
            product *= Integer.parseInt(Character.toString(stringNumber.charAt(i)));
        }

        return sum == product;
    }

    public static boolean checkIfSquare(long number) {
        return Math.sqrt(number) == (int) Math.sqrt(number);
    }

    public static boolean checkIfSunny(long number) {
        return checkIfSquare(number + 1);
    }

    public static boolean checkIfJumping(long number) {
        String stringNumber = Long.toString(number);
        if (stringNumber.length() != 1) {
            for (int i = 1; i < stringNumber.length(); i++) {
                if (Math.abs(Long.parseLong(stringNumber.substring(i, i + 1)) - Long.parseLong(stringNumber.substring(i - 1, i))) == 1) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkIfHappy(long number) {
        long[] checkedNumbers = new long[100];
        long currentNumber = number;
        long sum = 0;
        while (true) {
            String stringNumber = Long.toString(currentNumber);
            for (int i = 0; i < stringNumber.length(); i++) {
                sum += (long) Math.pow(Long.parseLong(stringNumber.substring(i, i + 1)), 2);
            }
            if (sum == 1) {
                return true;
            }
            for (long checkedNumber : checkedNumbers) {
                if (checkedNumber == 0) {
                    break;
                } else if (checkedNumber == sum) {
                    return false;
                }
            }

            for (int i = 0; i < checkedNumbers.length; i++) {
                if (checkedNumbers[i] == 0) {
                    checkedNumbers[i] = sum;
                    break;
                }
            }
            currentNumber = sum;
            sum = 0;
        }
    }
}
