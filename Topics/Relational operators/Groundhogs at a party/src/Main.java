import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        // You can use scanner.nextBoolean() to read a boolean value
        int reeses = scanner.nextInt();
        boolean isWeekend = scanner.nextBoolean();

        if ((reeses >= 10 && reeses <= 20) && !isWeekend || (reeses >= 15 && reeses <= 25) && isWeekend) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}