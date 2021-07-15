package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> mainDeckCards;
    private ArrayList<Card> sideDeckCards;

    public Deck(String name) {
        this.name = name;
        mainDeckCards = new ArrayList<>();
        sideDeckCards = new ArrayList<>();
    }

    public static Deck deserialize(String deckDeepSerialized) {
        DeckDeepSerialized deck = (new Gson()).fromJson(deckDeepSerialized, DeckDeepSerialized.class);
        if (deck == null) return null;
        Type collectionType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> mainDeckCardsSerialized = (new Gson()).fromJson(deck.mainDeckCardsSerialized, collectionType);
        ArrayList<String> sideDeckCardsSerialized = (new Gson()).fromJson(deck.sideDeckCardsSerialized, collectionType);
        ArrayList<Card> mainDeckCards = new ArrayList<>();
        ArrayList<Card> sideDeckCards = new ArrayList<>();
        for (String cardSerialized : mainDeckCardsSerialized) {
            try {
                mainDeckCards.add((new Gson()).fromJson(cardSerialized, MonsterCard.class));
            } catch (Exception e) {
                mainDeckCards.add((new Gson()).fromJson(cardSerialized, MagicCard.class));
            }
        }
        for (String cardSerialized : sideDeckCardsSerialized) {
            try {
                sideDeckCards.add((new Gson()).fromJson(cardSerialized, MonsterCard.class));
            } catch (Exception e) {
                sideDeckCards.add((new Gson()).fromJson(cardSerialized, MagicCard.class));
            }
        }
        Deck output = new Deck(deck.name);
        output.setMainDeckCards(mainDeckCards);
        output.setSideDeckCards(sideDeckCards);
        return output;
    }

    public String serialize() {
        ArrayList<String> mainDeckCardsSerialized = new ArrayList<>();
        ArrayList<String> sideDeckCardsSerialized = new ArrayList<>();
        for (Card card : this.mainDeckCards)
            mainDeckCardsSerialized.add(card.serialize());
        for (Card card : this.sideDeckCards)
            sideDeckCardsSerialized.add(card.serialize());
        String mainDeckSerialized = (new Gson()).toJson(mainDeckCardsSerialized);
        String sideDeckSerialized = (new Gson()).toJson(sideDeckCardsSerialized);
        DeckDeepSerialized deckDeepSerialized = new DeckDeepSerialized(this.name, mainDeckSerialized, sideDeckSerialized);
        return (new Gson()).toJson(deckDeepSerialized);
    }

    //Setters
    public void addCardToMainDeck(Card card) {
        mainDeckCards.add(card);
    }

    public void addCardToSideDeck(Card card) {
        sideDeckCards.add(card);
    }

    public void removeCardFromMainDeck(String cardName) {
        for (Card card : this.mainDeckCards) {
            if (card.getName().equals(cardName)) {
                this.mainDeckCards.remove(card);
                return;
            }
        }
    }

    public void removeCardFromSideDeck(String cardName) {
        for (Card card : sideDeckCards) {
            if (card.getName().equals(cardName)) {
                sideDeckCards.remove(card);
                return;
            }
        }
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public boolean isMainDeckFull() {
        return mainDeckCards.size() == 60;
    }

    public boolean isSideDeckFull() {
        return sideDeckCards.size() == 15;
    }

    public boolean isCardExistedInSideDeck(String cardName) {
        for (Card sideDeckCard : sideDeckCards) {
            if (sideDeckCard.getName().equals(cardName))
                return true;
        }
        return false;
    }

    public boolean isCardExistedInMainDeck(String cardName) {
        for (Card mainDeckCard : mainDeckCards) {
            if (mainDeckCard.getName().equals(cardName))
                return true;
        }
        return false;
    }

    public int mainDeckCounter() {
        return this.mainDeckCards.size();
    }

    public int sideDeckCounter() {
        return this.sideDeckCards.size();
    }

    public boolean isDeckValid() {
        return this.mainDeckCards.size() >= 45 && this.mainDeckCards.size() <= 60 && this.sideDeckCards.size() <= 15;
    }

    public ArrayList<Card> getMainDeckCards() {
        return this.mainDeckCards;
    }

    public void setMainDeckCards(ArrayList<Card> mainDeckCards) {
        this.mainDeckCards = mainDeckCards;
    }

    public ArrayList<Card> getSideDeckCards() {
        return this.sideDeckCards;
    }

    public void setSideDeckCards(ArrayList<Card> sideDeckCards) {
        this.sideDeckCards = sideDeckCards;
    }

    public String showDeck(String mainOrSide) {
        StringBuilder response = new StringBuilder();
        response.append("Deck: ").append(this.name).append("\n");
        response.append(mainOrSide).append(" deck:").append("\n");
        response.append("Monsters:").append("\n");
        switch (mainOrSide) {
            case "Main" -> {
                for (Card card : mainDeckCards)
                    if (card instanceof MonsterCard)
                        response.append(card.getStringForAllCardsShow()).append("\n");
            }
            case "Side" -> {
                for (Card card : sideDeckCards)
                    if (card instanceof MonsterCard)
                        response.append(card.getStringForAllCardsShow()).append("\n");
            }
        }
        response.append("Spell and Traps:").append("\n");
        switch (mainOrSide) {
            case "Main" -> {
                for (Card card : mainDeckCards)
                    if (card instanceof MagicCard)
                        response.append(card.getStringForAllCardsShow()).append("\n");
            }
            case "Side" -> {
                for (Card card : sideDeckCards)
                    if (card instanceof MagicCard)
                        response.append(card.getStringForAllCardsShow()).append("\n");
            }
        }
        response.deleteCharAt(response.length() - 1);
        return response.toString();
    }

    public String getStringForShowAllDecks() {
        StringBuilder response = new StringBuilder();
        response.append(this.name).append(": ");
        response.append("main deck ").append(mainDeckCards.size()).append(",");
        response.append("side deck ").append(sideDeckCards.size()).append(",");
        String validString;
        if (isDeckValid()) validString = "valid";
        else validString = "invalid";
        response.append(validString);
        return response.toString();
    }

    public boolean ifMaxOfCardIsReached(String cardName) {
        int flag = 0;
        for (Card card : mainDeckCards)
            if (card.getName().equals(cardName))
                flag++;

        for (Card card : sideDeckCards)
            if (card.getName().equals(cardName))
                flag++;
        return flag >= 3;
    }

    @Override
    public Object clone() {
        Deck dummy = new Deck(this.name);
        for (Card card : this.mainDeckCards)
            dummy.addCardToMainDeck((Card) card.clone());
        for (Card card : this.sideDeckCards)
            dummy.addCardToSideDeck((Card) card.clone());
        return dummy;
    }
}

class DeckDeepSerialized {
    protected String name;
    protected String mainDeckCardsSerialized;
    protected String sideDeckCardsSerialized;

    public DeckDeepSerialized(String name, String mainDeckCardsSerialized, String sideDeckCardsSerialized) {
        this.name = name;
        this.mainDeckCardsSerialized = mainDeckCardsSerialized;
        this.sideDeckCardsSerialized = sideDeckCardsSerialized;
    }
}
