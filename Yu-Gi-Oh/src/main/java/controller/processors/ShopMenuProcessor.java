package controller.processors;

import controller.Core;
import models.Account;
import models.cards.Card;
import models.comparators.CardSortByName;
import view.menus.Menus;
import models.cards.MagicCard;
import models.cards.MonsterCard;

public class ShopMenuProcessor extends Processor {
    public ShopMenuProcessor() {
        super(Menus.SHOP);
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

    private String buyCardErrorChecker(String arguments) {
        String response;
        if (Card.getCardByName(arguments) == null) response = "there is no card with this name";
        else if (Card.getCardByName(arguments).getPrice() > loggedInUser.getCoin()) response = "not enough money";
        else {
            buyCard(arguments);
            response = "card bought successfully";
        }
        return response;
    }

    //Command Performer
    private String showCard(String cardName) {
        return Card.getCardByName(cardName).getStringForShow();
    }

    private void buyCard(String cardName) {
        loggedInUser.decreaseCoin(Card.getCardByName(cardName).getPrice());
        loggedInUser.addCard(Card.getCardByName(cardName));
    }

    private String showAllCards() {
        Card.allCards.sort(new CardSortByName());
        StringBuilder response = new StringBuilder();
        for (Card card : Card.allCards)
            response.append(card.getStringForAllCardsShow()).append("\n");
        response.deleteCharAt(response.length()-1);
        return response.toString();
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
            case 3 -> response = buyCardErrorChecker(commandArguments);
            case 4 -> response = showAllCards();
            case 5 -> response = showCardErrorChecker(commandArguments);
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
