package Controller;

import Controller.MenusProcessor.*;
import View.Menus.Menus;
import View.UserInterface;

import java.util.Objects;


public class Core {//0
    public static Menus currentMenu;

    static {
        currentMenu = Menus.LOGIN;
    }

    public void run() {
        new LoginMenuProcessor();
        new MainMenuProcessor();
        new DuelMenuProcessor();
        new DeckMenuProcessor();
        new ScoreboardMenuProcessor();
        new ProfileMenuProcessor();
        new ShopMenuProcessor();
        new ImportExportMenuProcessor();

        UserInterface.run();
    } //done

    public static String menuDistributor(int inputId) {
        return Objects.requireNonNull(Processor.getProcessorByName(currentMenu)).commandDistributor(inputId);
    }
}
