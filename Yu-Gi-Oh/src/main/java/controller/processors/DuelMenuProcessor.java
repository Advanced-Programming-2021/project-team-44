package controller.processors;

import controller.Core;
import models.*;
import models.cards.Card;
import view.menus.Menus;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class DuelMenuProcessor extends Processor {
    protected static Scanner duelScanner;
    protected Phases phase;
    protected int whoseTurn;
    protected Player player1;
    protected Player player2;
    protected Board player1Board;
    protected Board player2Board;

    public DuelMenuProcessor(Menus Name) {
        super(Name);
    }

    abstract public void gameInitialization(Account player1, Account player2, int rounds);

    abstract public void execute();

    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(menu enter|" +
                "menu exit|" +
                "menu show-current|" +
                "|" +
                "|" +
                "|" +
                "|" +
                "|" +
                "|" +
                "|" +
                "|" +
                ")\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "" -> output[0] = "3";

            }
            output[1] = matcher.group(2);
        }
        return output;
    }

    //Error Checker
    protected String showCardErrorChecker(String arguments) {
        String response;
        if (Card.getCardByName(arguments) == null) response = "there is no card with this name";
        else {
            response = showCard(arguments);
        }
        return response;
    }

    protected String selectCardErrorChecker(String arguments) { //user and opponent
        return null;
    }

    protected String deselectErrorChecker(String arguments) {
        return null;
    }

    protected String summonErrorChecker(String arguments) {
        return null;
    }

    protected String setErrorChecker(String arguments) { //monster and spell and trap
        return null;
    }

    protected String setPositionErrorChecker(String arguments) {
        return null;
    }

    protected String flipSummonErrorChecker(String arguments) {
        return null;
    }

    protected String attackErrorChecker(String arguments) {
        return null;
    }

    protected String directAttackErrorChecker(String arguments) {
        return null;
    }

    protected String activateEffectErrorChecker(String arguments) {
        return null;
    }

    protected String showGraveyardErrorChecker(String arguments) {
        return null;
    }

    protected String showSelectedCardErrorChecker(String arguments) {
        return null;
    }

    //Command Performer
    protected String showCard(String cardName) {
        return Card.getCardByName(cardName).getStringForShow();
    }

    protected String selectCard(String arguments) { //user and opponent
        return null;
    }

    protected String deselect(String arguments) {
        return null;
    }

    protected String summon(String arguments) {
        return null;
    }

    protected String set(String arguments) { //monster and spell and trap
        return null;
    }

    protected String setPosition(String arguments) {
        return null;
    }

    protected String flipSummon(String input) {
        return null;
    }

    protected String attack(String input) {
        return null;
    }

    protected String directAttack(String input) {
        return null;
    }

    protected String activateEffect(String input) {
        return null;
    }

    protected String showGraveyard(String input) {
        return null;
    }

    protected String showSelectedCard(String input) {
        return null;
    }

    //Utils
    protected String showBoard(Account selfAccount, Account opponentAccount) {
        return null;
    }

    protected String showPhase() {
        return phase.name;
    }

    protected int showPlayerPoints(Player player) {
        return 0;
    }

    protected ArrayList<Card> checkForCardEffectActivation() {
        return null;
    }

    protected boolean checkForDuelEnd() {
        return false;
    }

    protected void endDuel() {
    }


    //Cheats
    protected void increaseMoneyCheat(int amount) {
    }

    protected void increaseLPCheat(int amount) {
    }

    protected void setWinnerCheat(String nickname) {
    }

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> {}
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
        Core.currentMenu = Menus.MAIN;
        endDuel();
    }
}
