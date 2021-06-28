package controller;

import controller.processors.*;
import models.cards.MagicCard;
import models.cards.MonsterCard;
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
        Initializer();

        new LoginMenuProcessor();
        new MainMenuProcessor();
        new PlayerDuelMenuProcessor();
        new AIDuelMenuProcessor();
        new DeckMenuProcessor();
        new ScoreboardMenuProcessor();
        new ProfileMenuProcessor();
        new ShopMenuProcessor();
        new ImportExportMenuProcessor();

        UserInterface.run();
    } //done

    public static String menuDistributor(int inputId, String commandArguments) {
        return Objects.requireNonNull(Processor.getProcessorByName(currentMenu)).process(inputId, commandArguments.trim());
    }

    public static void Initializer() {
        //TODO INITIALIZER
        //Users Initialize
        //Cards Initialize
        MonsterCard.addMonsterCardFromJSON();
        MagicCard.addMagicCardFromJSON();
    }
}
