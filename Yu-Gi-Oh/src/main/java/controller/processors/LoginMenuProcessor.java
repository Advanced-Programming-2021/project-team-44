package controller.processors;

import models.Account;
import view.menus.Menus;

public class LoginMenuProcessor extends Processor {//0

    public LoginMenuProcessor() {
        super(Menus.LOGIN);
        new Account("username", "password", "nickname");
    }

    //Error Checkers
    private String createUserErrorChecker(String input) {
        //TODO
        /*
        username
        password
        nickname

        if
        else if
        else {
        createUser(input)
        }
         */
        return null;
    }

    private String loginUserErrorChecker(String input) {
        //TODO
        return null;
    }

    //Command Performer
    private void createUser(String username, String password, String nickname) {
        //TODO
    }

    private void loginUser(String username, String password) {
        //TODO
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
    protected void exitMenu() {
        System.exit(0);
    } //done
}
