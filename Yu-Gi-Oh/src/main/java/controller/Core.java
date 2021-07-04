package controller;

import controller.processors.*;
import graphics.others.MusicPlayer;
import models.Account;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.UserInterface;
import view.menus.Menus;

import java.util.Objects;

public class Core {
    public static Menus currentMenu;
    public static String version;
    public static MusicPlayer musicPlayer;

    static {
        currentMenu = Menus.LOGIN;
        version = "1.0.0";
        musicPlayer = new MusicPlayer();
        musicPlayer.setDaemon(true);
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
        System.out.println(MonsterCard.addMonsterCardFromJSON());
        System.out.println(MagicCard.addMagicCardFromJSON());
        System.out.println(Account.initializeAccounts());
    }

    public static void destruct() {
        System.out.println(Account.saveAccounts());
        System.exit(0);
    }
}
