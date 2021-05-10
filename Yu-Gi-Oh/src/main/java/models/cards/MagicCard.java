package models.cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MagicCard extends Card{
    public static ArrayList<MagicCard> magicCards;
    protected MagicType type;
    protected MagicIcon icon;
    private String status;

    static {
        magicCards = new ArrayList<>();
    }

    protected MagicCard() {
    }

    public static MagicCard createMagicCard(String name) {
        MagicCard template = (MagicCard) Card.getCardByName(name);
        assert template != null;
        return (MagicCard) template.clone();
    }

    //Utils
    public static String magicCardsJsonParser() {
        //Generates Json files from csv file
        //TODO LOG
        ArrayList<Card> magicCards = new ArrayList<>();
        String path = "src/main/resources/static/cards/SpellTrap.csv";
        try {
            File source = new File(path);
            Scanner reader = new Scanner(source);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                HashMap<String, String> toBeAddedCard = getHashMapFromString(data);
                //File Creation
                File directory = new File("src/main/resources/static/cards/magics");
                directory.mkdir();
                String newCardFilePath = "src/main/resources/static/cards/magics/" + toBeAddedCard.get("Name") + ".json";
                File newCardFile = new File(newCardFilePath);
                try {
                    FileWriter writer = new FileWriter(newCardFile.getPath());
                    String jsonData = generateJSONByHashMap(toBeAddedCard);
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse magics JSON Files!";
                }
            }
            reader.close();
            return "Magics json files parsed successfully.";
        } catch (FileNotFoundException e) {
            return "Magics source file missing!";
        }
    }

    public static String addMagicCardFromJSON() {
        //Imports magic cards from json source files
        String response = "";
        File magicCardsDirectory = new File("src/main/resources/static/cards/magics");
        File[] magicJsonFiles = magicCardsDirectory.listFiles();
        if (magicJsonFiles == null) {
            response += "Magic Json files missing! Parsing Json files...\n";
            response += magicCardsJsonParser() + "\n";
            magicJsonFiles = magicCardsDirectory.listFiles();
        }
        assert magicJsonFiles != null;
        for (File file : magicJsonFiles) {
            String magicJson;
            try {
                magicJson = Files.readString(Paths.get(file.getPath()));
            } catch (IOException e) {
                response += "Json files can't be accessed!";
                return response;
            }
            MagicCard tmpMagicCard = (new Gson()).fromJson(magicJson, MagicCard.class);
            magicCards.add(tmpMagicCard);
        }
        for (SpellCardName name : SpellCardName.values())
            if (getMagicCardByName(name.stringName) == null) {
                response += name.stringName + " card is missing!";
                return response;
            }
        for (TrapCardName name : TrapCardName.values())
            if (getMagicCardByName(name.stringName) == null) {
                response += name.stringName + " card is missing!";
                return response;
            }
        response += "All magic cards added successfully!";
        return response;
    }

    public static HashMap<String, String> getHashMapFromString(String data) {
        String[] dataSplit = data.split(",(?!\\s)");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", Card.cardNameFilter(dataSplit[0]).trim());
        hashMap.put("Type", dataSplit[1].trim());
        hashMap.put("Icon", dataSplit[2].trim());
        hashMap.put("Description", Card.descriptionFilter(dataSplit[3]).trim());
        hashMap.put("Status", dataSplit[4].trim());
        hashMap.put("Price", dataSplit[5].trim());
        return hashMap;
    }

    public static String generateJSONByHashMap(HashMap<String, String> hashMap) {
        String jsonData = "{\"name\":\"" + hashMap.get("Name") + "\", " +
                "\"type\":\"" + hashMap.get("Type") + "\", " +
                "\"icon\":\"" + hashMap.get("Icon") + "\", " +
                "\"description\":\"" + hashMap.get("Description") + "\", " +
                "\"status\":\"" + hashMap.get("Status") + "\", " +
                "\"price\":" + Integer.parseInt(hashMap.get("Price")) + "}";
        return jsonData;
    }

    //Getters and Setters
    public static MagicCard getMagicCardByName(String name) {
        for (MagicCard card : magicCards)
            if (name.equals(card.getName()))
                return card;
        return null;
    }

    public MagicType getType() {
        return type;
    }

    public MagicIcon getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String getStringForShow() {
        String magicCardInfo;
        magicCardInfo = "Name: " + this.getName() + "\n" +
                this.getType() + "\n" +
                "Type: " + this.getIcon() + "\n" +
                "Description: " + this.getDescription();
        return magicCardInfo;
    }

    @Override
    public HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", this.name);
        hashMap.put("Type", this.type.stringName);
        hashMap.put("Icon", this.icon.stringName);
        hashMap.put("Description", this.description);
        hashMap.put("Status", this.status);
        hashMap.put("Price", String.valueOf(this.price));
        return hashMap;
    }

    @Override
    protected Object clone() {
        MagicCard dummy = new MagicCard();
        dummy.name = this.name;
        dummy.description = this.description;
        dummy.price = this.price;
        dummy.type = this.type;
        dummy.icon = this.icon;
        dummy.status = this.status;
        return dummy;
    }
}
