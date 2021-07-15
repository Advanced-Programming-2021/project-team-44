package models;

import controller.Core;
import models.cards.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void boardTest() {
        Core.cardInitializer();
        Account account = new Account("akbar", "1234", "achbar");
        Deck deck = new Deck("akbar deck");
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        account.setActiveDeck(deck);
        Player player = new Player(account);
        Board board = new Board(player);

        Assertions.assertEquals(board.getPlayer(), player);
        Assertions.assertEquals(board.getMonsterZoneState(1), "E");
        board.setMagicZoneState(1, "F");
        Assertions.assertEquals(board.getMagicZoneState(1), "F");
        board.setGraveyardState("2");
        Assertions.assertEquals(board.getGraveyardState(), "2");
        board.setFieldZoneState("1");
        Assertions.assertEquals(board.getFieldZoneState(), "1");

        Assertions.assertEquals(board.getStringAsOpponent(), "achbar:8000\n" +
                "\tc\tc\tc\tc\tc\tc\n" +
                "2\n" +
                "\tE\tE\tF\tE\tE\t\t\n" +
                "\tE\tE\tE\tE\tE\t\t\n" +
                "0\t\t\t\t\t\tE\n");
        Assertions.assertEquals(board.getStringAsSelf(), "E\t\t\t\t\t\t0\n" +
                "\tE\tE\tE\tE\tE\t\t\n" +
                "\tE\tE\tF\tE\tE\t\t\n" +
                "\t\t\t\t\t\t2\n" +
                "c\tc\tc\tc\tc\tc\t\n" +
                "achbar:8000");
    }
}