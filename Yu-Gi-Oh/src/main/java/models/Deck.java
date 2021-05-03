package models;

import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;

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

    //Setters
    public void addCardToMainDeck(Card card) {
        mainDeckCards.add(card);
    }

    public void addCardToSideDeck(Card card) {
        sideDeckCards.add(card);
    }

    public void removeCardFromMainDeck(String cardName) {
        for (Card card : mainDeckCards) {
            if (card.getName().equals(cardName)) {
                mainDeckCards.remove(card);
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

    public ArrayList<Card> getSideDeckCards() {
        return this.sideDeckCards;
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
        response.deleteCharAt(response.length()-1);
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
}
