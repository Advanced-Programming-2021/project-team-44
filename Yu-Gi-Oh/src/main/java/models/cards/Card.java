package models.cards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class Card {
    public static ArrayList<Card> allCards;

    static {
        allCards = new ArrayList<>();
    }

    protected String name;
    protected String description;
    protected int price;

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

    //Getters
    public String serialize() {
        String json;
        File file;
        if (this instanceof MagicCard)
            file = new File("src/main/resources/static/cards/magics/" + this.name + ".json");
        else
            file = new File("src/main/resources/static/cards/monsters/" + this.name + ".json");
        try {
            json = Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            return null;
        }
        return json;
    }

    abstract public String getStringForShow();

    abstract public HashMap<String, String> getHashMap();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (this instanceof MonsterCard && card instanceof MonsterCard)
            return ((MonsterCard) this).equals((MonsterCard) card);
        else if (this instanceof MagicCard && card instanceof MagicCard)
            return ((MagicCard) this).equals((MagicCard) card);
        return false;
    }
}