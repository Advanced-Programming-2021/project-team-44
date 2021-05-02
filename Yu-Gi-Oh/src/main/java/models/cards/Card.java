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
    public static String getStringForAllCardsShow() {
        //TODO
        //Collections.sort(cards);
        return null;
    }

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

    abstract public String getStringForShow();

    abstract public HashMap<String, String> getHashMap();

    public int getPrice() {
        return this.price;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}