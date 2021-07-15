package controller.processors;

import controller.Core;
import models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void commandDistributor() {
        Core.cardInitializer();
        ScoreboardMenuProcessor scoreboardMenuProcessor = new ScoreboardMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");
        Processor.loggedInUser = account;

        String responseFor0 = "menu navigation is not possible";
        Assertions.assertEquals(scoreboardMenuProcessor.process(0,"any menu"), responseFor0);

        String responseFor1 = "";
        Assertions.assertEquals(scoreboardMenuProcessor.process(1,"any menu"), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.MAIN);

        String responseFor2 = "Scoreboard Menu\n";
        Assertions.assertEquals(scoreboardMenuProcessor.process(2,""), responseFor2);

        Assertions.assertEquals(scoreboardMenuProcessor.process(3,""), "1- matadysa: 0\n");

        String responseFor99 = "* Commands in this Menu:\n" +
                "menu enter <name>\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "scoreboard show\n" +
                "help\n";
        Assertions.assertEquals(scoreboardMenuProcessor.process(99,""), responseFor99);
    }

}