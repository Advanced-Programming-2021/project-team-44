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

    public Player(Account account) {
        this.account = account;
        this.deck = this.account.getActiveDeck();
        this.monsterArea = new HashMap<>();
        this.spellArea = new HashMap<>();
        ArrayList<Card> tmpList = this.deck.getMainDeckCards();
        Collections.shuffle(tmpList);
        this.deckCards = tmpList;
        this.handCards = new ArrayList<>();
        setHandCards();
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

}
