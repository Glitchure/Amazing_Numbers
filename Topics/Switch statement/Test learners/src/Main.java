import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int number = scanner.nextInt();
        if (number == 1) {
            System.out.println("Yes!");
        } else if (number > 0 && number < 5) {
            System.out.println("No!");
        } else {
            System.out.println("Unknown number");
        }
    }
}