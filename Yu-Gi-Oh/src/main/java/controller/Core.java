package controller;

import controller.processors.*;
import models.Account;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.UserInterface;
import view.menus.Menus;

import javax.sound.midi.Soundbank;
import java.util.Objects;

public class Core {
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
        cardInitializer();
        accountInitializer();
    }

    public static void destruct() {
        System.out.println(Account.saveAccounts());
        System.exit(0);
    }

    public static void cardInitializer(){
        System.out.println(MonsterCard.addMonsterCardFromJSON());
        System.out.println(MagicCard.addMagicCardFromJSON());
    }

    public static void accountInitializer(){
        System.out.println(Account.initializeAccounts());
        System.out.println(Account.accounts.size());
    }
}
