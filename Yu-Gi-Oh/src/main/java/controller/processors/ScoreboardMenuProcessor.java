package controller.processors;

import controller.Core;
import models.Account;
import models.Menus;
import models.utils.comparators.AccountSortByNickname;
import models.utils.comparators.AccountSortByScore;

public class ScoreboardMenuProcessor extends Processor { //DONE
    private static ScoreboardMenuProcessor instance;

    public ScoreboardMenuProcessor() {
        super(Menus.SCOREBOARD);
    }

    public static ScoreboardMenuProcessor getInstance() {
        if (instance == null) {
            instance = new ScoreboardMenuProcessor();
        }
        return instance;
    }

    //Command Performer
    public String showScoreboard() {
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
    public String enterMenuErrorChecker(String input) {
        return "menu navigation is not possible";
    }

    @Override
    public String help() {
        return """
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                scoreboard show
                help
                """;
    }

    @Override
    public void enterMenu(Menus menu) {
    }

    @Override
    public void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    }
}
