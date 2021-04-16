package controller.processors;

import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.menus.Menus;

public class ImportExportMenuProcessor extends Processor {//0
    public ImportExportMenuProcessor() {
        super(Menus.IMPORTEXPORT);
        new MonsterCard();
        new MagicCard();
    }

    //Error Checker
    private String importCardErrorChecker(String input) {
        //TODO
        return null;
    }

    private String exportCardErrorChecker(String input) {
        //TODO
        return null;
    }

    //Command Performer
    private void importCard(String cardName) {
        //TODO
    }

    private void exportCard(String cardName) {
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
