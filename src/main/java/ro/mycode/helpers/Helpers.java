package ro.mycode.helpers;

import java.util.Scanner;

public class Helpers {

    public static Scanner getScanner(String input) {
        if (input.equals("")) {
            return new Scanner(System.in);
        } else {
            return new Scanner(input);
        }
    }
    public static Scanner getScanner() {
        return getScanner("");
    }

    public static boolean exit(Scanner scanner) {
        System.out.println("Sigur doriti sa iesit din aplicatie?");
        char ans = scanner.nextLine().toLowerCase().charAt(0);
        switch (ans) {
            case 'y' -> {
                return true;
            }
            case 'q' -> {
                System.exit(0);
            }
        }
        return false;
    }

}
