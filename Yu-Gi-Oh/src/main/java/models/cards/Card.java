package models.cards;

import java.util.ArrayList;
import java.util.*;

abstract public class Card {
    public static ArrayList<Card> cards;
    protected String name;
    protected String description;
    protected long price;

    static {
        cards = new ArrayList<>();
    }


    //Getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getStringForShow();

    public abstract HashMap<String, String> getHashMap();

    public static String getStringForAllCardsShow() {
        //TODO
        //Collections.sort(cards);
        return null;
    }

    public long getPrice() {return this.price;}

    public static Card getCardByName(String cardName){//TODO
        for (Card card : cards)
            if (card.getName().equals(cardName))
                return card;
        return null;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(long price){ this.price = price;}
}
