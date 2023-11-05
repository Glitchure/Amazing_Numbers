import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int[] sort(int[] numbers) {
        // write your code here
        int[] sortedNumbers = new int[numbers.length];
        for (int i = 0; i < sortedNumbers.length; i++) {
            sortedNumbers[i] = numbers[indexOfMinOfArray(numbers)];
            numbers[indexOfMinOfArray(numbers)] = maxOfArray(numbers) + 1;
        }
        return sortedNumbers;
    }

    public static int indexOfMinOfArray (int[] array) {
        int min = array[0];
        int minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int maxOfArray(int[] array) {
        int max = array[0];
        for (int j : array) {
            if (j > max) {
                max = j;
            }
        }
        return max;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split("\\s+");
        int[] numbers = Arrays.stream(values)
                .mapToInt(Integer::parseInt)
                .toArray();
        numbers = sort(numbers);
        Arrays.stream(numbers).forEach(e -> System.out.print(e + " "));
    }
}