package controller.processors;

import models.Account;
import models.Phases;
import models.Player;
import models.cards.MonsterCard;
import view.UserInterface;
import view.menus.Menus;

public class AIDuelMenuProcessor extends DuelMenuProcessor {
    private int whichPlayerIsAI;

    public AIDuelMenuProcessor() {
        super(Menus.AI_DUEL);
    }

    @Override
    public void gameInitialization(Account player1, Account player2, int rounds) {
        phase = Phases.DRAW;
        whoseTurn = 1;
        remainingRounds = rounds;
        if (player1 == null) {
            this.player1 = new Player(Account.getAccountByNickname("AI"));
            this.player2 = new Player(player2);
            whichPlayerIsAI = 1;
        }
        if (player2 == null) {
            this.player1 = new Player(player1);
            this.player2 = new Player(Account.getAccountByNickname("AI"));
            whichPlayerIsAI = 2;
        }
    }

    @Override
    public void execute() {
        //TODO
        if (whoseTurn == whichPlayerIsAI) {
            AIGetCommand(DuelMenuProcessor.phase);
        } else {
            String command = getActingPlayer().getCommand();
            String[] dividedCommand = commandHandler(command);
            String response = process(Integer.parseInt(dividedCommand[0]), dividedCommand[1]);
            UserInterface.returnResponse(response);
        }

        if (!ifRoundHasEnded()) execute();
        else cheatEndRound(getWinner(), getLoser());
    }

    @Override
    public void executeRound() {

    }

    @Override
    public void executeTurn() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void endRound(Player winner, Player loser) {

    }

    public void AIGetCommand(Phases phases) {
        switch (phases.stringName) {
            case "Draw Phase" -> AIDrawPhaseCommand();
            case "Standby Phase" -> AIStandbyPhaseCommand();
            case "Main Phase 1" -> AIMain1PhaseCommand();
            case "Battle Phase" -> AIBattlePhaseCommand();
            case "Main Phase 2" -> AIMain2PhaseCommand();
            case "End Phase" -> AIEndPhaseCommand();
        }
    }

    public void AIDrawPhaseCommand() {
        changePhase();
    }

    public void AIStandbyPhaseCommand() {
        changePhase();
    }

    public void AIMain1PhaseCommand() {
        //for (Card monsterCard : getAI().) {

        //}
    }

    public void AIBattlePhaseCommand() {

    }

    public void AIMain2PhaseCommand() {

    }

    public void AIEndPhaseCommand() {

    }

    public Player getAI() {
        switch (whichPlayerIsAI) {
            case 1 -> {
                return player1;
            }
            case 2 -> {
                return player2;
            }
        }
        return null;
    }

    public boolean canSummonMonster(MonsterCard monsterCard) {
        if (isSummonOrSetActionAvailable && !getAI().isMonsterZoneFull()) {
            if (monsterCard.getLevel() <= 4) return true;
            else if (monsterCard.getLevel() == 5 || monsterCard.getLevel() == 6) {
                return getAI().countMonstersInMonsterZone() >= 1;
            } else if (monsterCard.getLevel() > 6) {
                return getAI().countMonstersInMonsterZone() >= 2;
            }
        }
        return false;
    }
}

