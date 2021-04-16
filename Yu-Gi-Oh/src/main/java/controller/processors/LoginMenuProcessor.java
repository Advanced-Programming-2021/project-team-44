package controller.processors;

import controller.Core;
import models.Account;
import view.menus.Menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuProcessor extends Processor {

    public LoginMenuProcessor() {
        super(Menus.LOGIN);
        new Account("username", "password", "nickname");
    }

    //Error Checkers
    private String createUserErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?=(?: -[-]?)|(?:$))");
        Matcher matcher = pattern.matcher(arguments);
        String username = null;
        String nickname = null;
        String password = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--username", "-u" -> {
                    if (username != null) return "invalid command";
                    username = matcher.group(2);
                }
                case "--nickname", "-n" -> {
                    if (nickname != null) return "invalid command";
                    nickname = matcher.group(2);
                }
                case "--password", "-p" -> {
                    if (password != null) return "invalid command";
                    password = matcher.group(2);
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        //Invalid Command Arguments
        if (!Account.isUsernameValid(username)) response = "invalid username";
        else if (!Account.isNicknameValid(nickname)) response = "invalid nickname";
        else if (!Account.isPasswordValid(password)) response = "invalid password";
        else if (Account.getAccountByUsername(username) != null)
            response = "user with username " + username + "already exists";
        else if (Account.getAccountByNickname(nickname) != null)
            response = "user with nickname " + nickname + "already exists";
        else {
            createUser(username, password, nickname);
            response = "user created successfully!";
        }
        return response;
    }//done

    private String loginUserErrorChecker(String arguments) {
        //TODO
        return null;
    }

    //Command Performer
    private void createUser(String username, String password, String nickname) {
        new Account(username, password, nickname);
    }//done

    private void loginUser(String username, String password) {
        //TODO
        //if login == true
        Core.currentMenu = Menus.MAIN;
        Processor.loggedInUser = Account.getAccountByUsername(username);
    }

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> exitMenu();
            case 2 -> response = showMenu();
            case 3 -> response = createUserErrorChecker(commandArguments);
            case 4 -> response = loginUserErrorChecker(commandArguments);
        }
        return response;
    }//done

    @Override
    protected String enterMenuErrorChecker(String input) {
        return "please login first";
    }//done

    @Override
    protected void enterMenu(Menus menu) {
    }//done

    @Override
    protected void exitMenu() {
        System.exit(0);
    } //done
}
