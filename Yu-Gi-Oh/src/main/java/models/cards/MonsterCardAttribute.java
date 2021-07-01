package models.cards;

public enum MonsterCardAttribute {
    DARK("Dark"),
    EARTH("Earth"),
    FIRE("Fire"),
    LIGHT("Light"),
    WATER("Water"),
    WIND("Wind");

    public final String stringName;

    MonsterCardAttribute(String stringName) {
        this.stringName = stringName;
    }
}
