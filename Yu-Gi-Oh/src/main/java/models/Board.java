package models;

import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Player player;
    private HashMap<Integer, String> monsterZoneState;
    private HashMap<Integer, String> magicZoneState;
    private String graveyardState;
    private String fieldZoneState;

    public Board(Player player) {
        this.player = player;

        this.monsterZoneState = new HashMap<>();
        this.magicZoneState = new HashMap<>();
        this.graveyardState = "GY";
        this.fieldZoneState = "FZ";
    }

    public String printAsSelf() {
        String output = "";
        //TODO

        return output;
    }

    public String printAsOpponent() {
        //TODO
        return null;
    }

    public void update() {
    }

    //Getters and Setters
    public Player getPlayer() {
        return player;
    }

    public String getMonsterZoneState(int position) {
        return monsterZoneState.get(position);
    }

    public void setMonsterZoneState(int position, String state) {
        this.monsterZoneState.put(position, state);
    }

    public String getMagicZoneState(int position) {
        return magicZoneState.get(position);
    }

    public void setMagicZoneState(int position, String state) {
        this.magicZoneState.put(position, state);
    }

    public String getFieldZoneState() {
        return fieldZoneState;
    }

    public void setFieldZoneState(String fieldZoneState) {
        this.fieldZoneState = fieldZoneState;
    }

    ////Graveyard
    public String getGraveyardState() {
        return graveyardState;
    }

    public void setGraveyardState(String graveyardState) {
        this.graveyardState = graveyardState;
    }
}
