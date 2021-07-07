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
    public String importCardErrorChecker(String path) {
        //Command: import card PATH_TO_CARD_INFORMATION
        return importCard(path.trim());
    }

    public String exportCardErrorChecker(String... arguments) {
        //Command: export card <CARD_NAME> --path [PATH_TO_SAVE, DEFAULT=src/main/resources/dynamic/exports/CARD_NAME.json]
        String response;
        String path = arguments[1];
        String cardName = arguments[0];
        if (path.equals("")) path = "src/main/resources/dynamic/exports/" + cardName + ".json";
        if (Card.getCardByName(cardName) == null)
            response = "Card with this name doesn't exist!";
        else {
            response = exportCard(cardName, path);
        }
        return response;
    }


    //Command Performer
    public String importCard(String path) {
        File cardJsonFile = new File(path);
        String cardJson;
        try {
            cardJson = Files.readString(Paths.get(cardJsonFile.getPath()));
        } catch (IOException e) {
            return "Json files can't be accessed!";
        }
        try {
            MonsterCard tmpMonsterCard = (new Gson()).fromJson(cardJson, MonsterCard.class);
            MonsterCard.monsterCards.add(tmpMonsterCard);
        } catch (Exception e) {
            try {
                MagicCard tmpMagicCard = (new Gson()).fromJson(cardJson, MagicCard.class);
                MagicCard.magicCards.add(tmpMagicCard);
            } catch (Exception ex) {
                return "Invalid card type!";
            }
        }
        return "Card imported successfully!";
    }

    public String exportCard(String cardName, String path) {
        try {
            File exportedCardFile = new File(path);
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
        } catch (IOException e) {
            return "invalid file path";
        }
        return "Card exported successfully!\n" + "Path: " + path;
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
                import card <path>
                export card <name> [PATH_TO_SAVE, DEFAULT=src/main/resources/dynamic/exports/CARD_NAME.json]
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
