package Controller.MenusProcessor;

import Controller.Core;
import View.Menus.Menus;

public class ScoreboardMenuProcessor extends Processor {
    public ScoreboardMenuProcessor() {
        super(Menus.SCOREBOARD);
    }

    //Command Performer
    private String showScoreboard() {
        //TODO
        // rank- nickname: score
        return null;
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
