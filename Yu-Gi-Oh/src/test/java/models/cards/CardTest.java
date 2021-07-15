package models.cards;

import controller.Core;
import models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class CardTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    public void cardTest(){
        Core.cardInitializer();

        Assertions.assertNull(Card.getTypeOfCardByName("no card"));
        Assertions.assertEquals(Card.getTypeOfCardByName("Battle OX"), "monster");
        Assertions.assertEquals(Card.getTypeOfCardByName("Black Pendant"), "magic");

        Assertions.assertEquals(Card.cardNameFilter("Axe Raider"), "Axe Raider");

        Card.descriptionFilter("des");

        Card.getCardByName("Axe Raider").setName("Axe Raider");
        Card.getCardByName("Axe Raider").setDescription("An axe-wielding monster of tremendous strength and agility.");
        Assertions.assertEquals(Card.getCardByName("Axe Raider").getDescription(), "An axe-wielding monster of tremendous strength and agility.");
        Assertions.assertEquals(Card.getCardByName("Axe Raider").getPrice(), 3100);

        Card.getCardByName("an");

    }
}