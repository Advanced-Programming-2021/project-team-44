package controller.processors;

import com.google.gson.Gson;
import controller.Core;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import models.Menus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportExportMenuProcessor extends Processor { //DONE
    private static ImportExportMenuProcessor instance;

    public ImportExportMenuProcessor() {
        super(Menus.IMPORTEXPORT);
    }

    public static ImportExportMenuProcessor getInstance() {
        if (instance == null) {
            instance = new ImportExportMenuProcessor();
        }
        return instance;
    }

    //Error Checker
    public String importCardErrorChecker(String arguments) {
        //Command: import card PATH_TO_CARD_INFORMATION
        return importCard(arguments.trim());
    }

    public String exportCardErrorChecker(String arguments) {
        //Command: export card <CARD_NAME> --path [PATH_TO_SAVE, DEFAULT=src/main/resources/dynamic/exports/CARD_NAME.json]
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(?:--path|-p)\\s+(.+?)");
        Matcher matcher = pattern.matcher(arguments);
        String path;
        String cardName;
        if (matcher.find()) {
            path = arguments.substring(matcher.start(), matcher.end());
            arguments = arguments.substring(0, matcher.start()) + arguments.substring(matcher.end());
        } else path = "src/main/resources/dynamic/exports/" + arguments.trim() + ".json";
        cardName = arguments.trim();

        if (Card.getCardByName(cardName) == null)
            response = "Card with this name doesn't exist!";
        else {
            exportCard(cardName, path);
            response = "Card exported successfully!";
        }
        return response;
    }


    //Command Performer
    public String importCard(String path) {
        Scanner tmpScanner = new Scanner(System.in);
        System.out.println("What type of card is it? Respond with monster or magic.");
        String response = tmpScanner.nextLine();
        tmpScanner.close();
        File cardJsonFile = new File(path);
        String cardJson;
        try {
            cardJson = Files.readString(Paths.get(cardJsonFile.getPath()));
        } catch (IOException e) {
            return "Json files can't be accessed!";
        }
        switch (response) {
            case "monster" -> {
                MonsterCard tmpMonsterCard = (new Gson()).fromJson(cardJson, MonsterCard.class);
                MonsterCard.monsterCards.add(tmpMonsterCard);
            }
            case "magic" -> {
                MagicCard tmpMagicCard = (new Gson()).fromJson(cardJson, MagicCard.class);
                MagicCard.magicCards.add(tmpMagicCard);
            }
            default -> {
                return "Invalid card type!";
            }
        }
        return "Card imported successfully!";
    }

    public void exportCard(String cardName, String path) {
        File exportedCardFile = new File(path);
        try {
            Card toBeExportedCard = Card.getCardByName(cardName);
            String exportedCardData;
            if (toBeExportedCard instanceof MonsterCard toBeExportedMonsterCard) {
                exportedCardData = MonsterCard.generateJsonByHashMap(toBeExportedMonsterCard.getHashMap());
                FileWriter importedCardWriter = new FileWriter(exportedCardFile.getPath());
                importedCardWriter.write(exportedCardData);
                importedCardWriter.close();
            } else if (toBeExportedCard instanceof MagicCard toBeExportedMagicCard) {
                exportedCardData = MagicCard.generateJSONByHashMap(toBeExportedMagicCard.getHashMap());
                FileWriter importedCardWriter = new FileWriter(exportedCardFile.getPath());
                importedCardWriter.write(exportedCardData);
                importedCardWriter.close();
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public String enterMenuErrorChecker(String input) {
        return "menu navigation is not possible";
    }

    @Override
    public String help() {
        return """
                * Commands in this Menu:
                menu enter <name>
                menu exit
                menu show-current
                import card <name> [path]
                export card <name>
                help
                """;
    }

    @Override
    public void enterMenu(Menus menu) {
    }

    @Override
    public void exitMenu() {
        Core.currentMenu = Menus.MAIN;
    }
}
