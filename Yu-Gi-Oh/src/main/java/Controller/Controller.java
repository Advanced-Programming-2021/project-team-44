package Controller;

import Controller.MenusProcessor.*;
import View.Menus.*;
import View.UserInterface;


public class Controller {
    public static Menus currentMenu;
    public static LoginMenuProcessor loginMenuProcessor;
    public static MainMenuProcessor mainMenuProcessor;
    public static DuelMenuProcessor duelMenuProcessor;
    public static DeckMenuProcessor deckMenuProcessor;
    public static ScoreboardMenuProcessor scoreboardMenuProcessor;
    public static ProfileMenuProcessor profileMenuProcessor;
    public static ShopMenuProcessor shopMenuProcessor;
    public static ImportExportMenuProcessor importExportMenuProcessor;

    static {
        currentMenu = Menus.LOGIN;
        loginMenuProcessor = new LoginMenuProcessor();
        mainMenuProcessor = new MainMenuProcessor();
        duelMenuProcessor = new DuelMenuProcessor();
        deckMenuProcessor = new DeckMenuProcessor();
        scoreboardMenuProcessor = new ScoreboardMenuProcessor();
        profileMenuProcessor = new ProfileMenuProcessor();
        shopMenuProcessor = new ShopMenuProcessor();
        importExportMenuProcessor = new ImportExportMenuProcessor();
    }

    public void run() {
        while (true) {
            int inputId = UserInterface.run();
            menuDistributor(inputId);
        }
    } //done

    private void menuDistributor(int inputId) {
        String response = "";
        switch (currentMenu) {
            case LOGIN -> {
                response = loginMenuProcessor.commandDistributor(inputId);
            }
            case MAIN -> {
                response = mainMenuProcessor.commandDistributor(inputId);
            }
            case DUEL -> {
                response = duelMenuProcessor.commandDistributor(inputId);
            }
            case DECK -> {
                response = deckMenuProcessor.commandDistributor(inputId);
            }
            case SCOREBOARD -> {
                response = scoreboardMenuProcessor.commandDistributor(inputId);
            }
            case PROFILE -> {
                response = profileMenuProcessor.commandDistributor(inputId);
            }
            case SHOP -> {
                response = shopMenuProcessor.commandDistributor(inputId);
            }
            case IMPORTEXPORT -> {
                response = importExportMenuProcessor.commandDistributor(inputId);
            }
        }
        UserInterface.returnResponse(response);
    }
}
