package Controller.MenusProcessor;

import Controller.Core;
import View.Menus.Menus;

public class ProfileMenuProcessor extends Processor{
    public ProfileMenuProcessor() {
        super(Menus.PROFILE);
    }

    //Command Performer
    private void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    } //done

    private void changeNickname(String newNickname) {
        //Todo
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

    //Error Checker
}
