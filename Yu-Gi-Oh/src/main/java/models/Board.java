package models;

import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;

import java.util.HashMap;

public class Board {
    public static int[] areasNumber = new int[]{5, 3, 1, 2, 4};
    private Player player;
    private HashMap<Integer, MonsterCard> monsterArea;
    private HashMap<Integer, MagicCard> magicArea;
    private HashMap<Integer, Card> handArea;
    private Card fieldZone;
    private HashMap<Integer, String> monsterAreaState;
    private HashMap<Integer, String> magicAreaState;
    private String graveyardState;
    private String fieldZoneState;

    public Board(Player player) {
        this.player = player;
        this.monsterArea = new HashMap<>();
        this.magicArea = new HashMap<>();
        this.handArea = new HashMap<>();
        this.fieldZone = null;
        this.monsterAreaState = new HashMap<>();
        this.magicAreaState = new HashMap<>();
        this.graveyardState = "GY";
        this.fieldZoneState = "FZ";
    }

    public String printAsSelf() {
        String output = "";
        //TODO

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

    public void update() {
    }

    //Getters and Setters
    public Player getPlayer() {
        return player;
    }

    public MonsterCard getCardFromMonsterArea(int position) {
        return monsterArea.get(position);
    }

    public void setCardInMonsterArea(MonsterCard monsterCard, int position) {
        monsterArea.put(position, monsterCard);
    }

    public MagicCard getCardFromMagicArea(int position) {
        return magicArea.get(position);
    }

    public void setCardInMagicArea(MagicCard magicCard, int position) {
        magicArea.put(position, magicCard);
    }

    public Card getCardFromHandArea(int position) {
        return handArea.get(position);
    }

    public void setCardInHandArea(Card card, int position) {
        handArea.put(position, card);
    }

    public HashMap<Integer, String> getMonsterAreaState() {
        return monsterAreaState;
    }

    public void setMonsterAreaState(HashMap<Integer, String> monsterAreaState) {
        this.monsterAreaState = monsterAreaState;
    }

    public HashMap<Integer, String> getMagicAreaState() {
        return magicAreaState;
    }

    public void setMagicAreaState(HashMap<Integer, String> magicAreaState) {
        this.magicAreaState = magicAreaState;
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

    public Card getCardFromFieldZone() {
        return fieldZone;
    }

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }
}
