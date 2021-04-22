package models.cards;

public class MonsterCard extends Card{
    private int level;
    private MonsterCardAttribute attribute;
    private String type;
    private int attackPoint;
    private int defensePoint;

    public MonsterCard() {
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                "Type: " + this.getType() + "\n" +
                "ATK: " + this.getAttackPoint() + "\n" +
                "DEF: " + this.getDefensePoint() + "\n" +
                "Description: " + this.getDescription();
        return monsterCardInfo;
    }
}
