package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        while (true) {
            System.out.println("""
            1.create table;
            2.drop table;
            3.save users;
            4.remove student by id;
            5.clean the table;
            """);
            int number = new Scanner(System.in).nextInt();
            switch (number) {
                case 1 -> userService.createUsersTable();
                case 2 -> userService.dropUsersTable();
                case 3 -> {
                    System.out.println(userService.saveUser("Aygerim", "Bektenova", (byte) 16));
                    System.out.println(userService.saveUser("Nuriza", "Muratova", (byte) 17));
                    System.out.println(userService.saveUser("Eliza ", "Ashirbaeva", (byte) 18));
                    System.out.println(userService.saveUser("Saltanat", "Nematilla kyzy", (byte) 20));
                }
                case 4 -> userService.removeUserById(new Scanner(System.in).nextLong());
                case 5 -> userService.getAllUsers();
                case 6 -> userService.cleanUsersTable();
            }
        }
    }
}