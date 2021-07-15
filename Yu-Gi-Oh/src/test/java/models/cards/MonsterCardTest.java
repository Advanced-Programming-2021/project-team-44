package models.cards;

import controller.Core;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterCardTest {

    @Test
    void monsterCardTest() {
        Core.cardInitializer();
        MonsterCard.createMonsterCard("Axe Raider");
        MonsterCard.monsterCardsJsonParser();
        MonsterCard.getMonsterCardByName("Axe Raider").getName();
        MonsterCard.getMonsterCardByName("Axe Raider").getCardType();
        MonsterCard.getMonsterCardByName("Axe Raider").getAttribute();
        MonsterCard.getMonsterCardByName("Axe Raider").getMonsterType();
        MonsterCard.getMonsterCardByName("Axe Raider").getAttackPoint();
        MonsterCard.getMonsterCardByName("Axe Raider").getDefensePoint();
        MonsterCard.getMonsterCardByName("Axe Raider").getDescription();
        MonsterCard.getMonsterCardByName("Axe Raider").isAvailableForNormalSummon();
        MonsterCard.getMonsterCardByName("Axe Raider").isAvailableForRitualSummon();
        MonsterCard.getMonsterCardByName("Axe Raider").isAvailableForSpecialSummon();
        MonsterCard.getMonsterCardByName("Axe Raider").getEffectType();
        MonsterCard.getMonsterCardByName("Axe Raider").increaseAttackPoint(0);
        MonsterCard.getMonsterCardByName("Axe Raider").increaseDefensePoint(0);
        MonsterCard.getMonsterCardByName("Axe Raider").getStringForShow();
        MonsterCard.getMonsterCardByName("Axe Raider").getHashMap();
        MonsterCard.getMonsterCardByName("Axe Raider").equals(MonsterCard.getMonsterCardByName("Axe Raider"));
    }

}