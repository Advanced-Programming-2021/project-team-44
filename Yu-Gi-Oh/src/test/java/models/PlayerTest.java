package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getCommand() {
    }

    @Test
    void getAccountTest() {


    }

    @Test
    void setAccount() {
    }

    @Test
    void getLp() {
    }

    @Test
    void setLp() {
    }

    @Test
    void getDeckTest() {
        Account account = new Account("palermo", "p123", "boomBoomCiao");
        Deck deck = new Deck("active deck");
        account.setActiveDeck(deck);
        Player player = new Player(account);
        Assertions.assertEquals(player.getDeck(), deck);
    }

    @Test
    void setDeck() {
    }

    @Test
    void getMonsterArea() {
    }

    @Test
    void setMonsterArea() {
    }

    @Test
    void getSpellArea() {
    }

    @Test
    void setSpellArea() {
    }

    @Test
    void getHandCards() {
    }

    @Test
    void setHandCards() {
    }

    @Test
    void getDeckCards() {
    }

    @Test
    void setDeckCards() {
    }

    @Test
    void getGraveyardCards() {
    }

    @Test
    void setGraveyardCards() {
    }

    @Test
    void getFieldZone() {
    }

    @Test
    void setFieldZone() {
    }

    @Test
    void isCheatActivated() {
    }

    @Test
    void setCheatActivated() {
    }
}