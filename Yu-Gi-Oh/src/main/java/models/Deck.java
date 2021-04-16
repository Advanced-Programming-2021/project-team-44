package models;

import models.cards.Card;

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

    public void removeCardFromMainDeck(Card card) {
        mainDeckCards.remove(card);
    }

    public void removeCardFromSideDeck(Card card) {
        sideDeckCards.remove(card);
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

    public ArrayList<Card> getMainDeckCards() {
        return this.mainDeckCards;
    }

    public ArrayList<Card> getSideDeckCards() {
        return this.sideDeckCards;
    }

    public String getStringToShow() {
        return null;
    }
}
