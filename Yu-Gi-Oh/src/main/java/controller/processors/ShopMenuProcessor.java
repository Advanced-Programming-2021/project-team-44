package controller.processors;

import models.Account;
import models.cards.Card;
import view.menus.Menus;
import models.cards.MagicCard;
import models.cards.MonsterCard;

public class ShopMenuProcessor extends Processor {
    public ShopMenuProcessor() {
        super(Menus.SHOP);
        new MonsterCard();
        new MagicCard();
        new Account("username", "password", "nickname");
    }

    //Error Checker
    private String showCardErrorChecker(String input) {
        return null;
    }

    private String buyCardErrorChecker(String input) {
        //TODO
        return null;
    }

    //Command Performer
    private void showCard(Card card) {

    }

    private void buyCard(String cardName) {
        //TODO
    }

    private String showAllCards() {
        //TODO
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
