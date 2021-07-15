package controller.processors;

import controller.Core;
import models.Account;
import models.cards.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.menus.Menus;

import static org.junit.jupiter.api.Assertions.*;

class DeckMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void commandDistributorTest() {
        Core.cardInitializer();
        DeckMenuProcessor deckMenuProcessor = new DeckMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");
        Processor.loggedInUser = account;

        String responseFor0 = "menu navigation is not possible";
        Assertions.assertEquals(deckMenuProcessor.process(0,"any menu"), responseFor0);

        String responseFor1 = "";
        Assertions.assertEquals(deckMenuProcessor.process(1,"any menu"), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.MAIN);

        String responseFor2 = "Deck Menu\n";
        Assertions.assertEquals(deckMenuProcessor.process(2,""), responseFor2);

        String responseFor3 = "deck created successfully!";
        Assertions.assertEquals(deckMenuProcessor.process(3,"my deck"), responseFor3);
        responseFor3 = "deck with name my deck already exists";
        Assertions.assertEquals(deckMenuProcessor.process(3,"my deck"), responseFor3);

        String responseFor4 = "deck with name DECK does not exist";
        Assertions.assertEquals(deckMenuProcessor.process(4,"DECK"), responseFor4);
        responseFor4 = "deck deleted successfully";
        Assertions.assertEquals(deckMenuProcessor.process(4,"my deck"), responseFor4);

        String responseFor5 = "deck with name DECK does not exist";
        Assertions.assertEquals(deckMenuProcessor.process(5,"DECK"), responseFor5);
        responseFor5 = "deck activated successfully";
        deckMenuProcessor.process(3, "MY deck");
        Assertions.assertEquals(deckMenuProcessor.process(5,"MY deck"), responseFor5);

        String responseFor6 = "invalid command";
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card card --card card2"), responseFor6);
        Assertions.assertEquals(deckMenuProcessor.process(6,"-d deck --deck deck2"), responseFor6);
        Assertions.assertEquals(deckMenuProcessor.process(6,"--side --side"), responseFor6);
        Assertions.assertEquals(deckMenuProcessor.process(6,"--force --force"), responseFor6);
        Assertions.assertEquals(deckMenuProcessor.process(6,"--side -k"), responseFor6);
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card cardName"), responseFor6);
        responseFor6 = "card with name cardName does not exist";
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card cardName --deck MY deck"), responseFor6);
        responseFor6 = "deck with name deckName does not exist";
        Processor.loggedInUser.addCard(Card.getCardByName("Battle OX"));
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card Battle OX --deck deckName"), responseFor6);
        responseFor6 = "card added to deck successfully";
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card Battle OX --deck MY deck --side"), responseFor6);
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card Battle OX --deck MY deck "), responseFor6);
        responseFor6 = "card added to deck forcefully! shame on cheater!";
        Assertions.assertEquals(deckMenuProcessor.process(6,"--card Battle OX --deck MY deck --force"), responseFor6);

        String responseFor7 = "invalid command";
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card card --card card2"), responseFor7);
        Assertions.assertEquals(deckMenuProcessor.process(7,"-d deck --deck deck2"), responseFor7);
        Assertions.assertEquals(deckMenuProcessor.process(7,"--side --side"), responseFor7);
        Assertions.assertEquals(deckMenuProcessor.process(7,"--force --force"), responseFor7);
        Assertions.assertEquals(deckMenuProcessor.process(7,"--side -k"), responseFor7);
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card cardName"), responseFor7);
        responseFor7 = "card with name cardName does not exist";
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card cardName --deck MY deck"), responseFor7);
        responseFor7 = "deck with name deckName does not exist";
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card Battle OX --deck deckName"), responseFor7);
        responseFor7 = "card with name Axe Raider does not exist in side deck";
        Processor.loggedInUser.addCard(Card.getCardByName("Axe Raider"));
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card Axe Raider --deck MY deck --side"), responseFor7);
        responseFor7 = "card with name Axe Raider does not exist in main deck";
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card Axe Raider --deck MY deck "), responseFor7);
        responseFor7 = "card removed form deck successfully";
        Assertions.assertEquals(deckMenuProcessor.process(7,"--card Battle OX --deck MY deck "), responseFor7);

        String responseFor8 = "Decks:\n" +
                "Active deck:\n" +
                "MY deck: main deck 1,side deck 1,invalid\n" +
                "Other decks:\n";
        Assertions.assertEquals(deckMenuProcessor.process(8,""), responseFor8);

        String responseFor9 = "Axe Raider:An axe-wielding monster of tremendous strength and agility.\n" +
                "Battle OX:A monster with tremendous power, it destroys enemies with a swing of its axe.";
        Assertions.assertEquals(deckMenuProcessor.process(9,""), responseFor9);

        String responseFor10 = "deck with name deckName does not exist";
        Assertions.assertEquals(deckMenuProcessor.process(10,"-d deckName"), responseFor10);
        Assertions.assertEquals(deckMenuProcessor.process(10,"-d deckName --side"), responseFor10);
        responseFor10 = "invalid command";
        Assertions.assertEquals(deckMenuProcessor.process(10,"-h deckName --side"), responseFor10);

        String responseFor11 = "there is no card with this name";
        Assertions.assertEquals(deckMenuProcessor.process(11,"cardName"), responseFor11);
        responseFor11 = "Name: Battle OX\n" +
                "Level: 4\n" +
                "Type: Beast-Warrior\n" +
                "ATK: 1700\n" +
                "DEF: 1000\n" +
                "Description: A monster with tremendous power, it destroys enemies with a swing of its axe.";
        Assertions.assertEquals(deckMenuProcessor.process(11,"Battle OX"), responseFor11);

        String responseFor99 = "* Commands in this Menu:\n" +
                "menu enter <name>\n" +
                "menu exit\n" +
                "menu show-current\n" +
                "deck create <name>\n" +
                "deck delete <name>\n" +
                "deck set-activate <name>\n" +
                "deck add-card <card name> <deck name> [side/main]\n" +
                "deck rm-card <card name> <deck name> [side/main]\n" +
                "deck show --all\n" +
                "deck show --cards\n" +
                "deck show <name> [side/main]\n" +
                "card show <name>\n" +
                "help\n";
        Assertions.assertEquals(deckMenuProcessor.process(99,""), responseFor99);
    }
}