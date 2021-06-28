package controller.processors;

import models.Account;
import models.Board;
import models.Phases;
import models.Player;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.menus.Menus;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class DuelMenuProcessor extends Processor {

    public static Phases phase;
    public static int allRounds;
    public static int remainingRounds;
    protected static boolean hasAnyoneSurrendered = false;
    protected static boolean cheatEndRound = false;
    protected static boolean ifRoundHasEnded = false;
    protected int whoseTurn;
    protected Player player1;
    protected Player player2;
    protected Card selectedCard;
    protected boolean isSummonOrSetActionAvailable;
    protected ArrayList<Card> hasChangedPositionInThisTurn;
    protected ArrayList<Card> isNewlySet;
    protected ArrayList<Card> hasAttackedInThisTurn;
    protected ArrayList<MonsterCard> activeMonsterContinuousEffects;
    protected ArrayList<MonsterCard> monsterEffectsQueue;
    protected ArrayList<MagicCard> activeMagicEffects;

    public DuelMenuProcessor(Menus name) {
        super(name);
    }

    protected void newRoundInitializer() {
        remainingRounds--;

        Player dummy = player1;
        player1 = player2;
        player2 = dummy;

        phase = Phases.DRAW;
        whoseTurn = 1;
        hasAnyoneSurrendered = false;
        cheatEndRound = false;
        ifRoundHasEnded = false;
        deselect();
        isSummonOrSetActionAvailable = true;
        hasChangedPositionInThisTurn = new ArrayList<>();
        isNewlySet = new ArrayList<>();
        hasAttackedInThisTurn = new ArrayList<>();
        activeMonsterContinuousEffects = new ArrayList<>();
        monsterEffectsQueue = new ArrayList<>();
        activeMagicEffects = new ArrayList<>();
    }

    abstract public void gameInitialization(Account player1, Account player2, int rounds);

    abstract public void execute();

    abstract public void executeRound();

    abstract public void executeTurn();

    abstract public void endGame();

    abstract public void endRound(Player winner, Player loser);

    public String[] commandHandler(String input) {
        String[] output = {"-1", ""};
        Pattern pattern = Pattern.compile("^(help|" +
                "menu enter|" +
                "menu exit|" +
                "menu show-current|" +
                "card show|" +
                //Cheats
                "use cheat|" +
                "increase --LP|" +
                "duel set-winner|" +
                //Main
                "select -d|" +
                "select|" +
                "next phase|" +
                "summon|" +
                "special summon|" +
                "ritual summon|" +
                "set --position|" + "set -p|" +
                "set|" +
                "flip-summon|" +
                "attack direct|" +
                "attack|" +
                "activate effect|" +
                "show graveyard --opponent|" + "show graveyard -o|" +
                "show graveyard|" +
                "card show --selected|" + "card show -s|" +
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
                case "show graveyard --opponent", "show graveyard -o" -> output[0] = "16";
                case "surrender" -> output[0] = "17";
                case "use cheat" -> output[0] = "18";
                case "duel set-winner" -> output[0] = "19";
                case "increase --LP" -> output[0] = "20";
                case "special summon" -> output[0] = "30";
                case "ritual summon" -> output[0] = "31";
                case "help" -> output[0] = "99";
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

    protected String specialSummonErrorChecker() {
        if (selectedCard == null) return "no card is selected yet";
        else if (!getActingPlayer().ifHandContains(selectedCard)) return "you can't summon this card";
        else if (!(selectedCard instanceof MonsterCard)) return "you can't summon this card";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2) return "action not allowed in this phase";
        else {
            MonsterCard toBeSpecialSummonedCard = (MonsterCard) selectedCard;
            deselect();
            switch (toBeSpecialSummonedCard.getName()) {
                case "Gate Guardian", "" -> {
                    return specialSummon(toBeSpecialSummonedCard);
                }
                default -> {
                    return "this monster can't be special summoned";
                }
            }
        }
    } //done

    protected String ritualSummonErrorChecker() {
        if (selectedCard == null) return "no card is selected yet";
        else if (!getActingPlayer().ifHandContains(selectedCard)) return "you can't summon this card";
        else if (!(selectedCard instanceof MonsterCard)) return "you can't summon this card";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2) return "action not allowed in this phase";
        else if ()
        else {
            MonsterCard toBeRitualSummonedCard = (MonsterCard) selectedCard;
            deselect();
            switch (toBeRitualSummonedCard.getName()) {
                case "" -> {
                    return ritualSummon(toBeRitualSummonedCard);
                }
                default -> {
                    return "this monster can't be ritual summoned";
                }
            }
        }
    }

    protected String setErrorChecker() {
        //monster and spell and trap
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else if (!getActingPlayer().ifHandContains(selectedCard)) response = "you can't set this card";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2) response = "you can't do this action in this phase";
        else if (selectedCard instanceof MonsterCard && !isSummonOrSetActionAvailable)
            response = "you already summoned/set on this turn";
        else {
            if (selectedCard instanceof MonsterCard) {
                if (getActingPlayer().isMonsterZoneFull()) return "monster card zone is full";
            } else {
                if (getActingPlayer().isMagicZoneFull()) return "spell card zone is full";
            }
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
        else if (!(selectedCard instanceof MonsterCard)
                || !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
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
        else if (!(selectedCard instanceof MonsterCard)
                || !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
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
        else if (!(selectedCard instanceof MonsterCard)
                || !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
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
        else if (!(selectedCard instanceof MonsterCard)
                || !getActingPlayer().ifMonsterZoneContains((MonsterCard) selectedCard))
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
    } //done

    protected String activateEffectErrorChecker() {
        String response;
        if (selectedCard == null) response = "no card is selected yet";
        else if (!(selectedCard instanceof MagicCard))
            response = "activate effect is only for spell cards";
        else if (phase != Phases.MAIN1 && phase != Phases.MAIN2)
            response = "you can't activate an effect on this turn";
        else if (activeMagicEffects.contains((MagicCard) selectedCard))
            response = "you have already activated this card";
        else if (getActingPlayer().isMagicZoneFull())
            response = "spell card zone is full";
        else {
            response = activateEffect();
        }
        return response;
    } //done

    protected String showGraveyardErrorChecker(boolean ofOpponent) {
        showGraveyard(ofOpponent);
        return "";
    } //done

    protected String showSelectedCardErrorChecker() {
        if (selectedCard == null) return "no card is selected yet";
        if (getOtherPlayer().ownsCard(selectedCard)
                && getOtherPlayer().getCardState(selectedCard).charAt(getOtherPlayer().getCardState(selectedCard).length() - 1) == 'H')
            return "card not visible";
        return showSelectedCard();
    } //done

    ////Cheats
    protected String increaseLpErrorChecker(String arguments) {
        if (!getActingPlayer().isCheatActivated()) return "you can't use cheats";
        int amount;
        try {
            amount = Integer.parseInt(arguments);
        } catch (Exception e) {
            return "invalid value";
        }
        return increaseLp(amount);
    } //done

    protected String setWinnerErrorChecker(String arguments) {
        if (!getActingPlayer().isCheatActivated()) return "you can't use cheats";
        if (!getActingPlayer().getAccount().getNickname().equals(arguments)
                && !getOtherPlayer().getAccount().getNickname().equals(arguments))
            return "no player with this nickname in the game";
        setWinnerCheat(arguments);
        return "shame on cheater!";
    } //done

    //Command Performer
    ////Main
    protected String showCard(String cardName) {
        return Objects.requireNonNull(Card.getCardByName(cardName)).getStringForShow();
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

    protected void changeTurn() {
        if (whoseTurn == 1) whoseTurn = 2;
        else if (whoseTurn == 2) whoseTurn = 1;
        isSummonOrSetActionAvailable = true;
        hasChangedPositionInThisTurn = new ArrayList<>();
        isNewlySet = new ArrayList<>();
        hasAttackedInThisTurn = new ArrayList<>();
        deselect();
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
            changeTurn();
            response += "\n" + "its " + getActingPlayer().getAccount().getNickname() + "'s turn";
            if (getActingPlayer().getMainDeckCount() == 0) endRound(getOtherPlayer(), getActingPlayer());
            else getActingPlayer().setHandCards();
        } else if (phase == Phases.STANDBY) {
            //TODO main effect: card effects
        } else if (phase == Phases.END) {
            //TODO check for than 6 cards in hand
            //TODO check for cards effects in this phase
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
                System.out.print("Choose a card to tribute: ");
                Integer tributeIndex;
                try {
                    tributeIndex = getTribute();
                    if (tributeIndex == null) return "Summon Canceled";
                } catch (Exception e) {
                    return "there are no monsters on this address";
                }
                getActingPlayer().destroyMonster(tributeIndex);
                getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
                getActingPlayer().removeCardFromHandZone(selectedCard);
                activateSummonEffect((MonsterCard) selectedCard);
                deselect();
                getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
            }
        } else {
            if (getActingPlayer().howManyMonstersInTheGame() < 2) return "there are not enough cards for tribute";
            else {
                Integer firstTributeIndex = null;
                Integer secondTributeIndex = null;
                try {
                    System.out.print("Choose first card to tribute: ");
                    firstTributeIndex = getTribute();
                    if (firstTributeIndex == null) return "Summon Canceled";
                    System.out.print("Choose second card to tribute: ");
                    secondTributeIndex = getTribute();
                    if (secondTributeIndex == null) return "Summon Canceled";
                } catch (Exception e) {
                    return "there is no monster on one of these addresses";
                }
                getActingPlayer().destroyMonster(firstTributeIndex);
                getActingPlayer().destroyMonster(secondTributeIndex);
                getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
                getActingPlayer().removeCardFromHandZone(selectedCard);
                activateSummonEffect((MonsterCard) selectedCard);
                deselect();
                getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
            }
        }
        isSummonOrSetActionAvailable = false;
        return response;
    } //done

    //Util
    // {
    protected Integer getTribute() throws Exception {
        Scanner tmpScanner = new Scanner(System.in);
        Integer tributeIndex = null;
        String input;
        while (!(input = tmpScanner.nextLine().trim()).equals("cancel")) {
            try {
                tributeIndex = Integer.parseInt(input);
                assert tributeIndex <= 5;
            } catch (Exception e) {
                System.out.println("Invalid card index");
                continue;
            }
            break;
        }
        if (input.equals("cancel")) return null;
        tmpScanner.close();
        if (getActingPlayer().getCardFromMonsterZone(tributeIndex) == null)
            throw new Exception();
        return tributeIndex;
    } //done
    // }

    protected String specialSummon(MonsterCard toBeSpecialSummonedCard) {
        switch (toBeSpecialSummonedCard.getName()) {
            case "Gate Guardian" -> {
                System.out.println("""
                        This monster can be special summoned with 3 tributes. Choose 3 cards to tribute.
                        Input format:
                        <tribute index>
                        """);
                if (getActingPlayer().howManyMonstersInTheGame() < 3) return "there are not enough cards for tribute";
                Integer firstTribute;
                Integer secondTribute;
                Integer thirdTribute;
                try {
                    System.out.print("Choose first card to tribute: ");
                    firstTribute = getTribute();
                    if (firstTribute == null) return "Summon Canceled";
                    System.out.print("Choose second card to tribute: ");
                    secondTribute = getTribute();
                    if (secondTribute == null) return "Summon Canceled";
                    System.out.print("Choose third card to tribute: ");
                    thirdTribute = getTribute();
                    if (thirdTribute == null) return "Summon Canceled";
                } catch (Exception e) {
                    return "there is no monster on one of these addresses";
                }
                getActingPlayer().destroyMonster(firstTribute);
                getActingPlayer().destroyMonster(secondTribute);
                getActingPlayer().destroyMonster(thirdTribute);
            }
        }
        int emptyPosition = getActingPlayer().getFirstFreePositionInMonsterZone();
        if (emptyPosition == -1) return "monster card zone is full";
        getActingPlayer().setCardInMonsterZone(toBeSpecialSummonedCard, emptyPosition);
        getActingPlayer().removeCardFromHandZone(toBeSpecialSummonedCard);
        getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
        return "special summoned successfully";
    }

    protected String ritualSummon(MonsterCard toBeRitualSummonedCard) {
        switch (toBeRitualSummonedCard.getName()) {
            case "Skull Guardian" -> {}
            case "Crab Turtle" -> {}
        }
        int emptyPosition = getActingPlayer().getFirstFreePositionInMonsterZone();
        if (emptyPosition == -1) return "monster card zone is full";
        getActingPlayer().setCardInMonsterZone(toBeRitualSummonedCard, emptyPosition);
        getActingPlayer().removeCardFromHandZone(toBeRitualSummonedCard);
        getActingPlayerBoard().setMonsterZoneState(emptyPosition, "OO");
        return "ritual summoned successfully";
    }

    protected String set() {
        //monster and spell and trap
        String response = "set successfully";
        if (selectedCard instanceof MonsterCard) {
            int emptyPosition = getActingPlayer().getFirstFreePositionInMonsterZone();
            assert emptyPosition != -1;

            getActingPlayer().setCardInMonsterZone((MonsterCard) selectedCard, emptyPosition);
            getActingPlayer().removeCardFromHandZone(selectedCard);
            isNewlySet.add(selectedCard);
            deselect();
            getActingPlayerBoard().setMonsterZoneState(emptyPosition, "DH");
        } else {
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
        MonsterCard toBeFlipped = (MonsterCard) selectedCard;
        getActingPlayerBoard().setMonsterZoneState(getActingPlayer().getMonsterCardIndex(toBeFlipped), "OO");
        onFlipEffects(toBeFlipped);
        deselect();
        return "flip summoned successfully";
    } //done

    protected void onFlipEffects(Card gettingFlipped) {
        Scanner tmpScanner = new Scanner(System.in);
        switch (gettingFlipped.getName()) {
            case "Man-Eater Bug" -> {
                System.out.println("""
                        This card can destroy one of your opponent's monsters after being flipped. Choose a monster to be destroyed.
                        Input format:
                        <monster card index>
                        """);
                Pattern pattern = Pattern.compile("^(\\d)$");
                String input = tmpScanner.nextLine();
                Matcher matcher;
                while (!(matcher = pattern.matcher(input)).find()) {
                    System.out.println("invalid input.");
                    input = tmpScanner.nextLine();
                }
                int index = Integer.parseInt(matcher.group(1));
                getOtherPlayer().destroyMonster(index);
            }
        }
        tmpScanner.close();
    }

    protected String attack(int toBeAttackedIndex) {
        MonsterCard attackingCard = (MonsterCard) selectedCard;
        hasAttackedInThisTurn.add(selectedCard);
        deselect();
        MonsterCard toBeAttackedCard = getOtherPlayer().getCardFromMonsterZone(toBeAttackedIndex);
        String toBeAttackedCardState = getOtherPlayerBoard().getMonsterZoneState(toBeAttackedIndex);
        switch (toBeAttackedCardState) {
            case "OO" -> {
                int difference = Math.abs(toBeAttackedCard.getAttackPoint() - attackingCard.getAttackPoint());
                if (toBeAttackedCard.getAttackPoint() < attackingCard.getAttackPoint()) {
                    getOtherPlayer().increaseLp(-1 * difference);
                    getOtherPlayer().destroyMonster(toBeAttackedIndex);
                    onBeingAttackedAndDestroyedEffects(toBeAttackedCard, attackingCard);
                    return "your opponent's monster is destroyed and your opponent receives "
                            + difference
                            + " battle damage";
                } else if (toBeAttackedCard.getAttackPoint() == attackingCard.getAttackPoint()) {
                    getOtherPlayer().destroyMonster(toBeAttackedIndex);
                    getActingPlayer().destroyMonster(getActingPlayer().getMonsterCardIndex(attackingCard));
                    onBeingAttackedAndDestroyedEffects(toBeAttackedCard, attackingCard);
                    return "both you and your opponent monster cards are destroyed and no one receives damage";
                } else {
                    getActingPlayer().increaseLp(-1 * difference);
                    getActingPlayer().destroyMonster(getActingPlayer().getMonsterCardIndex(attackingCard));
                    return "your monster is destroyed and you received "
                            + difference
                            + " battle damage";
                }
            }
            case "DO" -> {
                int difference = Math.abs(toBeAttackedCard.getDefensePoint() - attackingCard.getAttackPoint());
                if (toBeAttackedCard.getDefensePoint() < attackingCard.getAttackPoint()) {
                    getOtherPlayer().destroyMonster(toBeAttackedIndex);
                    onBeingAttackedAndDestroyedEffects(toBeAttackedCard, attackingCard);
                    return "the defense position monster is destroyed";
                } else if (toBeAttackedCard.getDefensePoint() == attackingCard.getAttackPoint()) {
                    return "no card is destroyed";
                } else {
                    getActingPlayer().increaseLp(-1 * difference);
                    return "no card is destroyed and you received "
                            + difference
                            + " battle damage";
                }
            }
            case "DH" -> {
                String response = "opponent's monster card was " + toBeAttackedCard.getName() + " and ";
                int difference = Math.abs(toBeAttackedCard.getDefensePoint() - attackingCard.getAttackPoint());
                if (toBeAttackedCard.getDefensePoint() < attackingCard.getAttackPoint()) {
                    getOtherPlayer().destroyMonster(toBeAttackedIndex);
                    onBeingAttackedAndDestroyedEffects(toBeAttackedCard, attackingCard);
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
            }
            default -> {
                return "";
            }
        }
    } //done

    protected void onBeingAttackedAndDestroyedEffects(Card gettingAttacked, MonsterCard attackingCard) {
        switch (gettingAttacked.getName()) {
            case "Yomi Ship", "Exploder Dragon" -> {
                getActingPlayer().destroyMonster(getActingPlayer().getMonsterCardIndex(attackingCard));
            }
        }
    }

    protected String directAttack() {
        MonsterCard attackingCard = (MonsterCard) selectedCard;
        hasAttackedInThisTurn.add(selectedCard);
        deselect();
        getOtherPlayer().increaseLp(-1 * attackingCard.getAttackPoint());
        return "your opponent receives "
                + attackingCard.getAttackPoint()
                + " battle damage";
    } //done

    protected String activateEffect() {
        //TODO Spell effect activation preparations check
        MagicCard toBeActivatedCard = (MagicCard) selectedCard;
        deselect();
        Scanner tmpScanner = new Scanner(System.in);
        switch (toBeActivatedCard.getName()) {
            case "Monster Reborn" -> {
                System.out.println("""
                        Choose a monster from your graveyard or opponent's to revive and special summon.
                        Input format:
                        <self/opponent> <card name>
                        """);
                Pattern pattern = Pattern.compile("^(self|opponent)\\s+(.+?)$");
                String input = tmpScanner.nextLine();
                while (!pattern.matcher(input).find()) {
                    System.out.println("invalid input. ");
                }
            }
            case "Terraforming" -> {
            }
            case "Pot of Greed" -> {
            }
            case "Raigeki" -> {
            }
            case "Change of Heart" -> {
            }
            case "Harpie's Feather Duster" -> {
            }
            case "Swords of Revealing Light" -> {
            }
            case "Dark Hole" -> {
            }
            case "Supply Squad" -> {
            }
            case "Spell Absorption" -> {
            }
            case "Messenger of peace" -> {
            }
            case "Twin Twisters" -> {
            }
            case "Mystical space typhoon" -> {
            }
            case "Ring of defense" -> {
            }
            case "Yami" -> {
            }
            case "Forest" -> {
            }
            case "Closed Forest" -> {
            }
            case "Umiiruka" -> {
            }
            case "Sword of dark destruction" -> {
            }
            case "Black Pendant" -> {
            }
            case "United We Stand" -> {
            }
            case "Magnum Shield" -> {
            }
            case "Advanced Ritual Art" -> {
            }
            case "Magic Cylinder" -> {
            }
            case "Mirror Force" -> {
            }
            case "Mind Crush" -> {
            }
            case "Trap Hole" -> {
            }
            case "Torrential Tribute" -> {
            }
            case "Time Seal" -> {
            }
            case "Negate Attack" -> {
            }
            case "Solemn Warning" -> {
            }
            case "Magic Jammer" -> {
            }
            case "Call of The Haunted" -> {
            }
            case "Vanity's Emptiness" -> {
            }
            case "Wall of Revealing Light" -> {
            }
        }
        tmpScanner.close();
        return "spell activated";
    }  //TODO activate effect

    //TODO spell activation in other player's turn

    //TODO ritual summon
    //TODO special summon

    protected void showGraveyard(boolean ofOpponent) {
        ArrayList<Card> graveyardZone;
        if (ofOpponent) graveyardZone = getOtherPlayer().getGraveyardZone();
        else graveyardZone = getActingPlayer().getGraveyardZone();
        StringBuilder stringBuilder = new StringBuilder();
        if (graveyardZone.size() == 0)
            System.out.println("graveyard empty");
        else {
            for (Card card : graveyardZone) {
                stringBuilder.append(card.getName());
                stringBuilder.append(":");
                stringBuilder.append(card.getDescription());
                stringBuilder.append("\n");
            }
            System.out.print(stringBuilder);
        }
        Scanner tmpScanner = new Scanner(System.in);
        while (!tmpScanner.nextLine().equals("back")) {
            System.out.println("use back command to go back to game");
        }
    } //done

    protected String showSelectedCard() {
        return showCard(selectedCard.getName());
    } //done

    protected String surrender() {
        hasAnyoneSurrendered = true;
        getOtherPlayer().winsRound();
        int score1 = getOtherPlayer().getScore();
        int score2 = getActingPlayer().getScore();
        return getOtherPlayer().getAccount().getNickname()
                + " won the game and the score is: "
                + score1
                + " - "
                + score2;
    } //done

    ////Cheats
    protected String useCheat() {
        if (getActingPlayer().isCheatActivated()) return "cheats already activated";
        else {
            getActingPlayer().setCheatActivated(true);
            return "cheats activated successfully";
        }
    } //done

    protected String increaseLp(int amount) {
        getActingPlayer().increaseLp(amount);
        return amount + " Lp was successfully added to you";
    } //done

    protected void setWinnerCheat(String winnerNickname) {
        if (getActingPlayer().getAccount().getNickname().equals(winnerNickname))
            cheatEndRound(getActingPlayer(), getOtherPlayer());
        else
            cheatEndRound(getOtherPlayer(), getActingPlayer());
    } //done

    protected void cheatEndRound(Player winner, Player loser) {
        cheatEndRound = true;
        endRound(winner, loser);
    } //done

    //Utils
    protected String showBoard() {
        return "\n" + getOtherPlayerBoard().getStringAsOpponent() + "\n" +
                "\n" +
                "--------------------------" + "\n" +
                "\n" +
                getActingPlayerBoard().getStringAsSelf() + "\n";
    } //done

    protected void monsterEffectsActivator() {
        for (MonsterCard card : monsterEffectsQueue) {
            if (card.getEffectType().equals("Continuous")) activeMonsterContinuousEffects.add(card);
            getActingPlayer().activateSelfSummonEffect(card);
            getOtherPlayer().activateOpponentSummonEffect(card);
        }
    } //done

    protected boolean ifRoundHasEnded() {
        if (getActingPlayer().getLp() <= 0 || getOtherPlayer().getLp() <= 0)
            ifRoundHasEnded = true;
        return ifRoundHasEnded;
    } //done

    protected Player getWinner() {
        if (getActingPlayer().getLp() == 0 && getOtherPlayer().getLp() > 0)
            return getOtherPlayer();
        else if (getActingPlayer().getLp() > 0 && getOtherPlayer().getLp() == 0)
            return getActingPlayer();
        else
            return null;
    } //done

    protected Player getLoser() {
        if (getActingPlayer().getLp() == 0 && getOtherPlayer().getLp() > 0)
            return getActingPlayer();
        else if (getActingPlayer().getLp() > 0 && getOtherPlayer().getLp() == 0)
            return getOtherPlayer();
        else
            return null;
    } //done

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
        commandArguments = commandArguments.trim();
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
            case 13 -> response = activateEffectErrorChecker();
            case 14 -> response = showGraveyardErrorChecker(false);
            case 15 -> response = showSelectedCardErrorChecker();
            case 16 -> response = showGraveyardErrorChecker(true);
            case 17 -> response = surrender();
            case 18 -> response = useCheat();
            case 19 -> response = setWinnerErrorChecker(commandArguments);
            case 20 -> response = increaseLpErrorChecker(commandArguments);
            case 30 -> response = specialSummonErrorChecker();
            case 31 -> response = ritualSummonErrorChecker();
            case 99 -> response = help();
        }
        return response;
    }

    @Override
    protected String help() {
        return """
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                card show <name>
                select <monster/spell/field/hand> <index> [opponent]
                select -d
                summon
                special summon
                ritual summon
                set
                set --position
                flip-summon
                attack
                attack direct
                activate effect
                show graveyard [opponent]
                card show --selected
                surrender
                cancel (only usable in  in-command chain inputs)
                use cheat
                increase --LP <amount>
                duel set-winner <nickname>
                help
                """;
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
        System.out.println("\nmenu navigation is not possible\n");
    }
}
