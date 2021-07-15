package controller.processors;

import controller.Core;
import models.Account;
import models.Phases;
import models.Player;
import models.cards.MonsterCard;
import view.UserInterface;
import view.menus.Menus;

import java.util.ArrayList;

public class AIDuelMenuProcessor extends DuelMenuProcessor {
    private int whichPlayerIsAI;

    public AIDuelMenuProcessor() {
        super(Menus.AI_DUEL);
    }

    @Override
    public void gameInitialization(Account player1, Account player2, int rounds) {
        remainingRounds = rounds;
        allRounds = remainingRounds;
        if (player1 == null) {
            this.player1 = new Player(Account.getAccountByNickname("AI"));
            this.player2 = new Player(player2);
            whichPlayerIsAI = 1;
        }
        else {
            this.player1 = new Player(player1);
            this.player2 = new Player(Account.getAccountByNickname("AI"));
            whichPlayerIsAI = 2;
        }
        newRoundInitializer();
        execute();
    }//done

    @Override
    public void execute() {
        executeRound();
        endGame();
        Core.currentMenu = Menus.MAIN;
    }

    @Override
    public void executeRound() {
        executeTurn();
        if (hasAnyoneSurrendered) return;
        if (ifRoundHasEnded()) {
            if (remainingRounds > 0
                    && !(allRounds == 3
                    && (player1.getRoundsWon() == 2
                    || player2.getRoundsWon() == 2))) {
                newRoundInitializer();
                //TODO check for card swap between decks
                executeRound();
            } else return;
        }
        executeRound();
    }

    @Override
    public void executeTurn() {
        String command;
        if(getActingPlayer().getAccount().getNickname().equals("AI")) command = AIGetCommand(phase);
        else command = getActingPlayer().getCommand();
        String[] dividedCommand = commandHandler(command);
        String response = process(Integer.parseInt(dividedCommand[0]), dividedCommand[1]);
        UserInterface.returnResponse(response);
    }

    @Override
    public void endGame() {
        //Giving money and score
        if (getAI().getRoundsWon() > getHuman().getRoundsWon()) {
            coinPayer(false);
            System.out.println("\n" + "AI"
                    + " won the whole match with score: "
                    + getAI().getScore()
                    + " - "
                    + getHuman().getScore());
        } else {
            coinPayer(true);
            System.out.println("\n" + getHuman().getAccount().getNickname()
                    + " won the whole match with score: "
                    + getHuman().getScore()
                    + " - "
                    + getAI().getScore());
        }
    }

    @Override
    public void endRound(Player winner, Player loser) {
        winner.winsRound();
        ifRoundHasEnded = true;
        System.out.println("\n" + winner.getAccount().getNickname()
                + " won the match with score: "
                + winner.getScore()
                + " - "
                + loser.getScore());
    }

    public String AIGetCommand(Phases phases) {
        switch (phases.stringName) {
            case "Draw Phase" -> {
                return AIDrawPhaseCommand();
            }
            case "Standby Phase" -> {
                return AIStandbyPhaseCommand();
            }
            case "Main Phase 1" -> {
                return AIMain1PhaseCommand();
            }
            case "Battle Phase" -> {
                return AIBattlePhaseCommand();
            }
            case "Main Phase 2" -> {
                return AIMain2PhaseCommand();
            }
            case "End Phase" -> {
                return AIEndPhaseCommand();
            }
        }
        return "";
    }

    public String AIDrawPhaseCommand() {
        return "next phase\n";
    }

    public String AIStandbyPhaseCommand() {
        return "next phase\n";
    }

