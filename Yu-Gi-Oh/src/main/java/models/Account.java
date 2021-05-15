package models;

import models.cards.Card;
import models.utils.comparators.CardSortByName;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Account {
    public static ArrayList<Account> accounts;
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coin;
    private Deck activeDeck;
    private ArrayList<Card> spareCards;
    private ArrayList<Deck> decks;

    static {
        accounts = new ArrayList<>();
    }

    public Account(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
        this.coin = 0;
        this.activeDeck = null;
        spareCards = new ArrayList<>();
        decks = new ArrayList<>();
        accounts.add(this);
    }

    //Utils
    public static boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^\\w+$");
        return pattern.matcher(username).find();
    }

    public static boolean isNicknameValid(String nickname) {
        Pattern pattern = Pattern.compile("^\\S+$");
        return pattern.matcher(nickname).find();
    }

    public static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^\\S+$");
        return pattern.matcher(password).find();
    }

    //Getters
    public static Account getAccountByUsername(String username) {
        for (Account account : accounts)
            if (account.getUsername().equals(username))
                return account;
        return null;
    }

    public static Account getAccountByNickname(String nickname) {
        for (Account account : accounts)
            if (account.getNickname().equals(nickname))
                return account;
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public int getScore() {
        return this.score;
    }

    public int getCoin() {
        return this.coin;
    }

    public Deck getActiveDeck() {
        return this.activeDeck;
    }

    public ArrayList<Deck> getOtherDecks() {
        ArrayList<Deck> otherDecks = new ArrayList<>();
        for (Deck deck : decks)
            if (deck != activeDeck)
                otherDecks.add(deck);
        return otherDecks;
    }

    public String getStringForScoreboard() {
        return this.nickname + ": " + this.score;
    }

    public Deck getDeckByName(String deckName) {
        for (Deck deck : this.decks)
            if (deck.getName().equals(deckName))
                return deck;
        return null;
    }

    public Card getCardByName(String cardName) {
        for (Card card : this.spareCards)
            if (card.getName().equals(cardName))
                return card;
        return null;
    }

    public String showSpareCards() {
        StringBuilder response = new StringBuilder();
        spareCards.sort(new CardSortByName());
        for (Card card : spareCards) {
            response.append(card.getStringForAllCardsShow()).append("\n");
        }
        response.deleteCharAt(response.length() - 1);
        return response.toString();
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void increaseScore(int addedScore) {
        this.score = this.score + addedScore;
    }

    public void increaseCoin(int addedCoins) {
        this.coin = this.coin + addedCoins;
    }

    public void decreaseCoin(int decreasedCoins) {
        this.coin = this.coin - decreasedCoins;
    }

    public void addCard(Card card) {
        spareCards.add(card);
    }

    public void removeCard(Card card) {
        spareCards.remove(card);
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void removeDeck(Deck deck) {
        spareCards.addAll(deck.getMainDeckCards());
        spareCards.addAll(deck.getSideDeckCards());
        decks.remove(deck);
    }

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }
}