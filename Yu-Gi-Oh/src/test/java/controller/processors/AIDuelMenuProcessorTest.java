package controller.processors;

import controller.Core;
import models.Account;
import models.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIDuelMenuProcessorTest {

    @BeforeEach
    void accountsClear(){
        Account.accounts.clear();
    }
    @Test
    void duelTest() throws Exception {
        Core.Initializer();
        Account account1 = Account.getAccountByUsername("matinKing");
        Account account2 = Account.getAccountByUsername("matinKing2");
        MainMenuProcessor mainMenuProcessor = new MainMenuProcessor();
        PlayerDuelMenuProcessor playerDuelMenuProcessor = new PlayerDuelMenuProcessor();
        Processor.loggedInUser = account1;
        mainMenuProcessor.process(4, "-r 1 -s matinKing2");
        playerDuelMenuProcessor.getActingPlayer();
        playerDuelMenuProcessor.getOtherPlayer();
        playerDuelMenuProcessor.getActingPlayerBoard();
        playerDuelMenuProcessor.getPlayerByNumber(1);
        playerDuelMenuProcessor.getPlayerByNumber(3);
        playerDuelMenuProcessor.showBoard();
        playerDuelMenuProcessor.changeTurn();
        playerDuelMenuProcessor.getWinner();
        playerDuelMenuProcessor.getLoser();
        playerDuelMenuProcessor.selectedCard = playerDuelMenuProcessor.getActingPlayer().getCardFromHandZone(1);
        playerDuelMenuProcessor.flipSummonErrorChecker();
        playerDuelMenuProcessor.summon();
        playerDuelMenuProcessor.selectedCard = playerDuelMenuProcessor.getActingPlayer().getCardFromMonsterZone(1);
        playerDuelMenuProcessor.showSelectedCardErrorChecker();
        playerDuelMenuProcessor.directAttack();
        playerDuelMenuProcessor.ifRoundHasEnded();
        playerDuelMenuProcessor.monsterEffectsActivator();
        playerDuelMenuProcessor.showCard("Axe Raider");
        playerDuelMenuProcessor.setPosition("OO");
        playerDuelMenuProcessor.changePhase();
        playerDuelMenuProcessor.changeTurn();
        playerDuelMenuProcessor.surrender();
    }
}