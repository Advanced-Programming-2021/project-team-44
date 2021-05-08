package models.cards;

public enum MagicIcon {
    EQUIP("Equip"),
    FIELD("Field"),
    QUICK_PLAY("Quick-play"),
    RITUAL("Ritual"),
    CONTINUOUS("Continuous"),
    COUNTER("Counter"),
    NORMAL("Normal");

    public final String stringName;

    MagicIcon(String stringName) {
        this.stringName = stringName;
    }
}
