package controller;

import controller.processors.*;
import controller.threads.DataSaver;
import controller.threads.MusicPlayer;
import models.Account;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import models.Menus;

import java.util.Objects;

public class Core {
    public static Menus currentMenu;
    public static String version;
    public static MusicPlayer musicPlayer;
    public static DataSaver dataSaver;

    static {
        currentMenu = Menus.LOGIN;
        version = "1.0.0";
        musicPlayer = new MusicPlayer();
        dataSaver = new DataSaver();
    }

    public static void run() {
        ////Initialize
        Initializer();

        LoginMenuProcessor.getInstance();
        MainMenuProcessor.getInstance();
        DeckMenuProcessor.getInstance();
        ScoreboardMenuProcessor.getInstance();
        ProfileMenuProcessor.getInstance();
        ShopMenuProcessor.getInstance();
        ImportExportMenuProcessor.getInstance();

    } //done

    public static void Initializer() {
        System.out.println(MonsterCard.addMonsterCardFromJSON());
        System.out.println(MagicCard.addMagicCardFromJSON());
        System.out.println(Account.initializeAccounts());
    }

    public static void destruct() {
        System.out.println(Account.saveAccounts());
        System.exit(0);
    }
}
