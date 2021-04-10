package Controller.MenusProcessor;

import Controller.Core;
import View.Menus.Menus;

public class ShopMenuProcessor extends Processor{
    public ShopMenuProcessor() {
        super(Menus.SHOP);
    }

    //Command Performer
    private void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    } //done

    private void showAllCards() {
        //TODO
    }

    private void buyCard(String cardName) {
        //TODO
    }

    //Error Checker
    private void buyCardErrorChecker(String input) {
        //TODO
    }

    @Override
    public String commandDistributor(int commandId) {
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
}
