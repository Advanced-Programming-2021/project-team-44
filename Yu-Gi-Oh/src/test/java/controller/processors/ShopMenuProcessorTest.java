package controller.processors;

import controller.Core;
import models.Account;
import models.cards.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import models.Menus;

class ShopMenuProcessorTest {

    @Test
    void commandDistributorTest() {
        Core.Initializer();
        ShopMenuProcessor shopMenuProcessor = new ShopMenuProcessor();
        Account account = new Account("matinKing","12345","matadysa");
        Processor.loggedInUser = account;
        String responseFor0;
        String responseFor1;
        String responseFor2;
        String responseFor3;
        String responseFor4;
        String responseFor5;

        responseFor0 = "menu navigation is not possible";
        Assertions.assertEquals(shopMenuProcessor.process(0,"any menu"), responseFor0);

        responseFor1 = "";
        Assertions.assertEquals(shopMenuProcessor.process(1,"any menu"), responseFor1);
        Assertions.assertEquals(Core.currentMenu, Menus.MAIN);

        responseFor2 = "Shop Menu";
        Assertions.assertEquals(shopMenuProcessor.process(2,""), responseFor2);

        responseFor3 = "there is no card with this name";
        Assertions.assertEquals(shopMenuProcessor.process(3,"Not a card"), responseFor3);
        responseFor3 = "not enough money";
        Assertions.assertEquals(shopMenuProcessor.process(3,"Axe Raider"), responseFor3);
        account.increaseCoin(10000);
        responseFor3 = "card bought successfully";
        Assertions.assertEquals(shopMenuProcessor.process(3,"Axe Raider"), responseFor3);
        Assertions.assertNotNull(account.getCardByName("Axe Raider"));

        //responseFor4 =

        responseFor5 = "there is no card with this name";
        Assertions.assertEquals(shopMenuProcessor.process(5,"Not a card"), responseFor5);
        responseFor5 = Card.getCardByName("Axe Raider").getStringForShow();
        Assertions.assertEquals(shopMenuProcessor.process(5,"Axe Raider"), responseFor5);
        responseFor5 = Card.getCardByName("Advanced Ritual Art").getStringForShow();
        Assertions.assertEquals(shopMenuProcessor.process(5,"Advanced Ritual Art"), responseFor5);
    }

}