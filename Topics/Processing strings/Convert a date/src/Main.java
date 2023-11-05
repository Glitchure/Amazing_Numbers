import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        String[] sTemp = date.split("-");
        String[] formattedDate = new String[3];

        for (int i = 0; i < sTemp.length; i++) {
            formattedDate[i] = sTemp[i + 1 > sTemp.length - 1 ? 0 : i + 1];
        }
        for (int i = 0; i < formattedDate.length; i++) {
            System.out.print(formattedDate[i]);
            if (i != formattedDate.length - 1) {
                System.out.print("/");
            }
        }
    }
}