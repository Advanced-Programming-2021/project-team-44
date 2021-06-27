package controller.processors;

import controller.Core;
import models.Account;
import view.menus.Menus;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;


public class MainMenuProcessor extends Processor { //DONE

    public MainMenuProcessor() {
        super(Menus.MAIN);
    }

    //Error Checker
    private String duelStartErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?= -[-]?|$)");
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
        if (secondPLayerUsername == null || rounds == null) return "invalid command";

        if (Account.getAccountByUsername(secondPLayerUsername) == null)
            response = "there is no player with this username";
        else if (Processor.loggedInUser.getActiveDeck() == null)
            response = Processor.loggedInUser.getUsername() + " has no active deck";
        else if (Objects.requireNonNull(Account.getAccountByUsername(secondPLayerUsername)).getActiveDeck() == null)
            response = secondPLayerUsername + " has no active deck";
        else if (!Processor.loggedInUser.getActiveDeck().isDeckValid())
            response = Processor.loggedInUser.getUsername() + " deck is invalid";
        else if (!Objects.requireNonNull(Account.getAccountByUsername(secondPLayerUsername)).getActiveDeck().isDeckValid())
            response = secondPLayerUsername + " deck is invalid";
        else if (!rounds.equals("1") && !rounds.equals("3"))
            response = "number of rounds is not supported";
        else {
            duelStart(Processor.loggedInUser.getUsername(), secondPLayerUsername, Integer.parseInt(rounds));
            response = "duel started successfully between " + Processor.loggedInUser.getUsername() + " and " + secondPLayerUsername;
        }
        return response;
    }

    private String duelStartWithAIErrorChecker(String arguments) {
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(-[-]?\\S+)\\b(.+?)(?= -[-]?|$)");
        Matcher matcher = pattern.matcher(arguments);
        String rounds = null;
        //Invalid Command
        while (matcher.find()) {
            switch (matcher.group(1)) {
                case "--rounds", "-r" -> {
                    if (rounds != null) return "invalid command";
                    rounds = matcher.group(2).trim();
                }
                default -> {
                    return "invalid command";
                }
            }
        }
        if (rounds == null) return "invalid command";

        if (Processor.loggedInUser.getActiveDeck() == null)
            response = Processor.loggedInUser.getUsername() + " has no active deck";
        else if (!Processor.loggedInUser.getActiveDeck().isDeckValid())
            response = Processor.loggedInUser.getUsername() + " deck is invalid";
        else if (!rounds.equals("1") && !rounds.equals("3"))
            response = "number of rounds is not supported";
        else {
            duelStartWithAI(Processor.loggedInUser.getUsername(), Integer.parseInt(rounds));
            response = "duel started successfully between " + Processor.loggedInUser.getUsername() + " and " + "AI";
        }
        return response;
    }

    //Command Performer
    private String userLogout() {
        Processor.loggedInUser = null;
        Core.currentMenu = Menus.LOGIN;
        return "user logged out successfully!";
    }

    private void duelStart(String player1Username, String player2Username, int rounds) {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        Core.currentMenu = Menus.PLAYER_DUEL;
        if (randomNumber == 0) {
            Account player1 = Account.getAccountByUsername(player1Username);
            Account player2 = Account.getAccountByUsername(player2Username);
            ((PlayerDuelMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.PLAYER_DUEL))).gameInitialization(player1, player2, rounds);
        } else {
            Account player2 = Account.getAccountByUsername(player1Username);
            Account player1 = Account.getAccountByUsername(player2Username);
            ((PlayerDuelMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.PLAYER_DUEL))).gameInitialization(player1, player2, rounds);
        }
    }

    private void duelStartWithAI(String player1Username, int rounds) {
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        Core.currentMenu = Menus.AI_DUEL;
        if (randomNumber == 0) {
            Account player1 = Account.getAccountByUsername(player1Username);
            ((AIDuelMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.AI_DUEL))).gameInitialization(player1, null, rounds);
        } else {
            Account player2 = Account.getAccountByUsername(player1Username);
            ((AIDuelMenuProcessor) Objects.requireNonNull(Processor.getProcessorByName(Menus.AI_DUEL))).gameInitialization(null, player2, rounds);
        }
        //TODO AI
    }


    @Override
    public String process(int commandId, String commandArguments) {
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
            case 99 -> response = help();
        }
        return response;
    }

    @Override
    protected String enterMenuErrorChecker(String input) {
        String response;
        input = input.trim();
        switch (input) {
            case "Duel", "duel", "Duel Menu", "duel menu", "Login", "login", "Login Menu", "login menu" ->
                    response = "you can't enter this menu by this command";
            case "Deck", "deck", "Deck Menu", "deck menu" -> {
                response = "";
                enterMenu(Menus.DECK);
            }
            case "Scoreboard", "scoreboard", "Scoreboard Menu", "scoreboard menu" -> {
                response = "";
                enterMenu(Menus.SCOREBOARD);
            }
            case "Profile", "profile", "Profile Menu", "profile menu" -> {
                response = "";
                enterMenu(Menus.PROFILE);
            }
            case "Shop", "shop", "Shop Menu", "shop menu" -> {
                response = "";
                enterMenu(Menus.SHOP);
            }
            case "Import/Export", "import/export", "Import/Export Menu", "import/export menu" -> {
                response = "";
                enterMenu(Menus.IMPORTEXPORT);
            }
            case "Main", "main", "Main Menu", "main menu" -> response = "you are already in Main Menu!";
            default -> response = "invalid menu name";
        }
        return response;
    }

    @Override
    protected String help() {
        return """
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                user logout
                duel --new <opponent username> <rounds>
                duel --new --ai <rounds>
                help
                """;
    }

    @Override
    protected void enterMenu(Menus menu) {
        Core.currentMenu = menu;
    }

    @Override
    protected void exitMenu() {
        Processor.loggedInUser = null;
        Core.currentMenu = Menus.LOGIN;
    }
}
