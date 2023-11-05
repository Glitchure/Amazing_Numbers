import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        long firstNum = scanner.nextLong();
        String operation = scanner.next();
        long secondNum = scanner.nextLong();

        switch (operation) {
            case "+":
                System.out.println(firstNum + secondNum);
                break;
            case "-":
                System.out.println(firstNum - secondNum);
                break;
            case "*":
                System.out.println(firstNum * secondNum);
                break;
            case "/":
                if (secondNum == 0) {
                    System.out.println("Division by 0!");
                } else {
                    System.out.println(firstNum / secondNum);
                }
                break;
            default:
                System.out.println("Unknown operator");
        }
    }
}
