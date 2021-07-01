package models.cards;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MonsterCard extends Card {
    public static ArrayList<MonsterCard> monsterCards;
    private int level;
    private MonsterCardAttribute attribute;
    private String monsterType;
    private String cardType;
    private int attackPoint;
    private int defensePoint;

    static {
        monsterCards = new ArrayList<>();
    }

    private MonsterCard() {
    }

    public static MonsterCard createMonsterCard(String name) {
        MonsterCard template = (MonsterCard) Card.getCardByName(name);
        assert template != null;
        return (MonsterCard) template.clone();
    }

    //Utils
    public static String monsterCardsJsonParser() {
        //Generates Json files from csv file
        String path = "src/main/resources/static/cards/Monster.csv";
        try {
            File source = new File(path);
            Scanner reader = new Scanner(source);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                HashMap<String, String> toBeAddedCard = getHashMapFromString(data);
                //File Creation
                File directory = new File("src/main/resources/static/cards/monsters");
                directory.mkdir();
                String newCardFilePath = "src/main/resources/static/cards/monsters/" + toBeAddedCard.get("Name") + ".json";
                File newCardFile = new File(newCardFilePath);
                try {
                    FileWriter writer = new FileWriter(newCardFile.getPath());
                    String jsonData = generateJsonByHashMap(toBeAddedCard);
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse monsters JSON files!";
                }
            }
            reader.close();
            return "Monsters json files parsed successfully.";
        } catch (FileNotFoundException e) {
            return "Monsters source file missing!";
        }
    }

    public static String addMonsterCardFromJSON() {
        //Imports monster cards from json source files
        String response = "";
        File monsterCardsDirectory = new File("src/main/resources/static/cards/monsters");
        File[] monstersJsonFiles = monsterCardsDirectory.listFiles();
        if (monstersJsonFiles == null) {
            response += "Monsters Json files missing! Parsing Json files...\n";
            response += monsterCardsJsonParser() + "\n";
            monstersJsonFiles = monsterCardsDirectory.listFiles();
        }
        assert monstersJsonFiles != null;
        for (File file : monstersJsonFiles) {
            String monsterJson;
            try {
                monsterJson = Files.readString(Paths.get(file.getPath()));
            } catch (IOException e) {
                response += "Json files can't be accessed!";
                return response;
            }
            MonsterCard tmpMonsterCard = (new Gson()).fromJson(monsterJson, MonsterCard.class);
            monsterCards.add(tmpMonsterCard);
        }
        for (MonsterCardName name : MonsterCardName.values())
            if (getMonsterCardByName(name.stringName) == null) {
                response += name.stringName + " card is missing!";
                return response;
            }
        response += "All monster cards added successfully!";
        Card.allCards.addAll(monsterCards);
        return response;
    }

    public static HashMap<String, String> getHashMapFromString(String data) {
        String[] dataSplit = data.split(",(?!\\s)");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", Card.cardNameFilter(dataSplit[0]).trim());
        hashMap.put("Level", dataSplit[1].trim());
        hashMap.put("Attribute", dataSplit[2].trim());
        hashMap.put("Monster Type", dataSplit[3].trim());
        hashMap.put("Card Type", dataSplit[4].trim());
        hashMap.put("Attack", dataSplit[5].trim());
        hashMap.put("Defense", dataSplit[6].trim());
        hashMap.put("Description", Card.descriptionFilter(dataSplit[7]).trim());
        hashMap.put("Price", dataSplit[8].trim());
        return hashMap;
    }

    public static String generateJsonByHashMap(HashMap<String, String> hashMap) {
        return "{\"name\":\"" + hashMap.get("Name") + "\", " +
                "\"level\":" + Integer.parseInt(hashMap.get("Level")) + ", " +
                "\"attribute\":\"" + hashMap.get("Attribute") + "\", " +
                "\"monsterType\":\"" + hashMap.get("Monster Type") + "\", " +
                "\"cardType\":\"" + hashMap.get("Card Type") + "\", " +
                "\"attackPoint\":" + Integer.parseInt(hashMap.get("Attack")) + ", " +
                "\"defensePoint\":" + Integer.parseInt(hashMap.get("Defense")) + ", " +
                "\"description\":\"" + hashMap.get("Description") + "\", " +
                "\"price\":" + Integer.parseInt(hashMap.get("Price")) + "}";
    }

    //Getters
    public static MonsterCard getMonsterCardByName(String name) {
        for (MonsterCard card : monsterCards)
            if (name.equals(card.getName()))
                return card;
        return null;
    }

    public int getLevel() {
        return level;
    }

    public MonsterCardAttribute getAttribute() {
        return attribute;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public String getCardType() {
        return cardType;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public boolean isAvailableForNormalSummon() {
        return switch (this.name) {
            case "Crab Turtle", "Skull Guardian", "Gate Guardian", "The Tricky" -> false;
            default -> true;
        };
    }

    public boolean isAvailableForSpecialSummon() {
        return switch (this.name) {
            case "Gate Guardian", "The Tricky" -> true;
            default -> false;
        };
    }

    public boolean isAvailableForRitualSummon() {
        return switch (this.name) {
            case "Crab Turtle", "Skull Guardian" -> true;
            default -> false;
        };
    }

    public String getEffectType() {
        if (this.cardType.equals("Effect")) {
            switch (this.getName()) {
                case "Command Knight", "Herald of Creation", "Mirage Dragon", "Texchanger" , "Marshmallon", "Scanner" -> {
                    return "Continuous";
                }
                default -> {
                    return "One-Time";
                }
            }
        }
        return null;
    }

    //Setters
    public void increaseAttackPoint(int amount) {
        this.attackPoint += amount;
    }

    public void increaseDefensePoint(int amount) {
        this.defensePoint += amount;
    }

    @Override
    public String getStringForShow() {
        String monsterCardInfo;
        monsterCardInfo = "Name: " + this.getName() + "\n" +
                "Level: " + this.getLevel() + "\n" +
                "Type: " + this.getMonsterType() + "\n" +
                "ATK: " + this.getAttackPoint() + "\n" +
                "DEF: " + this.getDefensePoint() + "\n" +
                "Description: " + this.getDescription();
        return monsterCardInfo;
    }

    @Override
    public HashMap<String, String> getHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", this.name);
        hashMap.put("Level", String.valueOf(this.level));
        hashMap.put("Attribute", this.attribute.stringName);
        hashMap.put("Monster Type", this.monsterType);
        hashMap.put("Card Type", this.cardType);
        hashMap.put("Attack", String.valueOf(this.attackPoint));
        hashMap.put("Defense", String.valueOf(this.defensePoint));
        hashMap.put("Description", this.description);
        hashMap.put("Price", String.valueOf(this.price));
        return hashMap;
    }

    @Override
    public Object clone() {
        MonsterCard dummy = new MonsterCard();
        dummy.name = this.name;
        dummy.description = this.description;
        dummy.price = this.price;
        dummy.level = this.level;
        dummy.attribute = this.attribute;
        dummy.monsterType = this.monsterType;
        dummy.cardType = this.cardType;
        dummy.attackPoint = this.attackPoint;
        dummy.defensePoint = this.defensePoint;
        return dummy;
    }

    public boolean equals(MonsterCard monsterCard) {
        if (!this.name.equals(monsterCard.name)) return false;
        if (!this.description.equals(monsterCard.description)) return false;
        if (this.price != monsterCard.price) return false;
        if (this.level != monsterCard.level) return false;
        if (this.attribute != monsterCard.attribute) return false;
        if (!this.monsterType.equals(monsterCard.monsterType)) return false;
        if (!this.cardType.equals(monsterCard.cardType)) return false;
        if (this.attackPoint != monsterCard.attackPoint) return false;
        if (this.defensePoint != monsterCard.defensePoint) return false;
        return true;
    }
}
