package controller.processors;

import controller.Core;
import models.Menus;
import models.cards.Card;
import models.utils.comparators.CardSortByName;

public class ShopMenuProcessor extends Processor { //DONE
    private static ShopMenuProcessor instance;

    public ShopMenuProcessor() {
        super(Menus.SHOP);
    }

    public static ShopMenuProcessor getInstance() {
        if (instance == null) {
            instance = new ShopMenuProcessor();
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

    public String buyCardErrorChecker(String arguments) {
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
    public String showCard(String cardName) {
        return Card.getCardByName(cardName).getStringForShow();
    }

    public void buyCard(String cardName) {
        loggedInUser.decreaseCoin(Card.getCardByName(cardName).getPrice());
        loggedInUser.addCard(Card.getCardByName(cardName));
    }

    public String showAllCards() {
        Card.allCards.sort(new CardSortByName());
        StringBuilder response = new StringBuilder();
        for (Card card : Card.allCards)
            response.append(card.getStringForAllCardsShow()).append("\n");
        response.deleteCharAt(response.length() - 1);
        return response.toString();
    }

    public String increaseMoneyByCheat(String arguments) {
        //Cheat Enhanced
        int amount;
        try {
            amount = Integer.parseInt(arguments);
        } catch (Exception e) {
            return "invalid value";
        }
        loggedInUser.increaseCoin(amount);
        return amount + " coins was successfully added to your account, of course by cheats! shame on cheater!";
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
                card show <name>
                shop buy <name>
                shop show --all
                increase --money <amount> (cheat)
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
