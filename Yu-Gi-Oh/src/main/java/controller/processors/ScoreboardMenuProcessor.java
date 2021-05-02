package controller.processors;

import controller.Core;
import models.Account;
import models.comparators.AccountSortByNickname;
import models.comparators.AccountSortByScore;
import view.menus.Menus;

import java.util.Collections;

public class ScoreboardMenuProcessor extends Processor {
    public ScoreboardMenuProcessor() {
        super(Menus.SCOREBOARD);
    }

    //Command Performer
    private String showScoreboard() {
        String response;
        Collections.sort(Account.accounts, new AccountSortByScore());
        Collections.sort(Account.accounts, new AccountSortByNickname());
        return response;
    }

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> {
                response = "";
                exitMenu();
            }
            case 2 -> response = showMenu();
            case 3 -> response = showScoreboard();
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        return "menu navigation is not possible";
    }

    @Override
    protected void enterMenu(Menus menu) {
    }

    @Override
    protected void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    }
}
