package controller.processors;

import models.cards.Card;
import models.Deck;
import view.menus.Menus;

public class DeckMenuProcessor extends Processor {
    public DeckMenuProcessor() {
        super(Menus.DECK);
        new Deck("sample");
    }

    //Error Checker
    private String showCard(String input) {
        return null;
    }

    private String createDeckErrorChecker(String input) {
        return null;
    }

    private String deleteDeckErrorChecker(String input) {
        return null;
    }

    private String setActiveDeckErrorChecker(String input) {
        return null;
    }

    private String addCardToDeckErrorChecker(String input) {
        return null;
    }

    private String removeCardFromDeckErrorChecker(String input) {
        return null;
    }

    private String showDeckErrorChecker(String input) {
        return null;
    }

    //Command Performer
    private String showCard(Card card) {
        return null;
    }

    private void createDeck(Deck deck) {
    }

    private void deleteDeck(Deck deck) {
    }

    private void setActiveDeck(Deck deck) {
    }

    private void addCardToDeck(Deck deck, Card card, String whichDeck) {
    }

    private void removeCardFromDeck(Deck deck, Card card, String whichDeck) {
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
