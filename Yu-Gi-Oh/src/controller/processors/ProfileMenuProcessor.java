package controller.processors;

import controller.Core;
import models.Account;
import view.menus.Menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuProcessor extends Processor {
    public ProfileMenuProcessor() {
        super(Menus.PROFILE);
    }

    //Error Checker
    private String changeNicknameErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?=(?: -[-]?)|(?:$))");
        Matcher matcher = pattern.matcher(arguments);
        String newNickname = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--nickname", "-n" -> {
                    if (newNickname != null) return "invalid command";
                    newNickname = matcher.group(2);
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        //Invalid Command Arguments
        if (!Account.isNicknameValid(newNickname)) response = "invalid Nickname";
        else if (Account.getAccountByNickname(newNickname) != null)
            response = "user with nickname" + newNickname + "already exists";
        else {
            changeNickname(newNickname);
            response = "nickname changed successfully!";
        }
        return response;
    }

    private String changePasswordErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?=(?: -[-]?)|(?:$))");
        Matcher matcher = pattern.matcher(arguments);
        String currentPassword = null;
        String newPassword = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--current", "-c" -> {
                    if (currentPassword != null) return "invalid command";
                    currentPassword = matcher.group(2);
                }
                case "--new", "-n" -> {
                    if (newPassword != null) return "invalid command";
                    newPassword = matcher.group(2);
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        //Invalid Command Arguments
        if (!Account.isPasswordValid(currentPassword)) response = "invalid current Password";
        else if (!Account.isPasswordValid(newPassword)) response = "invalid new Password";
        else if (!loggedInUser.getPassword().equals(currentPassword)) response = "current password is invalid";
        else if (currentPassword.equals(newPassword)) response = "please enter a new password";
        else {
            changePassword(newPassword);
            response = "password changed successfully!";
        }
        return response;
    }

    //Command Performer
    private void changeNickname(String newNickname) {
        loggedInUser.setNickname(newNickname);
    }

    private void changePassword(String newPassword) {
        loggedInUser.setPassword(newPassword);
    }

    private String showProfile() {
        String response;
        response = "----------------------------------------\n" +
                "Nickname: " + loggedInUser.getNickname() + "\n" +
                "Username: " + loggedInUser.getUsername() + "\n" +
                "Bio: <bio>\n" +
                "Rank: <rank>\n" +
                "Score: " + loggedInUser.getScore() + " pts\n" +
                "Coin: " + loggedInUser.getCoin() + "\n" +
                "----------------------------------------";
        return response;
    } //Option //TODO

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> exitMenu();
            case 2 -> response = showMenu();
            case 3 -> response = changePasswordErrorChecker(commandArguments);
            case 4 -> response = changeNicknameErrorChecker(commandArguments);
            case 5 -> response = showProfile();
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        return "menu navigation is not possible";
    }

    @Override
    protected void enterMenu(Menus menu) {
    }

    @Override
    protected void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    }
}
