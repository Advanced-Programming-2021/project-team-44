package Controller.MenusProcessor;

import Controller.Controller;
import View.Menus.Menus;

public class MainMenuProcessor {
    public String commandDistributor(int commandId) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> {

            }
            case 1 -> {

            }
        }
        return response;
    }

    //Command Performer
    private void exitMenu() {
        Controller.currentMenu = Menus.MAIN;
    } //done
}
