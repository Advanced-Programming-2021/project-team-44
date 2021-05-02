package controller.processors;

import controller.Core;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import view.menus.Menus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportExportMenuProcessor extends Processor {//0

    public ImportExportMenuProcessor() {
        super(Menus.IMPORTEXPORT);
    }

    //Error Checker
    private String importCardErrorChecker(String arguments) {
        //Command: import card PATH_TO_CARD_INFORMATION
        return importCard(arguments.trim());
    }

    private String exportCardErrorChecker(String arguments) {
        //Command: export card <CARD_NAME> --path [ABSOLUTE_PATH_TO_SAVE, DEFAULT=./data/exports/CARD_NAME.json]
        String response;
        Pattern pattern = Pattern.compile("(?=\\B)(?:--path|-p)\\s+(.+?)");
        Matcher matcher = pattern.matcher(arguments);
        String path;
        String cardName;
        if (matcher.find()) {
            path = arguments.substring(matcher.start(), matcher.end());
            arguments = arguments.substring(0, matcher.start()) + arguments.substring(matcher.end());
        } else path = File.separator + "." +
                File.separator + "data" +
                File.separator + "exports" +
                File.separator + arguments.trim() + ".json";
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
    private String importCard(String path) {
        try {
            File toBeImportedFile = new File(path);
            Scanner importFileReader = new Scanner(toBeImportedFile);
            while (importFileReader.hasNextLine()) {
                String data = importFileReader.nextLine().trim();
                HashMap<String, String> cardHashMap = new HashMap<>();
                try {
                    cardHashMap = MonsterCard.getHashMapFromString(data);
                } catch (ArrayIndexOutOfBoundsException ae) {
                    cardHashMap = MagicCard.getHashMapFromString(data);
                } catch (Exception e) {
                    return "Input format is invalid!";
                }

                File importedCardFile;
                try {
                    String jsonData;
                    if (cardHashMap.size() == 6) {
                        //Magic
                        importedCardFile = new File(File.separator + "." +
                                File.separator + "data" +
                                File.separator + "static" +
                                File.separator + "cards" +
                                File.separator + "magic" +
                                File.separator + cardHashMap.get("Name") + ".json");

                        jsonData = MagicCard.generateJSONByHashMap(cardHashMap);
                    } else {
                        //Monster
                        importedCardFile = new File(File.separator + "." +
                                File.separator + "data" +
                                File.separator + "static" +
                                File.separator + "cards" +
                                File.separator + "monster" +
                                File.separator + cardHashMap.get("Name") + ".json");

                        jsonData = MonsterCard.generateJSONByHashMap(cardHashMap);
                    }
                    FileWriter importedCardWriter = new FileWriter(importedCardFile.getAbsolutePath());
                    importedCardWriter.write(jsonData);
                    importedCardWriter.close();
                } catch (IOException e) {
                    return "An error occurred!";
                }
            }
            importFileReader.close();
        } catch (FileNotFoundException e) {
            return "Input file doesn't exist!";
        }
        return "Cards imported successfully!";
    }

    private void exportCard(String cardName, String path) {
        File exportedCardFile = new File(path);
        try {
            Card toBeExportedCard = Card.getCardByName(cardName);
            String exportedCardData;
            if (toBeExportedCard instanceof MonsterCard) {
                MonsterCard toBeExportedMonsterCard = (MonsterCard) toBeExportedCard;
                exportedCardData = MonsterCard.generateJSONByHashMap(toBeExportedMonsterCard.getHashMap());

            } else if (toBeExportedCard instanceof MagicCard) {
                MagicCard toBeExportedMagicCard = (MagicCard) toBeExportedCard;
                exportedCardData = MagicCard.generateJSONByHashMap(toBeExportedMagicCard.getHashMap());

            } else exportedCardData = "";
            FileWriter importedCardWriter = new FileWriter(exportedCardFile.getAbsolutePath());
            importedCardWriter.write(exportedCardData);
            importedCardWriter.close();
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public String commandDistributor(int commandId, String commandArguments) {
        String response = "invalid command";
        switch (commandId) {
            case 0 -> response = enterMenuErrorChecker(commandArguments);
            case 1 -> {
                response = "";
                exitMenu();
            }
            case 2 -> response = showMenu();
            case 3 -> response = importCardErrorChecker(commandArguments);
            case 4 -> response = exportCardErrorChecker(commandArguments);
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
