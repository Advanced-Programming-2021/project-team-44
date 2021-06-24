package controller.processors;

import controller.Core;
import models.Account;
import models.Board;
import models.Phases;
import models.Player;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.menus.Menus;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class DuelMenuProcessor extends Processor {
//    /**
//     * Auxiliary Object Map:
//     * This map is used for checking the conditions in actions without adding more decentralized parameters.
//     *
//     *
//     */
//    private static HashMap<AuxiliaryIdentifiers, Object> auxiliaryObjectMap;
//    private static enum AuxiliaryIdentifiers{
//        IS_SUMMON_OR_SET_ACTION_AVAILABLE,
//        CHANGE_POSITION_ACTION_AVAILABLE
//    }

    public static Phases phase;
    protected int whoseTurn;
    protected Player player1;
    protected Player player2;
    protected Card selectedCard;
    protected boolean isSummonOrSetActionAvailable;
    protected ArrayList<Card> hasChangedPositionInThisTurn;
    protected ArrayList<Card> isNewlySet;
    protected ArrayList<Card> hasAttackedInThisTurn;
    protected ArrayList<MonsterCard> monsterActiveContinuousEffects;
    protected ArrayList<MonsterCard> monsterEffectsQueue;

    public DuelMenuProcessor(Menus name) {
        super(name);
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
                "surrender" +
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
            if (output[1] == null) output[1] = "";
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

    protected String selectCardErrorChecker(String arguments) {
        //user and opponent
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

    protected String summonErrorChecker() {
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else if (!getActingPlayer().ifHandContains(selectedCard)) response = "you can't summon this card";
        else if (!(selectedCard instanceof MonsterCard)) response = "you can't summon this card";
        else if (!((MonsterCard) selectedCard).isAvailableForNormalSummon()) response = "you can't summon this card";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2) response = "action not allowed in this phase";
        else if (getActingPlayer().isMonsterZoneFull()) response = "monster card zone is full";
        else if (!isSummonOrSetActionAvailable) response = "you already summoned/set on this turn";
        else {
            response = summon();
        }
        return response;
    } //done

    protected String setErrorChecker() {
        //monster and spell and trap
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else if (!getActingPlayer().ifHandContains(selectedCard)) response = "you can't set this card";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2) response = "you can't do this action in this phase";
        else if (!isSummonOrSetActionAvailable) response = "you already summoned/set on this turn";
        else {
            response = set();
        }
        return response;
    } //done

    protected String setPositionErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        enum SelectState {ATTACK, DEFENSE}
        SelectState selectState = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--position", "-p" -> {
                    if (selectState != null) return "invalid command";
                    String selectedState = matcher.group(2).trim();
                    if (selectedState.equals("attack")) selectState = SelectState.ATTACK;
                    else if (selectedState.equals("defense")) selectState = SelectState.DEFENSE;
                    else return "invalid command";
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (selectState == null) return "invalid command";

        if (selectedCard == null) response = "no card is selected yet";
        else if (selectedCard instanceof MonsterCard
                && !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
            response = "you can't change this card's position";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2)
            response = "you can't do this action in this phase";
        else if ((selectState == SelectState.ATTACK
                && !getActingPlayerBoard().getMonsterZoneState(getActingPlayer().getMonsterCardIndex((MonsterCard) selectedCard)).equals("DO"))
                || (selectState == SelectState.DEFENSE
                && !getActingPlayerBoard().getMonsterZoneState(getActingPlayer().getMonsterCardIndex((MonsterCard) selectedCard)).equals("OO")))
            response = "the card is already in the wanted position";
        else if (hasChangedPositionInThisTurn.contains(selectedCard))
            response = "you already changed this card position in this turn";
        else {
            String dummy;
            if (selectState == SelectState.ATTACK) dummy = "attack";
            else dummy = "defense";
            response = setPosition(dummy);
        }
        return response;
    }

    protected String flipSummonErrorChecker() {
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else if (selectedCard instanceof MonsterCard
                && !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
            response = "you can't change this card's position";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2)
            response = "you can't do this action in this phase";
        else if (!getActingPlayerBoard().getMonsterZoneState(getActingPlayer().getMonsterCardIndex((MonsterCard) selectedCard)).equals("DH")
                || isNewlySet.contains(selectedCard))
            response = "you can't flip summon this card";
        else {
            response = flipSummon();
        }
        return response;
    } //done

    protected String attackErrorChecker(String arguments) {
        String response;
        int toBeAttacked;
        try {
            toBeAttacked = Integer.parseInt(arguments.trim());
        } catch (Exception e) {
            return "invalid command";
        }
        if (toBeAttacked > 5 || toBeAttacked < 1) return "invalid command";
        if (selectedCard == null) response = "no card is selected yet";
        else if (selectedCard instanceof MonsterCard
                && !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
            response = "you  can't attack with this card";
        else if (phase != Phases.BATTLE)
            response = "you can't do this action in this phase";
        else if (hasAttackedInThisTurn.contains(selectedCard))
            response = "this card already attacked";
        else if (getOtherPlayer().getCardFromMonsterZone(toBeAttacked) == null)
            response = "there is no card to attack here";
        else {
            response = attack(toBeAttacked);
        }
        return response;
    } //done

    protected String directAttackErrorChecker() {
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else if (selectedCard instanceof MonsterCard
                && !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
            response = "you  can't attack with this card";
        else if (phase != Phases.BATTLE)
            response = "you can't do this action in this phase";
        else if (hasAttackedInThisTurn.contains(selectedCard))
            response = "this card already attacked";
        else if (getOtherPlayer().howManyMonstersInTheGame() > 0)
            response = "you can't attack the opponent directly";
        else {
            response = directAttack();
        }
        return response;
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

    protected String selectCard(String set, Integer position, Boolean ofOpponent) {
        //user and opponent
        String response = "card selected";
        Card tmpCard = null;
        switch (set) {
            case "monster" -> {
                if (ofOpponent) tmpCard = getOtherPlayer().getCardFromMonsterZone(position);
                else tmpCard = getActingPlayer().getCardFromMonsterZone(position);
            }
            case "spell" -> {
                if (ofOpponent) tmpCard = getOtherPlayer().getCardFromMagicZone(position);
                else tmpCard = getActingPlayer().getCardFromMagicZone(position);
            }
            case "field" -> {
                if (ofOpponent) tmpCard = getOtherPlayer().getCardFromFieldZone();
                else tmpCard = getActingPlayer().getCardFromFieldZone();
            }
            case "hand" -> {
                if (ofOpponent) tmpCard = getOtherPlayer().getCardFromHandZone(position);
                else tmpCard = getActingPlayer().getCardFromHandZone(position);
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
        String response;
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
            isSummonOrSetActionAvailable = true;
        }
        return response;
    } //done

    protected String summon() {
        String response = "summoned successfully";
        int emptyPosition = getActingPlayer().getFirstFreePositionInMonsterZone();
        assert emptyPosition != -1;
        if (((MonsterCard) selectedCard).getLevel() <= 4) {
            getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
            getActingPlayer().removeCardFromHandZone(selectedCard);
            activateSummonEffect((MonsterCard) selectedCard);
            deselect();
            getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
        } else if (((MonsterCard) selectedCard).getLevel() <= 6) {
            if (getActingPlayer().howManyMonstersInTheGame() == 0) return "there are not enough cards for tribute";
            else {
                Scanner tmpScanner = new Scanner(System.in);
                System.out.print("Choose a card to tribute: ");
                int tributeIndex = tmpScanner.nextInt();
                tmpScanner.close();
                if (getActingPlayer().getCardFromMonsterZone(tributeIndex) == null)
                    return "there are no monsters on this address";
                else {
                    getActingPlayer().destroyMonster(tributeIndex);
                    getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
                    getActingPlayer().removeCardFromHandZone(selectedCard);
                    activateSummonEffect((MonsterCard) selectedCard);
                    deselect();
                    getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
                }
            }
        } else {
            if (getActingPlayer().howManyMonstersInTheGame() < 2) return "there are not enough cards for tribute";
            else {
                Scanner tmpScanner = new Scanner(System.in);
                System.out.print("Choose first card to tribute: ");
                int firstTributeIndex = tmpScanner.nextInt();
                System.out.print("Choose second card to tribute: ");
                int secondTributeIndex = tmpScanner.nextInt();
                tmpScanner.close();
                if (getActingPlayer().getCardFromMonsterZone(firstTributeIndex) == null ||
                        getActingPlayer().getCardFromMonsterZone(secondTributeIndex) == null)
                    return "there is no monster on one of these addresses";
                else {
                    getActingPlayer().destroyMonster(firstTributeIndex);
                    getActingPlayer().destroyMonster(secondTributeIndex);
                    getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
                    getActingPlayer().removeCardFromHandZone(selectedCard);
                    activateSummonEffect((MonsterCard) selectedCard);
                    deselect();
                    getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
                }
            }
        }
        isSummonOrSetActionAvailable = false;
        return response;
    } //done

    protected String set() {
        //monster and spell and trap
        String response = "set successfully";
        if (selectedCard instanceof MonsterCard) {
            if (getActingPlayer().isMonsterZoneFull()) return "monster card zone is full";

            int emptyPosition = getActingPlayer().getFirstFreePositionInMonsterZone();
            assert emptyPosition != -1;

            getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
            getActingPlayer().removeCardFromHandZone(selectedCard);
            isNewlySet.add(selectedCard);
            deselect();
            getActingPlayerBoard().setMonsterZoneState(emptyPosition, "DH");
        } else {
            if (getActingPlayer().isMagicZoneFull()) return "spell card zone is full";

            int emptyPosition = getActingPlayer().getFirstFreePositionInMagicZone();
            assert emptyPosition != -1;

            getActingPlayer().setCardInMagicZone((MagicCard) selectedCard, emptyPosition);
            getActingPlayer().removeCardFromHandZone(selectedCard);
            isNewlySet.add(selectedCard);
            deselect();
            getActingPlayerBoard().setMagicZoneState(emptyPosition, "H");
        }
        return response;
    } //done

    protected String setPosition(String position) {
        MonsterCard dummy = (MonsterCard) selectedCard;
        if (position.equals("attack"))
            getActingPlayerBoard().setMonsterZoneState(getActingPlayer().getMonsterCardIndex(dummy), "OO");
        else if (position.equals("defense"))
            getActingPlayerBoard().setMonsterZoneState(getActingPlayer().getMonsterCardIndex(dummy), "DO");
        hasChangedPositionInThisTurn.add(selectedCard);
        deselect();
        return "monster card position changed successfully";
    } //done

    protected String flipSummon() {
        getActingPlayerBoard().setMonsterZoneState(getActingPlayer().getMonsterCardIndex((MonsterCard) selectedCard), "OO");
        deselect();
        return "flip summoned successfully";
    } //done

    protected String attack(int toBeAttackedIndex) {
        MonsterCard attackingCard = (MonsterCard) selectedCard;
        hasAttackedInThisTurn.add(selectedCard);
        deselect();
        MonsterCard toBeAttackedCard = getOtherPlayer().getCardFromMonsterZone(toBeAttackedIndex);
        String toBeAttackedCardState = getOtherPlayerBoard().getMonsterZoneState(toBeAttackedIndex);
        if (toBeAttackedCardState.equals("OO")) {
            int difference = Math.abs(toBeAttackedCard.getAttackPoint() - attackingCard.getAttackPoint());
            if (toBeAttackedCard.getAttackPoint() < attackingCard.getAttackPoint()) {
                getOtherPlayer().increaseLp(-1 * difference);
                getOtherPlayer().destroyMonster(toBeAttackedIndex);
                return "your opponent's monster is destroyed and your opponent receives "
                        + difference
                        + " battle damage";
            } else if (toBeAttackedCard.getAttackPoint() == attackingCard.getAttackPoint()) {
                getOtherPlayer().destroyMonster(toBeAttackedIndex);
                getActingPlayer().destroyMonster(getActingPlayer().getMonsterCardIndex(attackingCard));
                return "both you and your opponent monster cards are destroyed and no one receives damage";
            } else {
                getActingPlayer().increaseLp(-1 * difference);
                getActingPlayer().destroyMonster(getActingPlayer().getMonsterCardIndex(attackingCard));
                return "your monster is destroyed and you received "
                        + difference
                        + " battle damage";
            }
        } else if (toBeAttackedCardState.equals("DO")) {
            int difference = Math.abs(toBeAttackedCard.getDefensePoint() - attackingCard.getAttackPoint());
            if (toBeAttackedCard.getDefensePoint() < attackingCard.getAttackPoint()) {
                getOtherPlayer().destroyMonster(toBeAttackedIndex);
                return "the defense position monster is destroyed";
            } else if (toBeAttackedCard.getDefensePoint() == attackingCard.getAttackPoint()) {
                return "no card is destroyed";
            } else {
                getActingPlayer().increaseLp(-1 * difference);
                return "no card is destroyed and you received "
                        + difference
                        + " battle damage";
            }
        } else if (toBeAttackedCardState.equals("DH")) {
            String response = "opponent's monster card was " + toBeAttackedCard.getName() + " and ";
            int difference = Math.abs(toBeAttackedCard.getDefensePoint() - attackingCard.getAttackPoint());
            if (toBeAttackedCard.getDefensePoint() < attackingCard.getAttackPoint()) {
                getOtherPlayer().destroyMonster(toBeAttackedIndex);
                return response
                        + "the defense position monster is destroyed";
            } else if (toBeAttackedCard.getDefensePoint() == attackingCard.getAttackPoint()) {
                return response
                        + "no card is destroyed";
            } else {
                getActingPlayer().increaseLp(-1 * difference);
                return response
                        + "no card is destroyed and you received "
                        + difference
                        + " battle damage";
            }
        } else {
            return "";
        }
    } //done

    protected String directAttack() {
        MonsterCard attackingCard = (MonsterCard) selectedCard;
        hasAttackedInThisTurn.add(selectedCard);
        deselect();
        getOtherPlayer().increaseLp(-1 * attackingCard.getAttackPoint());
        return "your opponent receives "
                + attackingCard.getAttackPoint()
                + " battle damage";
    } //done

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
    } //TODO show board

    protected String showPhase() {
        return phase.stringName;
    } //done

    protected int showPlayerPoints(Player player) {
        return 0;
    } //TODO game points calculate

    protected void monsterEffectsActivator() {
        for (MonsterCard card : monsterEffectsQueue) {
            if (card.getEffectType().equals("Continuous")) monsterActiveContinuousEffects.add(card);
            getActingPlayer().activateSelfSummonEffect(card);
            getOtherPlayer().activateOpponentSummonEffect(card);
        }
    } //done

    protected boolean checkForDuelEnd() {
        return false;
    } //TODO Check for end

    protected void endDuel() {
    } //TODO End duel

    protected void activateSummonEffect(MonsterCard card) {
        //Called from summon method
        if ("Effect".equals(card.getCardType())) {
            monsterEffectsQueue.add(card);
            monsterEffectsActivator();
        }
    } //done

    protected Player getPlayerByNumber(int playerNumber) {
        return switch (playerNumber) {
            case 1 -> player1;
            case 2 -> player2;
            default -> null;
        };
    } //done

    protected Player getActingPlayer() {
        return getPlayerByNumber(whoseTurn);
    } //done

    protected Player getOtherPlayer() {
        int other;
        if (whoseTurn == 1) other = 2;
        else other = 1;
        return getPlayerByNumber(other);
    } //done

    protected Board getActingPlayerBoard() {
        return getActingPlayer().getBoard();
    } //done

    protected Board getOtherPlayerBoard() {
        return getOtherPlayer().getBoard();
    } //done

    @Override
    public String process(int commandId, String commandArguments) {
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
            case 7 -> response = summonErrorChecker();
            case 8 -> response = setErrorChecker();
            case 9 -> response = setPositionErrorChecker(commandArguments);
            case 10 -> response = flipSummonErrorChecker();
            case 11 -> response = attackErrorChecker(commandArguments);
            case 12 -> response = directAttackErrorChecker();
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
