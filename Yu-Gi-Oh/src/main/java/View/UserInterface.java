package View;

import Controller.Controller;
import View.Menus.*;

import java.util.Scanner;

public class UserInterface {
    public static LoginMenu loginMenu;
    public static MainMenu mainMenu;
    public static DuelMenu duelMenu;
    public static DeckMenu deckMenu;
    public static ScoreboardMenu scoreboardMenu;
    public static ProfileMenu profileMenu;
    public static ShopMenu shopMenu;
    public static ImportExportMenu importExportMenu;

    static {
        loginMenu = new LoginMenu();
        mainMenu = new MainMenu();
        duelMenu = new DuelMenu();
        deckMenu = new DeckMenu();
        scoreboardMenu = new ScoreboardMenu();
        profileMenu = new ProfileMenu();
        shopMenu = new ShopMenu();
        importExportMenu = new ImportExportMenu();
    }

    public static int run() {
        return inputHandler(inputScanner());
    } //done

    private static String inputScanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    } //done

    private static int inputHandler(String input) {
        switch (Controller.currentMenu) {
            case LOGIN -> {
                return loginMenu.commandHandler(input);
            }
            case MAIN -> {
                return mainMenu.commandHandler(input);
            }
            case DUEL -> {
                return duelMenu.commandHandler(input);
            }
            case DECK -> {
                return deckMenu.commandHandler(input);
            }
            case SCOREBOARD -> {
                return scoreboardMenu.commandHandler(input);
            }
            case PROFILE -> {
                return profileMenu.commandHandler(input);
            }
            case SHOP -> {
                return shopMenu.commandHandler(input);
            }
            case IMPORTEXPORT -> {
                return importExportMenu.commandHandler(input);
            }
        }
        return -1;
    } //done

    public static void returnResponse(String response) {
        System.out.println(response);
    } //done
}
