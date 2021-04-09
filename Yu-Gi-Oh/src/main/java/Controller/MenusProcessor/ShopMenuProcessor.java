package Controller.MenusProcessor;

import Controller.Controller;
import View.Menus.Menus;

public class ShopMenuProcessor {
    public String commandDistributor(int commandId) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> {

            }
            case 1 -> {

            }
            case 2 -> {

            }
        }
        return response;
    }

    //Command Performer
    private void exitMenu() {
        Controller.currentMenu = Menus.MAIN;
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
}
