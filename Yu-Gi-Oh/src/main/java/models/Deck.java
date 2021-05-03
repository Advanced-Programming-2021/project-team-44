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

    public boolean isCardExistedInSideDeck(String cardName){
        for (Card sideDeckCard: sideDeckCards) {
            if(sideDeckCard.getName().equals(cardName))
            return true;
        }
        return false;
    }

    public boolean isCardExistedInMainDeck(String cardName){
        for (Card mainDeckCard: mainDeckCards) {
            if(mainDeckCard.getName().equals(cardName))
            return true;
        }
        return false;
    }

    public int mainDeckCounter(){
        return this.mainDeckCards.size();
    }

    public int sideDeckCounter(){
        return this.sideDeckCards.size();
    }

    public boolean isDeckValid(){
        return this.mainDeckCards.size() >= 45 && this.mainDeckCards.size() <= 60 && this.sideDeckCards.size() <= 15;
    }

    public ArrayList<Card> getMainDeckCards() {
        return this.mainDeckCards;
    }

    public ArrayList<Card> getSideDeckCards() {
        return this.sideDeckCards;
    }

    public String showDeck() {
        String response;
        String validOrInvalid;
        if(this.isDeckValid()){
            validOrInvalid = "valid";
        }
        else{
            validOrInvalid = "invalid";
        }
        response = this.getName() + ": main deck "+ this.mainDeckCounter() + ", side deck " +
                this.sideDeckCounter() + ", " + validOrInvalid + "\n";
        return response;
    }

    public String showDeckCards(boolean isSide){
        String response;
        response = "Deck: " + this.getName() + "\n";
        if(isSide) {
            response += "Side deck: \n Monsters: \n";
            //TODO
        }
        else {
            response += "Main deck: \n Monsters: \n";
        }
        return response;
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
