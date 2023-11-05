import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = scanner.nextInt();

        if (n < s.length()) {
            int counter = 1;
            int index = n;
            while (true) {
                System.out.print(s.charAt(index));
                ++counter;
                if (counter > s.length()) {
                    break;
                } else {
                    ++index;
                    if (index > s.length() - 1) {
                        index = 0;
                    }
                }
            }
        } else {
            System.out.println(s);
        }
    }
}