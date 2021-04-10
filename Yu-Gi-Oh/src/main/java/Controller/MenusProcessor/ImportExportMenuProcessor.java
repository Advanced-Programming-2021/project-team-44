package Controller.MenusProcessor;

import Controller.Core;
import View.Menus.Menus;

public class ImportExportMenuProcessor extends Processor{
    public ImportExportMenuProcessor() {
        super(Menus.IMPORTEXPORT);
    }

    //Command Performer
    private void exitMenu() {
        Core.currentMenu = Menus.MAIN;
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
