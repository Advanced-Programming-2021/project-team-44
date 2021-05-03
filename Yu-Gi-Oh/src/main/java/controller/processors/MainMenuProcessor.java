package controller.processors;

import controller.Core;
import models.Account;
import view.menus.Menus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;


public class MainMenuProcessor extends Processor {//0

    public MainMenuProcessor() {
        super(Menus.MAIN);
    }

    //Error Checker
    private String duelStartErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?=(?: -[-]?)|(?:$))");
        Matcher matcher = pattern.matcher(arguments);
        String secondPLayerUsername = null;
        String rounds = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--second-player", "-s" -> {
                    if (secondPLayerUsername != null) return "invalid command";
                    secondPLayerUsername = matcher.group(2).trim();
                }
                case "--rounds", "-r" -> {
                    if (rounds != null) return "invalid command";
                    rounds = matcher.group(2).trim();
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (secondPLayerUsername == null ||  rounds == null) return "invalid command";

        if (Account.getAccountByUsername(secondPLayerUsername) == null)
            response = "there is no player with this username";
        else if (Processor.loggedInUser.getActiveDeck() == null)
            response = Processor.loggedInUser.getUsername() + " has no active deck";
        else if (Account.getAccountByUsername(secondPLayerUsername).getActiveDeck() == null)
            response = secondPLayerUsername + " has no active deck";
        else if (!Processor.loggedInUser.getActiveDeck().isDeckValid())
            response = Processor.loggedInUser.getUsername() + " deck is invalid";
        else if (!Account.getAccountByUsername(secondPLayerUsername).getActiveDeck().isDeckValid())
            response = secondPLayerUsername + " deck is invalid";
        else if (!rounds.equals("1") && !rounds.equals("3"))
            response = "number of rounds is not supported";
        else {
            Random random = new Random();
            int randomNumber = random.nextInt(2);
            int gameRounds = Integer.parseInt(rounds);
            if (randomNumber == 0) {
                Account player1 = Processor.loggedInUser;
                Account player2 = Account.getAccountByUsername(secondPLayerUsername);
                ((DuelMenuProcessor) Processor.getProcessorByName(Menus.DUEL)).gameInitialization(player1, player2, gameRounds);
            } else {
                Account player2 = Processor.loggedInUser;
                Account player1 = Account.getAccountByUsername(secondPLayerUsername);
                ((DuelMenuProcessor) Processor.getProcessorByName(Menus.DUEL)).gameInitialization(player1, player2, gameRounds);
            }

        }
        return "";
    }

    private String duelStartWithAIErrorChecker(String arguments) {
        return null;
    }

    //Command Performer
    private String userLogout() {
        Processor.loggedInUser = null;
        Core.currentMenu = Menus.LOGIN;
        return "user logged out successfully!";
    }

    private void duelStart(String player1, String player2) {
        //TODO
    }

    private void duelStartWithAI(String player1, String player2) {
        //TODO
    }


    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> {
                response = "user logged out successfully!";
                exitMenu();
            }
            case 2 -> response = showMenu();
            case 3 -> response = userLogout();
            case 4 -> response = duelStartErrorChecker(commandArguments);
            case 5 -> response = duelStartWithAIErrorChecker(commandArguments);
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
        Processor.loggedInUser = null;
        Core.currentMenu = Menus.LOGIN;
    }
}
