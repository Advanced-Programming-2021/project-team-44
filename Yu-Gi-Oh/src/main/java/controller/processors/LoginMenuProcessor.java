package controller.processors;

import controller.Core;
import models.Account;
import view.menus.Menus;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuProcessor extends Processor { //DONE

    public LoginMenuProcessor() {
        super(Menus.LOGIN);
    }

    //Error Checkers
    private String createUserErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        String username = null;
        String nickname = null;
        String password = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--username", "-u" -> {
                    if (username != null) return "invalid command";
                    username = matcher.group(2).trim();
                }
                case "--nickname", "-n" -> {
                    if (nickname != null) return "invalid command";
                    nickname = matcher.group(2).trim();
                }
                case "--password", "-p" -> {
                    if (password != null) return "invalid command";
                    password = matcher.group(2).trim();
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (username == null || nickname == null || password == null) return "invalid command";

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

    private String loginUserErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        String username = null;
        String password = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--username", "-u" -> {
                    if (username != null) return "invalid command";
                    username = matcher.group(2).trim();
                }
                case "--password", "-p" -> {
                    if (password != null) return "invalid command";
                    password = matcher.group(2).trim();
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (username == null || password == null) return "invalid command";

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
    private void createUser(String username, String password, String nickname) {
        new Account(username, password, nickname);
    }

    private void loginUser(String username) {
        Core.currentMenu = Menus.MAIN;
        Processor.loggedInUser = Account.getAccountByUsername(username);
    }

    @Override
    public String process(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> {
                response = "";
                exitMenu();
            }
            case 2 -> response = showMenu();
            case 3 -> response = createUserErrorChecker(commandArguments);
            case 4 -> response = loginUserErrorChecker(commandArguments);
            case 99 -> response = help();
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        return "please login first";
    }

    @Override
    protected String help() {
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
    protected void enterMenu(Menus menu) {
    }

    @Override
    protected void exitMenu() {
        Core.destruct();
    }
}
