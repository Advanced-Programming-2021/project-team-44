package Controller.MenusProcessor;

import Controller.Controller;
import View.Menus.Menus;

public class ImportExportMenuProcessor {
    public String commandDistributor(int commandId) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> {

            }
            case 1 -> {

            }
        }
        return response;
    }

    //Command Performer
    private void exitMenu() {
        Controller.currentMenu = Menus.MAIN;
    } //done

    private void importCard(String cardName) {
        //TODO
    }

    private void exportCard(String cardName) {
        //TODO
    }

    //Error Checker
    private void importCardErrorChecker(String input) {
        //TODO
    }

    private void exportCardErrorChecker(String input) {
        //TODO
    }
}
