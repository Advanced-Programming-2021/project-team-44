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

    //Getters and Setters
    public static int[] getAreasNumber() {
        return areasNumber;
    }

    public static void setAreasNumber(int[] areasNumber) {
        Board.areasNumber = areasNumber;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HashMap<Integer, String> getMonsterAreaState() {
        return monsterAreaState;
    }

    public void setMonsterAreaState(HashMap<Integer, String> monsterAreaState) {
        this.monsterAreaState = monsterAreaState;
    }

    public HashMap<Integer, String> getSpellAreaState() {
        return spellAreaState;
    }

    public void setSpellAreaState(HashMap<Integer, String> spellAreaState) {
        this.spellAreaState = spellAreaState;
    }

    public String getGraveyardState() {
        return graveyardState;
    }

    public void setGraveyardState(String graveyardState) {
        this.graveyardState = graveyardState;
    }

    public String getFieldZoneState() {
        return fieldZoneState;
    }

    public void setFieldZoneState(String fieldZoneState) {
        this.fieldZoneState = fieldZoneState;
    }
}