    public String AIMain1PhaseCommand() {
        StringBuilder command = new StringBuilder();
        for (int i = 0; i < getAI().getHandZoneMonstersCount() ; i++) {
            if(canAISummonMonster(AIHandZoneSortedByAttackPoint().get(i))){
                command.append("select --hand ").append(getAI().getPositionOfCardInTheHandZone(MonsterCard.getMonsterCardByName(AIHandZoneSortedByAttackPoint().get(i).getName()))).append("\n");
                command.append("summon").append("\n");
                if(AIHandZoneSortedByAttackPoint().get(i).getLevel() > 4 && AIHandZoneSortedByAttackPoint().get(i).getLevel() < 7){
                    command.append(getAI().getFirstFullPositionInMonsterZone()).append("\n");
                }
                else if(AIHandZoneSortedByAttackPoint().get(i).getLevel() > 6){
                    command.append(getAI().getFirstFullPositionInMonsterZone()).append("\n");
                    command.append(getAI().getSecondFullPositionInMonsterZone()).append("\n");
                }
            }
        }
        if(command.toString().equals("")) return "next phase\n";
        return command.toString();
    } //done

    public String AIBattlePhaseCommand() {
        StringBuilder command = new StringBuilder();
        if(canAIAttackDirect()){
            for (int AIMonsterLocation = 1; AIMonsterLocation < 6; AIMonsterLocation++){
                if(getAI().getCardFromMonsterZone(AIMonsterLocation) != null){
                    if(!hasAttackedInThisTurn.contains(getAI().getCardFromMonsterZone(AIMonsterLocation))){
                        command.append("select --monster ").append(AIMonsterLocation).append("\n");
                        command.append("attack direct");
                    }
                }
            }
        }else{
            for (int AIMonsterLocation = 1; AIMonsterLocation < 6; AIMonsterLocation++) {
                if(getAI().getCardFromMonsterZone(AIMonsterLocation) != null){
                    if(!hasAttackedInThisTurn.contains(getAI().getCardFromMonsterZone(AIMonsterLocation))){
                        for (int humanMonsterLocation = 1; humanMonsterLocation < 6; humanMonsterLocation++) {
                            if(getHuman().getCardFromMonsterZone(humanMonsterLocation) != null){
                                if(getOtherPlayerBoard().getMonsterZoneState(humanMonsterLocation).equals("DH")||
                                        isAttackUseful(getAI().getCardFromMonsterZone(AIMonsterLocation), getHuman().getCardFromMonsterZone(humanMonsterLocation), humanMonsterLocation)){
                                    command.append("select --monster ").append(AIMonsterLocation).append("\n");
                                    command.append("attack ").append(humanMonsterLocation).append("\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        if(command.toString().equals("")) return "next phase\n";
        return command.toString();
    } //done

    public String AIMain2PhaseCommand() {
        return "next phase\n";
    }

    public String AIEndPhaseCommand() {
        return "next phase\n";
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

    public Player getHuman(){
        switch (whichPlayerIsAI) {
            case 1 -> {
                return player2;
            }
            case 2 -> {
                return player1;
            }
        }
        return null;
    }

    public void coinPayer(boolean winner){
        if(winner){
            getHuman().getAccount().increaseScore(allRounds * 1000);
            getHuman().getAccount().increaseCoin(allRounds * (1000 + getHuman().getMaxLp()));
        } else {
            getHuman().getAccount().increaseCoin(allRounds * 100);
        }
    }

    public boolean canAISummonMonster(MonsterCard monsterCard) {
        if (isSummonOrSetActionAvailable && !getAI().isMonsterZoneFull()) {
            if (monsterCard.getLevel() <= 4) return true;
            else if (monsterCard.getLevel() == 5 || monsterCard.getLevel() == 6) {
                return getAI().howManyMonstersInTheGame() >= 1;
            } else{
                return getAI().howManyMonstersInTheGame() >= 2;
            }
        }
        return false;
    }

    public boolean isAttackUseful(MonsterCard attacker, MonsterCard defender, int defenderLocation){
        if(getOtherPlayerBoard().getMonsterZoneState(defenderLocation).equals("OO")){
            return attacker.getAttackPoint() >= defender.getAttackPoint();
        }
        else return attacker.getAttackPoint() >= defender.getDefensePoint();
    }

    public boolean canAIAttackDirect(){
        return getHuman().howManyMonstersInTheGame() == 0;
    }
    public ArrayList<MonsterCard> AIHandZoneSortedByAttackPoint(){
        return new ArrayList<>();
    }
}

