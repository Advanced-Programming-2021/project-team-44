package models.cards;

import java.util.ArrayList;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String cardNameFilter(String name) {
        Pattern pattern = Pattern.compile("([^\\\\/:*?\"<>|])");
        Matcher matcher = pattern.matcher(name);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find())
            stringBuilder.append(matcher.group(1));
        return stringBuilder.toString();
    }

    public static String descriptionFilter(String description) {
        Pattern pattern = Pattern.compile("^\"(.*)\"$");
        Matcher matcher = pattern.matcher(description);
        if (matcher.find())
            description = matcher.group(1);

        description = description.replaceAll("\"", "\\\\\\\"");

        return description;
    }
    public void setName(String name){ this.name = name;}

    public void setDescription(String description){ this.description = description;}

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

    @Override
    public Object clone() {
        if (this instanceof MonsterCard) return ((MonsterCard) this).clone();
        else if (this instanceof MagicCard) return ((MagicCard) this).clone();
        else return null;
    }

    public boolean equals(Card card) {
        if (this == card) return true;
        if (this instanceof MonsterCard && card instanceof MonsterCard) return ((MonsterCard) this).equals((MonsterCard) card);
        else if (this instanceof MagicCard && card instanceof MagicCard) return ((MagicCard) this).equals((MagicCard) card);
        return false;
    }
}