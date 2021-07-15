package controller.processors;

import controller.Core;
import models.Account;
import models.Phases;
import models.Player;
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
        playerDuelMenuProcessor.set();
        playerDuelMenuProcessor.selectedCard = playerDuelMenuProcessor.getActingPlayer().getCardFromMonsterZone(1);
        playerDuelMenuProcessor.showSelectedCardErrorChecker();
        playerDuelMenuProcessor.directAttack();
        playerDuelMenuProcessor.ifRoundHasEnded();
        playerDuelMenuProcessor.monsterEffectsActivator();
        playerDuelMenuProcessor.showCard("Axe Raider");
        playerDuelMenuProcessor.setPosition("OO");
        playerDuelMenuProcessor.changePhase();
        playerDuelMenuProcessor.changeTurn();
        playerDuelMenuProcessor.selectedCard = playerDuelMenuProcessor.getActingPlayer().getCardFromHandZone(1);
        playerDuelMenuProcessor.summon();
        playerDuelMenuProcessor.selectedCard = playerDuelMenuProcessor.getActingPlayer().getCardFromMonsterZone(1);
        playerDuelMenuProcessor.attack(1);
        playerDuelMenuProcessor.process(0,"");
        playerDuelMenuProcessor.process(1,"");
        playerDuelMenuProcessor.process(2,"");
        playerDuelMenuProcessor.process(3,"");
        playerDuelMenuProcessor.process(4,"");
        playerDuelMenuProcessor.process(5,"");
        playerDuelMenuProcessor.process(6,"");
        playerDuelMenuProcessor.process(7,"");
        playerDuelMenuProcessor.process(8,"");
        playerDuelMenuProcessor.process(9,"");
        playerDuelMenuProcessor.process(10,"");
        playerDuelMenuProcessor.process(11,"");
        playerDuelMenuProcessor.process(12,"");
        playerDuelMenuProcessor.process(13,"");
        playerDuelMenuProcessor.process(15,"");
        playerDuelMenuProcessor.process(17,"");
        playerDuelMenuProcessor.process(18,"");
        playerDuelMenuProcessor.process(19,"");
        playerDuelMenuProcessor.process(20,"");
        playerDuelMenuProcessor.process(30, "");
        playerDuelMenuProcessor.process(31,"");
        playerDuelMenuProcessor.process(99,"");
        playerDuelMenuProcessor.help();
        playerDuelMenuProcessor.increaseLp(0);
        playerDuelMenuProcessor.changePhase();
        playerDuelMenuProcessor.changePhase();
        playerDuelMenuProcessor.changePhase();
        playerDuelMenuProcessor.changePhase();

        playerDuelMenuProcessor.surrender();
    }

    @Test
    void AITest(){
        //Core.Initializer();
        //Account account1 = Account.getAccountByUsername("matinKing");
       // MainMenuProcessor mainMenuProcessor = new MainMenuProcessor();
        //AIDuelMenuProcessor aiDuelMenuProcessor = new AIDuelMenuProcessor();
        //Processor.loggedInUser = account1;
        //aiDuelMenuProcessor.gameInitialization(null,account1, 1);
        //aiDuelMenuProcessor.getAI();
        //aiDuelMenuProcessor.getHuman();
        //aiDuelMenuProcessor.AIHandZoneSortedByAttackPoint();
        //aiDuelMenuProcessor.AIGetCommand(Phases.DRAW);
        //aiDuelMenuProcessor.AIGetCommand(Phases.MAIN1);
    }
}