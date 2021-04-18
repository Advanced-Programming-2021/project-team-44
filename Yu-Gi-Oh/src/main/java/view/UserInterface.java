package view;

import view.menus.LoginMenu;
import view.menus.Menu;

import java.util.Scanner;

public class UserInterface {//0

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Menu.setScanner(scanner);
        Menu loginMenu = new LoginMenu();
        loginMenu.execute();
    } //done

    public static void returnResponse(String response) {
        System.out.println(response);
    } //done
}
