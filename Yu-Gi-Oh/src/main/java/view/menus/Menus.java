package view.menus;

public enum Menus {
    LOGIN("Login Menu"),
    MAIN("Main Menu"),
    DUEL("Duel Menu"),
    PLAYER_DUEL("Player Duel Menu"),
    AI_DUEL("AI Duel Menu"),
    DECK("Deck Menu"),
    SCOREBOARD("Scoreboard Menu"),
    PROFILE("Profile Menu"),
    SHOP("Shop Menu"),
    IMPORTEXPORT("Import/Export Menu");

    public final String toBePrintedName;

    Menus(String name) {
        this.toBePrintedName = name;
    }
}
