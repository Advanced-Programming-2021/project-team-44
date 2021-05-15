package models;

import models.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
    private Account account;
    private int lp;
    private Deck deck;
    private HashMap<Integer, Card> monsterArea;
    private HashMap<Integer, Card> spellArea;
    private ArrayList<Card> handCards;
    private ArrayList<Card> deckCards;
    private ArrayList<Card> graveyardCards;
    private Card fieldZone;
    private boolean isCheatActivated;

    public Player(Account account) {
        this.setAccount(account);
        this.setDeck(account.getActiveDeck());
        this.monsterArea = new HashMap<>();
        this.spellArea = new HashMap<>();
        ArrayList<Card> tmpList = this.deck.getMainDeckCards();
        Collections.shuffle(tmpList);
        this.deckCards = tmpList;
        this.handCards = new ArrayList<>();
        //setHandCards();
        this.graveyardCards = new ArrayList<>();
        this.fieldZone = null;
    }

    public String getCommand(String dir) {
        System.out.print(account.getNickname() + "@" + dir + ":$ ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        scanner.close();
        return command;
    }

    private void setHandCards() {
        for (int i = 0; i < 5; i++) {
            handCards.add(deckCards.get(0));
            deckCards.remove(0);
        }
    }

    //Getters and Setters
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public HashMap<Integer, Card> getMonsterArea() {
        return monsterArea;
    }

    public void setMonsterArea(HashMap<Integer, Card> monsterArea) {
        this.monsterArea = monsterArea;
    }

    public HashMap<Integer, Card> getSpellArea() {
        return spellArea;
    }

    public void setSpellArea(HashMap<Integer, Card> spellArea) {
        this.spellArea = spellArea;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(ArrayList<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public ArrayList<Card> getGraveyardCards() {
        return graveyardCards;
    }

    public void setGraveyardCards(ArrayList<Card> graveyardCards) {
        this.graveyardCards = graveyardCards;
    }

    public Card getFieldZone() {
        return fieldZone;
    }

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }

    public boolean isCheatActivated() {
        return isCheatActivated;
    }

    public void setCheatActivated(boolean cheatActivated) {
        isCheatActivated = cheatActivated;
    }
}
