package controller.processors;

import controller.Core;
import models.Deck;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import models.Menus;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckMenuProcessor extends Processor { //DONE
    private static DeckMenuProcessor instance;

    public DeckMenuProcessor() {
        super(Menus.DECK);
    }

    public static DeckMenuProcessor getInstance() {
        if (instance == null) {
            instance = new DeckMenuProcessor();
        }
        return instance;
    }

    //Error Checker
    public String showCardErrorChecker(String arguments) {
        String response;
        if (Card.getCardByName(arguments) == null) response = "there is no card with this name";
        else {
            response = showCard(arguments);
        }
        return response;
    }

    public String createDeckErrorChecker(String arguments) {
        String response;
        String deckName = arguments.trim();
        if (Processor.loggedInUser.getDeckByName(deckName) != null)
            response = "deck with name " + deckName + " already exists";
        else {
            createDeck(deckName);
            response = "deck created successfully!";
        }
        return response;
    }

    public String deleteDeckErrorChecker(String arguments) {
        String response;
        String deckName = arguments.trim();
        if (Processor.loggedInUser.getDeckByName(deckName) == null)
            response = "deck with name " + deckName + " does not exist";
        else {
            deleteDeck(deckName);
            response = "deck deleted successfully";
        }
        return response;
    }

    public String setActiveDeckErrorChecker(String arguments) {
        String response;
        String deckName = arguments.trim();
        if (Processor.loggedInUser.getDeckByName(deckName) == null)
            response = "deck with name " + deckName + " does not exist";
        else {
            setActiveDeck(deckName);
            response = "deck activated successfully";
        }
        return response;
    }

    public String addCardToDeckErrorChecker(String arguments) {
        //Cheat Enhanced
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-\\w|--\\w+)\\b(.*?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        String cardName = null;
        String deckName = null;
        Boolean isSideDeck = null;
        Boolean isForced = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--card", "-c" -> {
                    if (cardName != null) return "invalid command";
                    cardName = matcher.group(2).trim();
                }
                case "--deck", "-d" -> {
                    if (deckName != null) return "invalid command";
                    deckName = matcher.group(2).trim();
                }
                case "--side", "-s" -> {
                    if (isSideDeck != null) return "invalid command";
                    isSideDeck = true;
                }
                case "--force", "-f" -> {
                    if (isForced != null) return "invalid command";
                    isForced = true;
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (isSideDeck == null) isSideDeck = false;
        if (isForced == null) isForced = false;
        if (cardName == null || deckName == null) return "invalid command";

        if (Processor.loggedInUser.getCardByName(cardName) == null)
            response = "card with name " + cardName + " does not exist";
        else if (Processor.loggedInUser.getDeckByName(deckName) == null)
            response = "deck with name " + deckName + " does not exist";
        else {
            boolean fullState;
            String whichDeck;
            if (isSideDeck) {
                fullState = Processor.loggedInUser.getDeckByName(deckName).isSideDeckFull();
                whichDeck = "side";
            } else {
                fullState = Processor.loggedInUser.getDeckByName(deckName).isMainDeckFull();
                whichDeck = "main";
            }
            if (fullState && !isForced) response = whichDeck + " deck is full";
            else if (Processor.loggedInUser.getDeckByName(deckName).ifMaxOfCardIsReached(cardName) && !isForced)
                response = "there are already three cards with name " + cardName + " in deck" + deckName;
            else {
                addCardToDeck(deckName, cardName, whichDeck);
                if (isForced)
                    response = "card added to deck forcefully! shame on cheater!";
                else
                    response = "card added to deck successfully";
            }
        }
        return response;
    }

    public String removeCardFromDeckErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-\\w|--\\w+)\\b(.*?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        String cardName = null;
        String deckName = null;
        Boolean isSideDeck = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--card", "-c" -> {
                    if (cardName != null) return "invalid command";
                    cardName = matcher.group(2).trim();
                }
                case "--deck", "-d" -> {
                    if (deckName != null) return "invalid command";
                    deckName = matcher.group(2).trim();
                }
                case "--side", "-s" -> {
                    if (isSideDeck != null) return "invalid command";
                    isSideDeck = true;
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (isSideDeck == null) isSideDeck = false;
        if (cardName == null || deckName == null) return "invalid command";

        if (Processor.loggedInUser.getCardByName(cardName) == null)
            response = "card with name " + cardName + " does not exist";
        else if (Processor.loggedInUser.getDeckByName(deckName) == null)
            response = "deck with name " + deckName + " does not exist";
        else {
            String whichDeck;
            if (isSideDeck) {
                whichDeck = "side";
            } else {
                whichDeck = "main";
            }
            if (isSideDeck && !Processor.loggedInUser.getDeckByName(deckName).isCardExistedInSideDeck(cardName)) {
                response = "card with name " + cardName + " does not exist in side deck";
            } else if (!isSideDeck && !Processor.loggedInUser.getDeckByName(deckName).isCardExistedInMainDeck(cardName)) {
                response = "card with name " + cardName + " does not exist in main deck";
            } else {
                removeCardFromDeck(deckName, cardName, whichDeck);
                response = "card removed form deck successfully";
            }
        }
        return response;
    }

    public String showDeckErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-\\w|--\\w+)\\b(.*?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        String deckName = null;
        Boolean isSideDeck = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--deck-name", "-d" -> {
                    if (deckName != null) return "invalid command";
                    deckName = matcher.group(2).trim();
                }
                case "--side", "-s" -> {
                    if (isSideDeck != null) return "invalid command";
                    isSideDeck = true;
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (isSideDeck == null) isSideDeck = false;
        if (deckName == null) return "invalid command";

        if (loggedInUser.getDeckByName(deckName) == null) {
            response = "deck with name " + deckName + " does not exist";
        } else {
            response = showDeck(deckName, isSideDeck);
        }
        return response;
    }

    //Command Performer
    public String showCard(String cardName) {
        return Objects.requireNonNull(Card.getCardByName(cardName)).getStringForShow();
    }

    public void createDeck(String deckName) {
        Processor.loggedInUser.addDeck(new Deck(deckName));
    }

    public void deleteDeck(String deckName) {
        Processor.loggedInUser.removeDeck(Processor.loggedInUser.getDeckByName(deckName));
    }

    public void setActiveDeck(String deckName) {
        Processor.loggedInUser.setActiveDeck(Processor.loggedInUser.getDeckByName(deckName));
    }

    public void addCardToDeck(String deckName, String cardName, String whichDeck) {
        switch (whichDeck) {
            case "main" -> {
                switch (Objects.requireNonNull(Card.getTypeOfCardByName(cardName))) {
                    case "monster" -> Processor.loggedInUser.getDeckByName(deckName).addCardToMainDeck(MonsterCard.createMonsterCard(cardName));
                    case "magic" -> Processor.loggedInUser.getDeckByName(deckName).addCardToMainDeck(MagicCard.createMagicCard(cardName));
                }
            }
            case "side" -> {
                switch (Objects.requireNonNull(Card.getTypeOfCardByName(cardName))) {
                    case "monster" -> Processor.loggedInUser.getDeckByName(deckName).addCardToSideDeck(MonsterCard.createMonsterCard(cardName));
                    case "magic" -> Processor.loggedInUser.getDeckByName(deckName).addCardToSideDeck(MagicCard.createMagicCard(cardName));
                }
            }
        }
    }

    public void removeCardFromDeck(String deckName, String cardName, String whichDeck) {
        switch (whichDeck) {
            case "main" -> Processor.loggedInUser.getDeckByName(deckName).removeCardFromMainDeck(cardName);
            case "side" -> Processor.loggedInUser.getDeckByName(deckName).removeCardFromSideDeck(cardName);
        }
    }

    public String showAllDecks() {
        StringBuilder response = new StringBuilder();
        response.append("Decks:").append("\n");
        response.append("Active deck:").append("\n");
        if (Processor.loggedInUser.getActiveDeck() != null)
            response.append(Processor.loggedInUser.getActiveDeck().getStringForShowAllDecks()).append("\n");
        response.append("Other decks:").append("\n");
        if (Processor.loggedInUser.getOtherDecks().size() == 0)
            return response.toString();
        for (Deck deck : Processor.loggedInUser.getOtherDecks())
            response.append(deck.getStringForShowAllDecks()).append("\n");
        response.deleteCharAt(response.length() - 1);
        return response.toString();
    }

    public String showDeck(String deckName, Boolean isSide) {
        String mainOrSide;
        if (isSide) mainOrSide = "Side";
        else mainOrSide = "Main";
        return Processor.loggedInUser.getDeckByName(deckName).showDeck(mainOrSide);
    }

    public String showCards() {
        return loggedInUser.showSpareCards();
    }

    @Override
    public String enterMenuErrorChecker(String input) {
        return "menu navigation is not possible";
    }

    @Override
    public String help() {
        return """
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                deck create <name>
                deck delete <name>
                deck set-activate <name>
                deck add-card <card name> <deck name> [side/main]
                deck rm-card <card name> <deck name> [side/main]
                deck show --all
                deck show --cards
                deck show <name> [side/main]
                card show <name>
                help
                """;
    }

    @Override
    public void enterMenu(Menus menu) {
    }

    @Override
    public void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    }
}
