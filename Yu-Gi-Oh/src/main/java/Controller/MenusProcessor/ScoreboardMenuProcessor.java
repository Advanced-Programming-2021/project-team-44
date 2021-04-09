package Controller.MenusProcessor;

import Controller.Controller;
import View.Menus.Menus;

public class ScoreboardMenuProcessor {
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

    //Command Performer
    private void exitMenu() {
        Controller.currentMenu = Menus.MAIN;
    } //done

    private void showScoreboard() {
        //TODO
        // rank- nickname: score
    }
}
