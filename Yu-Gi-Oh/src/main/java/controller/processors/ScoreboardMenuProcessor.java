package controller.processors;

import controller.Core;
import models.Account;
import models.Menus;
import models.utils.comparators.AccountSortByNickname;
import models.utils.comparators.AccountSortByScore;

import java.util.HashMap;

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
    public HashMap<Integer, Account> showScoreboard() {
        HashMap<Integer, Account> output = new HashMap<>();
        Account.accounts.sort(new AccountSortByNickname());
        Account.accounts.sort(new AccountSortByScore());
        int rank = 1;
        for (int i = 0; i < Account.accounts.size(); i++) {
            if (i != 0)
                if (Account.accounts.get(i - 1).getScore() > Account.accounts.get(i).getScore())
                    rank++;
            output.put(rank, Account.accounts.get(i));
        }
        return output;
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
