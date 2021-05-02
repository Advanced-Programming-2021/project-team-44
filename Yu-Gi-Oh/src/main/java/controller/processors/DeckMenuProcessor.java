package controller.processors;

import models.cards.Card;
import models.Deck;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.menus.Menus;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckMenuProcessor extends Processor {
    public DeckMenuProcessor() {
        super(Menus.DECK);
    }

    //Error Checker
    private String showCardErrorChecker(String arguments) {
        String response;
        if (Card.getCardByName(arguments) == null) response = "there is no card with this name";
        else {
            response = showCard(arguments);
        }
        return response;
    }

    private String createDeckErrorChecker(String arguments) {
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

    private String deleteDeckErrorChecker(String arguments) {
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

    private String setActiveDeckErrorChecker(String arguments) {
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

    private String addCardToDeckErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)((?:-\\w)|(?:--\\w+))\\b(.*?)(?=(?: -[-]?)|(?:$))");
        Matcher matcher = pattern.matcher(arguments);
        String cardName = null;
        String deckName = null;
        Boolean isSideDeck = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--card", "-c" -> {
                    if (cardName != null) return "invalid command";
                    cardName = matcher.group(2);
                }
                case "--deck", "-d" -> {
                    if (deckName != null) return "invalid command";
                    deckName = matcher.group(2);
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
            if (fullState) response = whichDeck + " deck is full";
            else if (Processor.loggedInUser.getDeckByName(deckName).ifMaxOfCardIsReached(cardName))
                response = "there are already three cards with name " + cardName + " in deck" + deckName;
            else {
                addCardToDeck(deckName, cardName, whichDeck);
                response = "card added to deck successfully";
            }
        }
    }

    private String removeCardFromDeckErrorChecker(String arguments) {
        return null;
    }

    private String showDeckErrorChecker(String arguments) {
        return null;
    }

    //Command Performer
    private String showCard(String cardName) {
        return Card.getCardByName(cardName).getStringForShow();
    }

    private void createDeck(String deckName) {
        Processor.loggedInUser.addDeck(new Deck(deckName));
    }

    private void deleteDeck(String deckName) {
        Processor.loggedInUser.removeDeck(Processor.loggedInUser.getDeckByName(deckName));
    }

    private void setActiveDeck(String deckName) {
        Processor.loggedInUser.setActiveDeck(Processor.loggedInUser.getDeckByName(deckName));
    }

    private void addCardToDeck(String deckName, String cardName, String whichDeck) {
        switch (whichDeck) {
            case "main" -> {
                switch (Objects.requireNonNull(Card.getTypeOfCardByName(cardName))) {
                    case "monster" ->
                            Processor.loggedInUser.getDeckByName(deckName).addCardToMainDeck(new MonsterCard(cardName));
                    case "magic" ->
                            Processor.loggedInUser.getDeckByName(deckName).addCardToMainDeck(new MagicCard(cardName));
                }
            }
            case "side" -> {
                switch (Objects.requireNonNull(Card.getTypeOfCardByName(cardName))) {
                    case "monster" ->
                            Processor.loggedInUser.getDeckByName(deckName).addCardToSideDeck(new MonsterCard(cardName));
                    case "magic" ->
                            Processor.loggedInUser.getDeckByName(deckName).addCardToSideDeck(new MagicCard(cardName));
                }
            }
        }
    }

    private void removeCardFromDeck(String deckName, String cardName, String whichDeck) {
    }

    private String showAllDecks() {
        return null;
    }

    private String showDeck(Deck deck, String whichDeck) {
        return null;
    }

    private String showCards() {
        return null;
    }

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
