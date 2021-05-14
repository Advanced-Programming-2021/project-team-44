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
    protected Card selectedCard;

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
                "card show|" +
                //Cheats
                "use cheat|" +
                "increase|" +
                "select --hand|" +
                "duel set-winner|" +
                //Main
                "select -d|" +
                "select|" +
                "next phase|" +
                "summon|" +
                "set --position|" + "set -p|" +
                "set|" +
                "flip-summon|" +
                "attack direct|" +
                "attack|" +
                "activate effect|" +
                "show graveyard|" +
                "card show --selected|" + "card show -s|" +
                "cancel|" +
                "surrender|" +
                ")\\b(?:\\s+(.*))?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "menu enter" -> output[0] = "0";
                case "menu exit" -> output[0] = "1";
                case "menu show-current" -> output[0] = "2";
                case "card show" -> output[0] = "3";
                case "select" -> output[0] = "4";
                case "select -d" -> output[0] = "5";
                case "next phase" -> output[0] = "6";
                case "summon" -> output[0] = "7";
                case "set" -> output[0] = "8";
                case "set --position", "set -p" -> output[0] = "9";
                case "flip-summon" -> output[0] = "10";
                case "attack" -> output[0] = "11";
                case "attack direct" -> output[0] = "12";
                case "activate effect" -> output[0] = "13";
                case "show graveyard" -> output[0] = "14";
                case "card show --selected", "card show -s" -> output[0] = "15";
                case "cancel" -> output[0] = "16";
                case "surrender" -> output[0] = "17";
                case "use cheat" -> output[0] = "18";
                case "increase" -> output[0] = "19";
                case "select --hand" -> output[0] = "20";
                case "duel set-winner" -> output[0] = "21";
            }
            output[1] = matcher.group(2);
        }
        return output;
    }

    //Error Checker
    ////Main
    protected String showCardErrorChecker(String arguments) {
        String response;
        if (Card.getCardByName(arguments) == null) response = "there is no card with this name";
        else {
            response = showCard(arguments);
        }
        return response;
    } //done

    protected String selectCardErrorChecker(String arguments) { //user and opponent
        String response = "";
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        enum SelectType {MONSTER, SPELL, FIELD, HAND}
        SelectType type = null;
        Boolean ofOpponent = null;
        String selectedPositionString = null;
        Integer selectedPosition = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--monster", "-m" -> {
                    if (type != null) return "invalid command";
                    type = SelectType.MONSTER;
                    selectedPositionString = matcher.group(2);
                }
                case "--spell", "-s" -> {
                    if (type != null) return "invalid command";
                    type = SelectType.SPELL;
                    selectedPositionString = matcher.group(2);
                }
                case "--field", "-f" -> {
                    if (type != null) return "invalid command";
                    type = SelectType.FIELD;
                }
                case "--hand", "-h" -> {
                    if (type != null) return "invalid command";
                    type = SelectType.HAND;
                    selectedPositionString = matcher.group(2);
                }
                case "--opponent", "-o" -> {
                    if (ofOpponent != null) return "invalid command";
                    ofOpponent = true;
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (ofOpponent == null) ofOpponent = false;
        if (type == null) return "invalid command";
        if (type == SelectType.HAND && ofOpponent) return "invalid command";
        if (type == SelectType.MONSTER || type == SelectType.SPELL || type == SelectType.HAND) {
            try {
                selectedPosition = Integer.parseInt(selectedPositionString);
            } catch (Exception e) {
                return "invalid command";
            }
        }

        if ((type == SelectType.MONSTER || type == SelectType.SPELL) && (selectedPosition < 1 || selectedPosition > 5))
            response = "invalid selection";
        else if (type == SelectType.HAND && (selectedPosition < 1 || selectedPosition > 6))
            response = "invalid selection";
        else {
            switch (type) {
                case MONSTER -> response = selectCard("monster", selectedPosition, ofOpponent);
                case SPELL -> response = selectCard("spell", selectedPosition, ofOpponent);
                case FIELD -> response = selectCard("field", null, ofOpponent);
                case HAND -> response = selectCard("hand", selectedPosition, null);
            }
        }
        return response;
    } //done

    protected String deselectErrorChecker() {
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else {
            response = "card deselected";
            deselect();
        }
        return response;
    } //done

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

    protected String cancelErrorChecker(String arguments) {
        return null;
    }

    protected String surrenderErrorChecker(String arguments) {
        return null;
    }

    ////Cheats
    protected String increasePropertyErrorChecker(String arguments) {
        return null;
    }

    protected String selectHandErrorChecker(String arguments) {
        return null;
    }

    protected String setWinnerErrorChecker(String arguments) {
        return null;
    }

    //Command Performer
    ////Main
    protected String showCard(String cardName) {
        return Card.getCardByName(cardName).getStringForShow();
    } //done

    protected String selectCard(String set, Integer position, Boolean ofOpponent) { //user and opponent
        String response = "card selected";
        Card tmpCard = null;
        switch (set) {
            case "monster" -> {
                if (ofOpponent) tmpCard = getOtherPlayerBoard().getCardFromMonsterArea(position);
                else tmpCard = getActingPlayerBoard().getCardFromMonsterArea(position);
            }
            case "spell" -> {
                if (ofOpponent) tmpCard = getOtherPlayerBoard().getCardFromMagicArea(position);
                else tmpCard = getActingPlayerBoard().getCardFromMagicArea(position);
            }
            case "field" -> {
                if (ofOpponent) tmpCard = getOtherPlayerBoard().getCardFromFieldZone();
                else tmpCard = getActingPlayerBoard().getCardFromFieldZone();
            }
            case "hand" -> {
                if (ofOpponent) tmpCard = getOtherPlayerBoard().getCardFromHandArea(position);
                else tmpCard = getActingPlayerBoard().getCardFromHandArea(position);

            }
        }
        if (tmpCard == null) response = "no card found in the given position";
        else selectedCard = tmpCard;
        return response;
    } //done

    protected void deselect() {
        selectedCard = null;
    } //done

    protected String changePhase() {
        String response = "";
        switch (phase) {
            case DRAW -> phase = Phases.STANDBY;
            case STANDBY -> phase = Phases.MAIN1;
            case MAIN1 -> phase = Phases.BATTLE;
            case BATTLE -> phase = Phases.MAIN2;
            case MAIN2 -> phase = Phases.END;
            case END -> phase = Phases.DRAW;
        }
        response = "phase: " + phase.stringName;
        if (phase == Phases.DRAW) {
            if (whoseTurn == 1) whoseTurn = 2;
            else if (whoseTurn == 2) whoseTurn = 1;
            response += "\n" + "its " + getActingPlayer().getAccount().getNickname() + "'s turn";
        }
        return response;
    } //done

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

    ////Cheats
    protected String useCheat() {
        if (getActingPlayer().isCheatActivated()) return "cheats already activated";
        else {
            getActingPlayer().setCheatActivated(true);
            return "cheats activated successfully";
        }
    }

    protected String increaseProperty(String arguments) {
        return null;
    }

    protected String selectHand(String arguments) {
        return null;
    }

    protected String setWinnerCheat(String arguments) {
        return null;
    }

    //Utils
    protected String showBoard(Account selfAccount, Account opponentAccount) {
        return null;
    }

    protected String showPhase() {
        return phase.stringName;
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

    protected Player getPlayerByNumber(int playerNumber) {
        return switch (playerNumber) {
            case 1 -> player1;
            case 2 -> player2;
            default -> null;
        };
    }

    protected Player getActingPlayer() {
        return getPlayerByNumber(whoseTurn);
    }

    protected Player getOtherPlayer() {
        int other;
        if (whoseTurn == 1) other = 2;
        else other = 1;
        return getPlayerByNumber(other);
    }

    protected Board getPlayerBoardByNumber(int playerNumber) {
        return switch (playerNumber) {
            case 1 -> player1Board;
            case 2 -> player2Board;
            default -> null;
        };
    }

    protected Board getActingPlayerBoard() {
        return getPlayerBoardByNumber(whoseTurn);
    }

    protected Board getOtherPlayerBoard() {
        int other;
        if (whoseTurn == 1) other = 2;
        else other = 1;
        return getPlayerBoardByNumber(other);
    }

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> {
                response = "";
                exitMenu();
            }
            case 2 -> response = showMenu();
            case 3 -> response = showCardErrorChecker(commandArguments);
            case 4 -> response = selectCardErrorChecker(commandArguments);
            case 5 -> response = deselectErrorChecker();
            case 6 -> response = changePhase();
            case 7 -> response = summonErrorChecker(commandArguments);
            case 8 -> response = setErrorChecker(commandArguments);
            case 9 -> response = setPositionErrorChecker(commandArguments);
            case 10 -> response = flipSummonErrorChecker(commandArguments);
            case 11 -> response = attackErrorChecker(commandArguments);
            case 12 -> response = directAttackErrorChecker(commandArguments);
            case 13 -> response = activateEffectErrorChecker(commandArguments);
            case 14 -> response = showGraveyardErrorChecker(commandArguments);
            case 15 -> response = showSelectedCardErrorChecker(commandArguments);
            case 16 -> response = cancelErrorChecker(commandArguments);
            case 17 -> response = surrenderErrorChecker(commandArguments);
            case 18 -> response = useCheat();
            case 19 -> response = increasePropertyErrorChecker(commandArguments);
            case 20 -> response = selectHandErrorChecker(commandArguments);
            case 21 -> response = setWinnerErrorChecker(commandArguments);
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        return "menu navigation is not possible";
    }

    @Override
    protected void enterMenu(Menus menu) {
    }

    @Override
    protected void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    }
}
