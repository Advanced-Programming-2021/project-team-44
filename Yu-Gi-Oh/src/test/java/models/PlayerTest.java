package models;

import controller.Core;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }

    @Test
    void playerTest() {
        Core.cardInitializer();
        Account account = new Account("ali", "1234", "aliKing");
        Deck deck = new Deck("anghezi");
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        deck.addCardToMainDeck(Card.getCardByName("Battle OX"));
        account.setActiveDeck(deck);
        Player player = new Player(account);
        player.setAccount(account);
        Assertions.assertEquals(player.getAccount(), account);
        player.getBoard();
        player.increaseLp(200);
        Assertions.assertEquals(player.getLp(), 8200);
        player.setCheatActivated(true);
        assertTrue(player.isCheatActivated());
        player.winsRound();
        Assertions.assertEquals(player.getScore(), 1000);
        assertFalse(player.ownsCard(Card.getCardByName("Axe Raider")));
        //Assertions.assertNull(player.getCardState(Card.getCardByName("Axe Raider")));
        Assertions.assertEquals(player.getRoundsWon(), 1);
        Assertions.assertEquals(player.getMaxLp(), 8200);
        Assertions.assertNull(player.getCardFromMonsterZone(1));
        Assertions.assertNull(player.getCardFromMagicZone(1));
        Assertions.assertNull(player.getCardFromFieldZone());
        Assertions.assertNull(player.getCardFromHandZone(2));
        Assertions.assertFalse(player.ifCardsMatchTheLevelOfTheRitualMonster(MonsterCard.getMonsterCardByName("Axe Raider")));
        player.setCardInHandZone(Card.getCardByName("Battle OX"), 1);
        player.setCardInHandZone(Card.getCardByName("Battle OX"), 2);
        player.setCardInHandZone(Card.getCardByName("Battle OX"), 3);
        player.setCardInHandZone(Card.getCardByName("Battle OX"), 4);
        player.setCardInHandZone(Card.getCardByName("Battle OX"), 5);
        player.setCardInHandZone(Card.getCardByName("Battle OX"), 6);
        Assertions.assertFalse(player.ifHandContainsAdvancedRitualArtCard());
        player.setCardInHandZone(Card.getCardByName("Advanced Ritual Art"), 1);
        Assertions.assertTrue(player.ifHandContainsAdvancedRitualArtCard());
        Assertions.assertEquals(player.getPositionOfCardInTheHandZone(Card.getCardByName("Advanced Ritual Art")), 1);
        Assertions.assertEquals(player.getPositionOfCardInTheHandZone(Card.getCardByName("Axe Raider")), -1);
        Assertions.assertEquals(player.getHandZoneCount(), 6);
        player.setFieldZone(Card.getCardByName("Axe Raider"));

        player.getSecondFullPositionInMonsterZone();
        Assertions.assertEquals(player.getFirstFullPositionInMonsterZone(), -1);
        Assertions.assertEquals(player.getFirstFreePositionInMagicZone(), 1);
        player.getFirstFreePositionInHandZone();
        player.getFirstFreePositionInMagicZone();
        Assertions.assertEquals(player.getHandZoneMonstersCount(), 5);

        player.setCardInMonsterZone(MonsterCard.getMonsterCardByName("Axe Raider"), 1);
        Assertions.assertFalse(player.isMonsterZoneFull());
        player.setCardInMonsterZone(MonsterCard.getMonsterCardByName("Axe Raider"), 2);
        player.setCardInMonsterZone(MonsterCard.getMonsterCardByName("Axe Raider"), 3);
        player.setCardInMonsterZone(MonsterCard.getMonsterCardByName("Axe Raider"), 4);
        player.setCardInMonsterZone(MonsterCard.getMonsterCardByName("Axe Raider"), 5);
        Assertions.assertTrue(player.isMonsterZoneFull());

        Assertions.assertEquals(player.getFirstFreePositionInMonsterZone(), -1);
        player.getSecondFullPositionInMonsterZone();

        Assertions.assertEquals(player.howManyMonstersInTheGame(), 5);

        Assertions.assertFalse(player.ifMonsterZoneContains(MonsterCard.getMonsterCardByName("Battle OX")));
        Assertions.assertFalse(player.ifMagicZoneContains(MagicCard.getMagicCardByName("Advanced Ritual Art")));

        player.destroyMonster(1);
        player.setCardInMonsterZone(MonsterCard.getMonsterCardByName("Axe Raider"), 1);
        Assertions.assertEquals(player.getMonsterCardIndex(MonsterCard.getMonsterCardByName("Axe Raider")), 1);
        Assertions.assertEquals(player.getMonsterCardIndex(MonsterCard.getMonsterCardByName("Battle OX")), -1);

        player.setCardInMagicZone(MagicCard.getMagicCardByName("Dark Hole"), 1);
        Assertions.assertFalse(player.isMagicZoneFull());
        player.setCardInMagicZone(MagicCard.getMagicCardByName("Advanced Ritual Art"), 2);
        player.setCardInMagicZone(MagicCard.getMagicCardByName("Advanced Ritual Art"), 3);
        player.setCardInMagicZone(MagicCard.getMagicCardByName("Advanced Ritual Art"), 4);
        player.setCardInMagicZone(MagicCard.getMagicCardByName("Advanced Ritual Art"), 5);
        Assertions.assertTrue(player.isMagicZoneFull());
        player.destroyMagic(1);

        Assertions.assertEquals(player.getMagicCardIndex(MagicCard.getMagicCardByName("Advanced Ritual Art")), 2);
        Assertions.assertEquals(player.getMagicCardIndex(MagicCard.getMagicCardByName("Black Pendant")), -1);

        player.removeCardFromHandZone(Card.getCardByName("Battle OX"));
        player.setHandCards();
    }

}