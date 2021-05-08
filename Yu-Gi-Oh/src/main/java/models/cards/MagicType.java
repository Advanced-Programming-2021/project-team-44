package models.cards;

public enum MagicType {
    SPELL("Spell"),
    TRAP("Trap");

    public final String stringName;

    MagicType(String stringName) {
        this.stringName = stringName;
    }
}
