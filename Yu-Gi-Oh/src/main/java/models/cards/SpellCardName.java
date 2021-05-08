package models.cards;

public enum SpellCardName {
    MONSTER_REBORN("Monster Reborn"),
    TERRAFORMING("Terraforming"),
    POT_OF_GREED("Pot of Greed"),
    RAIGEKI("Raigeki"),
    CHANGE_OF_HEART("Change of Heart"),
    HARPIES_FEATHER_DUSTER("Harpie's Feather Duster"),
    SWORDS_OF_REVEALING_LIGHT("Swords of Revealing Light"),
    DARK_HOLE("Dark Hole"),
    SUPPLY_SQUAD("Supply Squad"),
    SPELL_ABSORPTION("Spell Absorption"),
    MESSENGER_OF_PEACE("Messenger of peace"),
    TWIN_TWISTERS("Twin Twisters"),
    MYSTICAL_SPACE_TYPHOON("Mystical space typhoon"),
    RING_OF_DEFENSE("Ring of defense"),
    YAMI("Yami"),
    FOREST("Forest"),
    CLOSED_FOREST("Closed Forest"),
    UMIIRUKA("Umiiruka"),
    SWORD_OF_DARK_DESTRUCTION("Sword of dark destruction"),
    BLACK_PENDANT("Black Pendant"),
    UNITED_WE_STAND("United We Stand"),
    MAGNUM_SHIELD("Magnum Shield"),
    ADVANCED_RITUAL_ART("Advanced Ritual Art");

    public final String stringName;

    SpellCardName(String stringName) {
        this.stringName = stringName;
    }
}
