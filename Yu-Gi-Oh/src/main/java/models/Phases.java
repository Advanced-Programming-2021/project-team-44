package models;

public enum Phases {
    DRAW("Draw Phase"),
    STANDBY("Standby Phase"),
    MAIN1("Main Phase 1"),
    BATTLE("Battle Phase"),
    MAIN2("Main PHase 2"),
    END("End Phase");

    public final String name;

    Phases(String name) {
        this.name = name;
    }
}
