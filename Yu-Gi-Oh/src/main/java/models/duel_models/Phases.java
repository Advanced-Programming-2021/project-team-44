package models.duel_models;

public enum Phases {
    DRAW("Draw Phase"),
    STANDBY("Standby Phase"),
    MAIN1("Main Phase 1"),
    BATTLE("Battle Phase"),
    MAIN2("Main Phase 2"),
    END("End Phase");

    public final String stringName;

    Phases(String stringName) {
        this.stringName = stringName;
    }
}
