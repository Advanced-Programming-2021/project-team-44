package Controller.MenusProcessor;

import Controller.Core;
import View.Menus.Menus;

public class ScoreboardMenuProcessor extends Processor{
    public ScoreboardMenuProcessor() {
        super(Menus.SCOREBOARD);
    }

    //Command Performer
    private void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    } //done

    private void showScoreboard() {
        //TODO
        // rank- nickname: score
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
