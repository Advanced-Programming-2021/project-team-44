package models;

import java.util.HashMap;

public class Board {
    private Player player;
    private HashMap<Integer, String> monsterZoneState;
    private HashMap<Integer, String> magicZoneState;
    private String graveyardState;
    private String fieldZoneState;
    private String deckCountState;

    public Board(Player player) {
        this.player = player;
        this.monsterZoneState = new HashMap<>();
        this.magicZoneState = new HashMap<>();
        this.graveyardState = "0";
        this.fieldZoneState = "E";
        this.deckCountState = String.valueOf(player.getMainDeckCount());
        this.update();
    }

    public String getStringAsSelf() {
        this.update();
        StringBuilder output = new StringBuilder();
        //Construction
        {
            output.append("\n");
            output.append(fieldZoneState).append("\t\t\t\t\t\t").append(graveyardState).append("\n");
            output.append("\t")
                    .append(monsterZoneState.get(4)).append("\t")
                    .append(monsterZoneState.get(2)).append("\t")
                    .append(monsterZoneState.get(1)).append("\t")
                    .append(monsterZoneState.get(3)).append("\t")
                    .append(monsterZoneState.get(5)).append("\t")
                    .append("\t").append("\n");
            output.append("\t")
                    .append(magicZoneState.get(4)).append("\t")
                    .append(magicZoneState.get(2)).append("\t")
                    .append(magicZoneState.get(1)).append("\t")
                    .append(magicZoneState.get(3)).append("\t")
                    .append(magicZoneState.get(5)).append("\t")
                    .append("\t").append("\n");
            output.append("\t\t\t\t\t\t").append(deckCountState).append("\n");
            for (int i = 0; i < player.getHandZoneCount(); i++)
                output.append("c").append("\t");
            output.append("\n");
            output.append(player.getAccount().getNickname()).append(":").append(player.getLp());
        }
        return output.toString();
    }

    public String getStringAsOpponent() {
        this.update();
        StringBuilder output = new StringBuilder();
        //Construction
        {
            output.append(player.getAccount().getNickname()).append(":").append(player.getLp()).append("\n");
            for (int i = 0; i < player.getHandZoneCount(); i++)
                output.append("\t").append("c");
            output.append("\n");
            output.append(deckCountState).append("\n");
            output.append("\t")
                    .append(magicZoneState.get(5)).append("\t")
                    .append(magicZoneState.get(3)).append("\t")
                    .append(magicZoneState.get(1)).append("\t")
                    .append(magicZoneState.get(2)).append("\t")
                    .append(magicZoneState.get(4)).append("\t")
                    .append("\t").append("\n");
            output.append("\t")
                    .append(monsterZoneState.get(5)).append("\t")
                    .append(monsterZoneState.get(3)).append("\t")
                    .append(monsterZoneState.get(1)).append("\t")
                    .append(monsterZoneState.get(2)).append("\t")
                    .append(monsterZoneState.get(4)).append("\t")
                    .append("\t").append("\n");
            output.append(graveyardState).append("\t\t\t\t\t\t").append(fieldZoneState).append("\n");
            output.append("\n");
        }
        return output.toString();
    }

    public void update() {
        //Field Zone
        if (player.getCardFromFieldZone() == null) fieldZoneState = "E";
        else fieldZoneState = "O";

        //Graveyard
        graveyardState = String.valueOf(player.getGraveyardZone().size());

        //Deck Count
        deckCountState = String.valueOf(player.getMainDeckCount());
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

    public String getGraveyardState() {
        return graveyardState;
    }

    public void setGraveyardState(String graveyardState) {
        this.graveyardState = graveyardState;
    }
}
