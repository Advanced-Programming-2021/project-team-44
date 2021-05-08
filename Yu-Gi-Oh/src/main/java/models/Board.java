package models;

import java.util.HashMap;

public class Board {
    public static int[] areasNumber = new int[]{5, 3, 1, 2, 4};
    private Player player;

    private HashMap<Integer, String> monsterAreaState;
    private HashMap<Integer, String> spellAreaState;
    private String graveyardState;
    private String fieldZoneState;

    public Board(Player player) {
        this.player = player;
        this.monsterAreaState = new HashMap<>();
        this.spellAreaState = new HashMap<>();
        this.graveyardState = "GY";
        this.fieldZoneState = "FZ";
    }

    public String printAsSelf() {
        String output = "";

        //Monsters Area
        String monstersArea = "";
        for (int number : areasNumber)


        output = fieldZoneState + "\\t\\t\\t\\t\\t\\t" + graveyardState
                + "\\t\\t" + "\\t\\t"
        ;
        return output;
    }

    public String printAsOpponent() {
        return null;
    }

    public void update() {}
}
