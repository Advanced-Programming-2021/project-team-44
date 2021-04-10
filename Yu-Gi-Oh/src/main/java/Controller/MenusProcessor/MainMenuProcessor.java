package Controller.MenusProcessor;

import Controller.Core;
import View.Menus.Menus;

public class MainMenuProcessor extends Processor{
    public MainMenuProcessor() {
        super(Menus.MAIN);
    }

    //Command Performer
    private void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    } //done

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
