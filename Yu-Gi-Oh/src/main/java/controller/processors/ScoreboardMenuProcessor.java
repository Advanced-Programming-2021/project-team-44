package controller.processors;

import models.Account;
import view.menus.Menus;

public class ScoreboardMenuProcessor extends Processor {
    public ScoreboardMenuProcessor() {
        super(Menus.SCOREBOARD);
        new Account("username", "password", "nickname");
    }

    //Command Performer
    private String showScoreboard() {
        //TODO
        // rank- nickname: score
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
