package Models.Cards;

public class MonsterCard extends Card{
    private int level;
    private MonsterCardAttribute attribute;
    private String type;
    private int attackPoint;
    private int defensePoint;

    public MonsterCard() {
    }

    @Override
    public String getStringForShow() {
        return null;
    }
}
