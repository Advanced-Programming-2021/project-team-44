package controller.processors;

import view.menus.Menus;

public class MainMenuProcessor extends Processor {//0
    public MainMenuProcessor() {
        super(Menus.MAIN);
    }

    //Error Checker
    private String duelStartErrorChecker(String input) {
        return null;
    }

    //Command Performer
    private String userLogout() {
        return null;
    }

    private void duelStart(String player1, String player2) {

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

    @Override
    protected String enterMenuErrorChecker(String input) {
        return null;
    }

    @Override
    protected void enterMenu(Menus menu) {
    }

    @Override
    protected void exitMenu() {}
}
