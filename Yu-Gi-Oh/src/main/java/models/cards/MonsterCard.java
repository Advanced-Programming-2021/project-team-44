package models.cards;

import java.util.HashMap;

public class MonsterCard extends Card{
    private int level;
    private MonsterCardAttribute attribute;
    private String monsterType;
    private String cardType;
    private int attackPoint;
    private int defensePoint;

    public MonsterCard() {
    }

    //Utils
    public static String generateJSONByHashMap(HashMap<String, String> hashMap) {
        String jsonData = "{\"name\":\"" + hashMap.get("Name") + "\", " +
                "\"level\":\"" + Integer.parseInt(hashMap.get("Level")) + "\", " +
                "\"attribute\":\"" + hashMap.get("Attribute") + "\", " +
                "\"monsterType\":\"" + hashMap.get("Monster Type") + "\", " +
                "\"cardType\":\"" + hashMap.get("Card Type") + "\", " +
                "\"attack\":\"" + Integer.parseInt(hashMap.get("Attack")) + "\", " +
                "\"defense\":\"" + Integer.parseInt(hashMap.get("Defense")) + "\", " +
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
