package Controller;

import View.Menus.*;

import java.util.Scanner;

public class Controller {//0
    public static Menus currentMenu;
    public static LoginMenu loginMenu;
    public static MainMenu mainMenu;
    public static DuelMenu duelMenu;
    public static DeckMenu deckMenu;
    public static ScoreboardMenu scoreboardMenu;
    public static ProfileMenu profileMenu;
    public static ShopMenu shopMenu;
    public static ImportExportMenu importExportMenu;

    static {
        currentMenu = Menus.LOGIN;
        loginMenu = new LoginMenu();
        mainMenu = new MainMenu();
        duelMenu = new DuelMenu();
        deckMenu = new DeckMenu();
        scoreboardMenu = new ScoreboardMenu();
        profileMenu = new ProfileMenu();
        shopMenu = new ShopMenu();
        importExportMenu = new ImportExportMenu();
    }

    public void run() {
        inputScanner();
    } //done

    private void inputScanner() {
        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            line = scanner.nextLine();
            inputHandler(line);
        } while (true);
    } //done

    private void inputHandler(String input) {
        switch (currentMenu) {
            case LOGIN -> loginMenu.commandHandler(input);
            case MAIN -> mainMenu.commandHandler(input);
            case DUEL -> duelMenu.commandHandler(input);
            case DECK -> deckMenu.commandHandler(input);
            case SCOREBOARD -> scoreboardMenu.commandHandler(input);
            case PROFILE -> profileMenu.commandHandler(input);
            case SHOP -> shopMenu.commandHandler(input);
            case IMPORTEXPORT -> importExportMenu.commandHandler(input);
        }
    } //done
}
