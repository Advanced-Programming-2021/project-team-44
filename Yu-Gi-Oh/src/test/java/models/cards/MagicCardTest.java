package models.cards;

import controller.Core;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagicCardTest {

    @Test
    void magicCardTest() {
        Core.cardInitializer();
        MagicCard.magicCardsJsonParser();
        MagicCard.createMagicCard("Closed Forest");
        MagicCard.getMagicCardByName("Closed Forest").getStringForShow();
        MagicCard.getMagicCardByName("Dark Hole").getType();
        MagicCard.getMagicCardByName("Dark Hole").getStatus();
        MagicCard.getMagicCardByName("an");
        MagicCard.getMagicCardByName("Dark Hole").equals(MagicCard.getMagicCardByName("Dark Hole"));

    }
}