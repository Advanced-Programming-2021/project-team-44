package models;

import controller.Core;
import models.cards.Card;
import models.cards.MonsterCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void addOrRemoveCardToMainDeckTest() {
        Core.cardInitializer();
        Deck deck1 = new Deck("My first");
        deck1.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck1.addCardToMainDeck(Card.getCardByName("Baby dragon"));
        Assertions.assertEquals(deck1.mainDeckCounter(), 2);
        deck1.removeCardFromMainDeck("Axe Raider");
        Assertions.assertEquals(deck1.mainDeckCounter(), 1);
        deck1.removeCardFromMainDeck("Battle OX");
        Assertions.assertEquals(deck1.mainDeckCounter(), 1);
    }

    @Test
    void addOrRemoveCardToSideDeckTest() {
        Core.cardInitializer();
        Deck deck1 = new Deck("My first");
        deck1.addCardToSideDeck(Card.getCardByName("Axe Raider"));
        Assertions.assertEquals(deck1.sideDeckCounter(), 1);
        deck1.removeCardFromSideDeck("Axe Raider");
        Assertions.assertEquals(deck1.sideDeckCounter(), 0);
        deck1.removeCardFromSideDeck("Battle OX");
        Assertions.assertEquals(deck1.sideDeckCounter(), 0);
    }

    @Test
    void getNameTest() {
        Deck deck = new Deck("deckName");
        Assertions.assertEquals(deck.getName(), "deckName");
    }

    @Test
    void isMainDeckFullTest() {
        Core.cardInitializer();
        Deck deck = new Deck("Main deck");
        Assertions.assertFalse(deck.isMainDeckFull());
        for (int i = 0; i < 60; i++) {
            deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        }
        Assertions.assertTrue(deck.isMainDeckFull());

    }

    @Test
    void isSideDeckFullTest() {
        Core.cardInitializer();
        Deck deck = new Deck("Side deck");
        Assertions.assertFalse(deck.isSideDeckFull());
        for (int i = 0; i < 15; i++) {
            deck.addCardToSideDeck(Card.getCardByName("Battle OX"));
        }
        Assertions.assertTrue(deck.isSideDeckFull());
    }

    @Test
    void isCardExistedInSideDeckTest() {
        Core.cardInitializer();
        Deck deck = new Deck("DECK");
        deck.addCardToSideDeck(Card.getCardByName("Axe Raider"));
        Assertions.assertTrue(deck.isCardExistedInSideDeck("Axe Raider"));
        Assertions.assertFalse(deck.isCardExistedInSideDeck("Battle OX"));
        deck.removeCardFromSideDeck("Axe Raider");
        Assertions.assertFalse(deck.isCardExistedInSideDeck("Axe Raider"));
    }

    @Test
    void isCardExistedInMainDeckTest() {
        Core.cardInitializer();
        Deck deck = new Deck("DECK");
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        Assertions.assertTrue(deck.isCardExistedInMainDeck("Axe Raider"));
        Assertions.assertFalse(deck.isCardExistedInMainDeck("Battle OX"));
        deck.removeCardFromMainDeck("Axe Raider");
        Assertions.assertFalse(deck.isCardExistedInMainDeck("Axe Raider"));
    }

    @Test
    void isDeckValidTest() {
        Core.cardInitializer();
        Deck deck = new Deck("validInvalidDeck");
        Assertions.assertFalse(deck.isDeckValid());
        for (int i = 0; i < 50; i++) {
            deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        }
        Assertions.assertTrue(deck.isDeckValid());
        for (int i = 0; i < 20; i++) {
            deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        }
        Assertions.assertFalse(deck.isDeckValid());
    }

    @Test
    void getMainDeckCardsTest() {
        Core.cardInitializer();
        Deck deck = new Deck("abas bo azar");
        ArrayList<Card> cards = new ArrayList<>();
        Assertions.assertEquals(deck.getMainDeckCards(), cards);
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        cards.add(Card.getCardByName("Axe Raider"));
        cards.add(Card.getCardByName("Battle OX"));
        Assertions.assertEquals(deck.getMainDeckCards(), cards);
    }

    @Test
    void getSideDeckCardsTest() {
        Core.cardInitializer();
        Deck deck = new Deck("abas bo azar");
        ArrayList<Card> cards = new ArrayList<>();
        Assertions.assertEquals(deck.getSideDeckCards(), cards);
        deck.addCardToSideDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToSideDeck(Card.getCardByName("Battle OX"));
        cards.add(Card.getCardByName("Axe Raider"));
        cards.add(Card.getCardByName("Battle OX"));
        Assertions.assertEquals(deck.getSideDeckCards(), cards);
    }

    @Test
    void showDeckTest() {
        Core.cardInitializer();
        Deck deck = new Deck("taj");
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Advanced Ritual Art"));
        deck.addCardToSideDeck(Card.getCardByName("Baby dragon"));
        deck.addCardToSideDeck(Card.getCardByName("Black Pendant"));
        String stringForMain = """
                Deck: taj
                Main deck:
                Monsters:
                Axe Raider:An axe-wielding monster of tremendous strength and agility.
                Spell and Traps:
                Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster. You must also send Normal Monsters from your Deck to the Graveyard whose total Levels equal the Level of that Ritual Monster.""";
        Assertions.assertEquals(deck.showDeck("Main"), stringForMain);
        String stringForSide = """
                Deck: taj
                Side deck:
                Monsters:
                Baby dragon:Much more than just a child, this dragon is gifted with untapped power.
                Spell and Traps:
                Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.""";
        Assertions.assertEquals(deck.showDeck("Side"), stringForSide);
    }

    @Test
    void getStringForShowAllDecksTest() {
        Core.cardInitializer();
        Deck deck = new Deck("crown");
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToSideDeck(Card.getCardByName("Battle OX"));
        String string = "crown: main deck 2,side deck 1,invalid";
        Assertions.assertEquals(deck.getStringForShowAllDecks(), string);
    }

    @Test
    void ifMaxOfCardIsReachedTest() {
        Core.cardInitializer();
        Deck deck = new Deck("first deck");
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        Assertions.assertFalse(deck.ifMaxOfCardIsReached("Axe Raider"));
        deck.addCardToSideDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToSideDeck(Card.getCardByName("Axe Raider"));
        Assertions.assertTrue(deck.ifMaxOfCardIsReached("Axe Raider"));
    }

    @Test
    void cloneTest(){
        Core.cardInitializer();
        Deck deck = new Deck("achbar");
        deck.addCardToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addCardToSideDeck(Card.getCardByName("Monster Reborn"));
        deck.clone();
    }

    @Test
    void settersTest(){
        Deck deck = new Deck("mah");
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(Card.getCardByName("Battle OX"));
        cards.add(Card.getCardByName("Monster Reborn"));
        deck.setMainDeckCards(cards);
        Assertions.assertEquals(cards, deck.getMainDeckCards());
        deck.setSideDeckCards(cards);
        Assertions.assertEquals(cards, deck.getSideDeckCards());
    }

    @Test
    void DeckDeepSerializedTest(){
        new DeckDeepSerialized("khorshid", "poshtesh", "be mas");
    }
}