package controller.processors;

import controller.Core;
import models.Account;
import models.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void commandDistributor() {
        Core.cardInitializer();
        MainMenuProcessor mainMenuProcessor = new MainMenuProcessor();
        Account account = new Account("matinKing3","12345","matadysa3");
        Account account2 = new Account("matinKing4","12345","matadysa4");
        Processor.loggedInUser = account;

        String responseFor0 = "you can't enter this menu by this command";
        Assertions.assertEquals(mainMenuProcessor.process(0,"Duel"), responseFor0);
        responseFor0 = "";
        Assertions.assertEquals(mainMenuProcessor.process(0,"Deck"), responseFor0);
        Assertions.assertEquals(mainMenuProcessor.process(0,"Shop"), responseFor0);
        Assertions.assertEquals(mainMenuProcessor.process(0,"Scoreboard"), responseFor0);
        Assertions.assertEquals(mainMenuProcessor.process(0,"Profile"), responseFor0);
        Assertions.assertEquals(mainMenuProcessor.process(0,"Import/Export"), responseFor0);
        responseFor0 = "you are already in Main Menu!";
        Assertions.assertEquals(mainMenuProcessor.process(0,"Main"), responseFor0);
        responseFor0 = "invalid menu name";
        Assertions.assertEquals(mainMenuProcessor.process(0,"any"), responseFor0);

        String responseFor2 = "Main Menu\n";
        Assertions.assertEquals(mainMenuProcessor.process(2,""), responseFor2);

        String responseFor3 = "user logged out successfully!";
        Assertions.assertEquals(mainMenuProcessor.process(3,""), responseFor3);

        Processor.loggedInUser = account;
        String responseFor4 = "invalid command";
        Assertions.assertEquals(mainMenuProcessor.process(4,"-r 2 -s akbar --ali"), responseFor4);
        responseFor4 = "there is no player with this username";
        Assertions.assertEquals(mainMenuProcessor.process(4,"-r 1 -s akbar"), responseFor4);
        responseFor4 = "matinKing3 has no active deck";
        Assertions.assertEquals(mainMenuProcessor.process(4,"-r 1 -s matinKing4"), responseFor4);
        account.setActiveDeck(new Deck("deck"));
        responseFor4 = "matinKing4 has no active deck";
        Assertions.assertEquals(mainMenuProcessor.process(4,"-r 1 -s matinKing4"), responseFor4);
        account2.setActiveDeck(new Deck("deck2"));
        responseFor4 = "matinKing3 deck is invalid";
        Assertions.assertEquals(mainMenuProcessor.process(4,"-r 1 -s matinKing4"), responseFor4);

        String responseFor5 = "invalid command";
        Assertions.assertEquals(mainMenuProcessor.process(5,""), responseFor5);
        Assertions.assertEquals(mainMenuProcessor.process(5,"-r 3 --ali"), responseFor5);
        responseFor5 = "matinKing3 deck is invalid";
        Assertions.assertEquals(mainMenuProcessor.process(5,"-r 3"), responseFor5);

        String responseFor99 =
                "* Commands in this Menu:\n" +
                "menu enter <name>\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "user logout\n" +
                "duel --new <opponent username> <rounds>\n" +
                "duel --new --ai <rounds>\n" +
                "help\n";
        Assertions.assertEquals(mainMenuProcessor.process(99,""), responseFor99);

        String responseFor1 = "user logged out successfully!";
        Assertions.assertEquals(mainMenuProcessor.process(1,""), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.LOGIN);
    }
}