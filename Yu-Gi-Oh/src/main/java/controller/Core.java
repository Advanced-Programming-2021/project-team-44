package controller;

import controller.processors.*;
import view.UserInterface;
import view.menus.Menus;

import java.util.Objects;

public class Core {//0
    public static Menus currentMenu;

    static {
        currentMenu = Menus.LOGIN;
    }

    public void run() {
        ////Initialize

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

    public static String menuDistributor(int inputId, String commandArguments) {
        return Objects.requireNonNull(Processor.getProcessorByName(currentMenu)).commandDistributor(inputId, commandArguments);
    }

    public static void Initializer() {
        //TODO
    }
}
