package models.cards;

public enum TrapCardName {
    MAGIC_CYLINDER("Magic Cylinder"),
    MIRROR_FORCE("Mirror Force"),
    MIND_CRUSH("Mind Crush"),
    TRAP_HOLE("Trap Hole"),
    TORRENTIAL_TRIBUTE("Torrential Tribute"),
    TIME_SEAL("Time Seal"),
    NEGATE_ATTACK("Negate Attack"),
    SOLEMN_WARNING("Solemn Warning"),
    MAGIC_JAMMER("Magic Jammer"),
    CALL_OF_THE_HAUNTED("Call of The Haunted"),
    VANITYS_EMPTINESS("Vanity's Emptiness"),
    WALL_OF_REVEALING_LIGHT("Wall of Revealing Light");

    public final String stringName;

    TrapCardName(String stringName) {
        this.stringName = stringName;
    }
}
