package controller.processors;

import models.*;
import models.cards.Card;
import view.menus.Menus;

import java.util.ArrayList;

public class DuelMenuProcessor extends Processor {
    private Phases phase;
    private int turn;
    private Player player1;
    private Player player2;
    private Board player1Board;
    private Board player2Board;
    private boolean ifAI;

    public DuelMenuProcessor() {
        super(Menus.DUEL);
    }

    public void gameInitialization(Account player1, Account player2, int rounds) {
        ifAI = player1 == null || player2 == null;
        if (ifAI) {

        } else {
            this.player1 = new Player(player1);
            this.player2 = new Player(player2);
        }
        phase = Phases.DRAW;
    }

    //Error Checker
    private String showCard(String input) {
        return null;
    }

    private String selectCardErrorChecker(String input) { //user and opponent
        return null;
    }

    private String deselectErrorChecker(String input) {
        return null;
    }

    private String summonErrorChecker(String input) {
        return null;
    }

    private String setErrorChecker(String input) { //monster and spell and trap
        return null;
    }

    private String setPositionErrorChecker(String input) {
        return null;
    }

    private String flipSummonErrorChecker(String input) {
        return null;
    }

    private String attackErrorChecker(String input) {
        return null;
    }

    private String directAttackErrorChecker(String input) {
        return null;
    }

    private String activateEffectErrorChecker(String input) {
        return null;
    }

    private String showGraveyardErrorChecker(String input) {
        return null;
    }

    private String showSelectedCardErrorChecker(String input) {
        return null;
    }

    //Command Performer
    private String showCard(Card card) {
        return null;
    }

    private String selectCard(String input) { //user and opponent
        return null;
    }

    private String deselect(String input) {
        return null;
    }

    private String summon(String input) {
        return null;
    }

    private String set(String input) { //monster and spell and trap
        return null;
    }

    private String setPosition(String input) {
        return null;
    }

    private String flipSummon(String input) {
        return null;
    }

    private String attack(String input) {
        return null;
    }

    private String directAttack(String input) {
        return null;
    }

    private String activateEffect(String input) {
        return null;
    }

    private String showGraveyard(String input) {
        return null;
    }

    private String showSelectedCard(String input) {
        return null;
    }


    private String showBoard(Account selfAccount, Account opponentAccount) {
        return null;
    }

    private String showPhase() {
        return phase.name;
    }

    private int showPlayerPoints(Player player) {
        return 0;
    }

    private ArrayList<Card> checkForCardEffectActivation() {
        return null;
    }

    public boolean isAI() {
        return ifAI;
    }


    //Cheats
    private void increaseMoneyCheat(int amount) {}

    private void increaseLPCheat(int amount) {}

    private void setWinnerCheat(String nickname) {}

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> {

            }
            case 1 -> {
                response = "";
                exitMenu();
            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        return null;
    }

    @Override
    protected void enterMenu(Menus menu) {

    }

    @Override
    protected void exitMenu() {

    }
}
