package View;

import View.Menus.LoginMenu;
import View.Menus.Menu;

import java.util.Scanner;

public class UserInterface {

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
