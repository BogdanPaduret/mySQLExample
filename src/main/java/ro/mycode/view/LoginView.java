package ro.mycode.view;

import ro.mycode.helpers.Helpers;
import ro.mycode.model.User;
import ro.mycode.repository.UserRepo;

import java.util.Scanner;

import static ro.mycode.helpers.Helpers.*;

public class LoginView implements View {

    //instance variables
    User user;
    UserRepo userRepo;

    //constructor
    public LoginView() {
        userRepo = new UserRepo();
    }
    public LoginView(String repositoryAddress, String username, String password) {
        this();
        userRepo.setJdbcURL(repositoryAddress);
        userRepo.setUsername(username);
        userRepo.setPassword(password);
    }

    private void menu() {
        String string = "";

        if (user != null) {
            string += "Sunteti logat ca " + user.getFirstName() + " " + user.getLastName() + "\n";
        }

        string += "Apasati 1 pentru a va loga";
        string += "\nApasati 2 pentru a va inregistra";
        string += "\nApasati 3 pentru a intra in aplicatie";
        string += "\nApasati 9 pentru a vizualiza toti utilizatorii";
        string += "\nApasati 0 pentru a iesi";

        System.out.println(string);
    }

    @Override
    public void play() {
        boolean running = true;
        int choice = -1;

        Scanner scanner = getScanner();

        while (running) {
            menu();

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Trebuie introdus un numar");
            }

            switch (choice) {
                case 0 -> running = !Helpers.exit(scanner);
                case 1 -> login(scanner);
                case 2 -> register(scanner);
//                case 3 -> startApp();
//                case 9 -> showAll();
            }
        }
    }

    private void login(Scanner scanner) {
        User user = getUserByName(scanner);
        if (user == null) {
            System.out.println("A aparut o eroare.");

        }
        if (user == null) {
            System.out.println("Utilizatorul nu a fost gasit.");
        }

        this.user = user;
    }

    private void register(Scanner scanner) {
        User user = getUserByName(scanner);
        if () {
        }
    }

    private User getUserByName(Scanner scanner) {
        System.out.println("Introduceti prenumele si numele separate prin virgula");
        String[] input = scanner.nextLine().split(",");
        String firstName = input[0];
        String lastName = input[1];

        User user = userRepo.get(firstName, lastName);
        if (user == null) {
            user = userRepo.get(lastName, firstName);
            if (user != null) {
                System.out.println("Utilizator gasit dar ati introdus numele si prenumele pe dos.");
            }
        }
        return user;
    }

    private User getUserById(Scanner scanner) {
        System.out.println("Introduceti ID-ul user-ului.");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Trebuie sa introduceti un numar");
        }
        return userRepo.get(id);
    }
}
