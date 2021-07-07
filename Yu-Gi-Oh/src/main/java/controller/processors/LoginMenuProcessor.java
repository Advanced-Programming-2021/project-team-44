package controller.processors;

import controller.Core;
import models.Account;
import models.Menus;

import java.util.Objects;

public class LoginMenuProcessor extends Processor { //DONE

    private static LoginMenuProcessor instance;

    protected LoginMenuProcessor() {
        super(Menus.LOGIN);
    }

    public static LoginMenuProcessor getInstance() {
        if (instance == null) {
            instance = new LoginMenuProcessor();
        }
        return instance;
    }

    //Error Checkers
    public String createUserErrorChecker(String... arguments) {
        String response;

        String username = arguments[0];
        String nickname = arguments[1];
        String password = arguments[2];

        //Invalid Command Arguments
        if (!Account.isUsernameValid(username)) response = "invalid username";
        else if (!Account.isNicknameValid(nickname)) response = "invalid nickname";
        else if (!Account.isPasswordValid(password)) response = "invalid password";
        else if (Account.getAccountByUsername(username) != null)
            response = "user with username " + username + " already exists";
        else if (Account.getAccountByNickname(nickname) != null)
            response = "user with nickname " + nickname + " already exists";
        else {
            createUser(username, password, nickname);
            response = "user created successfully!";
        }
        return response;
    }

    public String loginUserErrorChecker(String... arguments) {
        String response;
        String username = arguments[0];
        String password = arguments[1];

        //Invalid Command Arguments
        if (!Account.isUsernameValid(username)) response = "invalid username";
        else if (!Account.isPasswordValid(password)) response = "invalid password";
        else if (Account.getAccountByUsername(username) == null)
            response = "Username and password did not match!";
        else if (!Objects.requireNonNull(Account.getAccountByUsername(username)).getPassword().equals(password))
            response = "Username and password did not match!";
        else {
            loginUser(username);
            response = "user logged in successfully!";
        }
        return response;
    }

    //Command Performer
    public void createUser(String username, String password, String nickname) {
        new Account(username, password, nickname);
    }

    public void loginUser(String username) {
        Core.currentMenu = Menus.MAIN;
        Processor.loggedInUser = Account.getAccountByUsername(username);
    }

    @Override
    public String enterMenuErrorChecker(String input) {
        return "please login first";
    }

    @Override
    public String help() {
        return """
                * Commands in this Menu:
                menu enter <name>
                exit
                menu show-current
                user create <username> <nickname> <password>
                user login <username> <password>
                help
                """;
    }

    @Override
    public void enterMenu(Menus menu) {
    }

    @Override
    public void exitMenu() {
        Core.destruct();
    }
}
