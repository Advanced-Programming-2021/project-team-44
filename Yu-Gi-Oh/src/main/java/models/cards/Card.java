package models.cards;

import java.util.ArrayList;
import java.util.*;

abstract public class Card {
    public static ArrayList<Card> allCards;
    protected String name;
    protected String description;
    protected int price;

    static {
        allCards = new ArrayList<>();
    }


    //Getters
    abstract public String getStringForShow();

    abstract public HashMap<String, String> getHashMap();

    public static Card getCardByName(String cardName) {
        for (Card card : allCards)
            if (card.getName().equals(cardName))
                return card;
        return null;
    }

    public static String getTypeOfCardByName(String cardName) {
        String type;
        for (Card card : allCards)
            if (card.getName().equals(cardName)) {
                if (card instanceof MonsterCard) type = "monster";
                else type = "magic";
                return type;
            }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public String getStringForAllCardsShow() {
        return this.name + ":" + this.description;
    }
}