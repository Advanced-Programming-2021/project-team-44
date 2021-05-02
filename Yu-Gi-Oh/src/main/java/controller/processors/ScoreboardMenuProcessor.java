package controller.processors;

import controller.Core;
import models.Account;
import models.comparators.AccountSortByNickname;
import models.comparators.AccountSortByScore;
import view.menus.Menus;

public class ScoreboardMenuProcessor extends Processor {
    public ScoreboardMenuProcessor() {
        super(Menus.SCOREBOARD);
    }

    //Command Performer
    private String showScoreboard() {
        StringBuilder response = new StringBuilder();
        Account.accounts.sort(new AccountSortByNickname());
        Account.accounts.sort(new AccountSortByScore());
        int rank = 1;
        for (int i = 0; i < Account.accounts.size(); i++) {
            if (i == 0)
                response.append(rank).append("- ").append(Account.accounts.get(i).getStringForScoreboard()).append("\n");
            else {
                if (i == Account.accounts.size() - 1)
                    response.append(rank).append("- ").append(Account.accounts.get(i).getStringForScoreboard());
                else {
                    if (Account.accounts.get(i - 1).getScore() > Account.accounts.get(i).getScore()) rank++;
                    response.append(rank).append("- ").append(Account.accounts.get(i).getStringForScoreboard()).append("\n");
                }
            }
        }
        return response.toString();
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
