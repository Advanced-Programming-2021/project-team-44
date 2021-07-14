package controller.processors;

import controller.Core;
import models.Menus;
import models.cards.Card;
import models.utils.comparators.CardSortByName;

import java.util.Objects;

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
    public String buyCardErrorChecker(String cardName) {
        if (Card.getCardByName(cardName) == null) return  "there is no card with this name";
        else if (Objects.requireNonNull(Card.getCardByName(cardName)).getPrice() > loggedInUser.getCoin()) return  "not enough money";
        else {
            buyCard(cardName);
            return "card bought successfully";
        }
    }

    //Command Performer
    public void buyCard(String cardName) {
        loggedInUser.decreaseCoin(Objects.requireNonNull(Card.getCardByName(cardName)).getPrice());
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

    public String forceBuyCard(String cardName) {
        loggedInUser.addCard(Card.getCardByName(cardName));
        return "card bought successfully, of course by cheats!\nshame on cheater!";
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
