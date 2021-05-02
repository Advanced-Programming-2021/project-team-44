package models.cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MonsterCard extends Card{
    private int level;
    private MonsterCardAttribute attribute;
    private String monsterType;
    private String cardType;
    private int attackPoint;
    private int defensePoint;

    public MonsterCard(String name) {
        //TODO
    }

    //Utils
    public static HashMap<String, String> getHashMapFromString(String data) {
        String[] dataSplit = data.split(",(?!\\s)");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name", dataSplit[0]);
        hashMap.put("Level", dataSplit[1]);
        hashMap.put("Attribute", dataSplit[2]);
        hashMap.put("Monster Type", dataSplit[3]);
        hashMap.put("Card Type", dataSplit[4]);
        hashMap.put("Attack", dataSplit[5]);
        hashMap.put("Defense", dataSplit[6]);
        hashMap.put("Description", dataSplit[7]);
        hashMap.put("Price", dataSplit[8]);
        return hashMap;
    }

    public static ArrayList<Card> monsterCardsInitializer() {
        //TODO LOG
        ArrayList<Card> monsterCards = new ArrayList<>();
        String path = File.separator + "." +
                File.separator + "data" +
                File.separator + "static" +
                File.separator + "cards" +
                File.separator + "Monster.csv";
        try {
            File source = new File(path);
            Scanner reader = new Scanner(source);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                HashMap<String, String> toBeAddedCard = getHashMapFromString(data);
                //File Creation
                String newCardFilePath = File.separator + "." +
                        File.separator + "data" +
                        File.separator + "static" +
                        File.separator + "cards" +
                        File.separator + "monster" +
                        File.separator + toBeAddedCard.get("Name") + ".json";
                File newCardFile = new File(newCardFilePath);
                try {
                    FileWriter writer = new FileWriter(newCardFile.getAbsolutePath());
                    String jsonData = generateJSONByHashMap(toBeAddedCard);
                    writer.write(jsonData);
                    writer.write(jsonData);
                    writer.close();
                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();
                    MonsterCard tmpMonsterCard = gson.fromJson(jsonData, MonsterCard.class);
                    monsterCards.add(tmpMonsterCard);
                } catch (IOException e) {
                    System.out.println("Can't parse monsters JSON Files!");
                    //Error Code: Can't parse JSON files - Parse Error: 3
                    System.exit(3);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Monsters source file missing!");
            //Error Code: Source not found - Source Error: 2
            System.exit(2);
        }
        return monsterCards;
    }

    public static String generateJSONByHashMap(HashMap<String, String> hashMap) {
        String jsonData = "{\"name\":\"" + hashMap.get("Name") + "\", " +
                "\"level\":" + Integer.parseInt(hashMap.get("Level")) + ", " +
                "\"attribute\":\"" + hashMap.get("Attribute") + "\", " +
                "\"monsterType\":\"" + hashMap.get("Monster Type") + "\", " +
                "\"cardType\":\"" + hashMap.get("Card Type") + "\", " +
                "\"attackPoint\":" + Integer.parseInt(hashMap.get("Attack")) + ", " +
                "\"defensePoint\":" + Integer.parseInt(hashMap.get("Defense")) + ", " +
                "\"description\":\"" + hashMap.get("Description") + "\", " +
                "\"price\":" + Integer.parseInt(hashMap.get("Price")) + "}";
        return jsonData;
    }

    //Getters and Setters
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public MonsterCardAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(MonsterCardAttribute attribute) {
        this.attribute = attribute;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
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
}
