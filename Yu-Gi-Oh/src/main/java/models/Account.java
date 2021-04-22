package models;

import models.cards.Card;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Account {
    public static ArrayList<Account> accounts;
    private String username;
    private String password;
    private String nickname;
    private long score;
    private long coin;
    private Deck activeDeck;
    private ArrayList<Card> cards;
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
        cards = new ArrayList<>();
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

    public String getPassword() { return this.password;}

    public long getScore() {
        return this.score;
    }

    public long getCoin() {
        return this.coin;
    }

    public Deck getActiveDeck() {
        return this.activeDeck;
    }

    public String getStringForScoreboard() {
        return null;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void increaseScore(int addedScore) {
        this.score = this.score + (long) addedScore;
    }

    public void increaseCoin(long addedCoins) {
        this.coin = this.coin + addedCoins;
    }

    public void decreaseCoin(long decreasedCoins) {this.coin = this.coin - decreasedCoins;}

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void removeDeck(Deck deck) {
        cards.addAll(deck.getMainDeckCards());
        cards.addAll(deck.getSideDeckCards());
        decks.remove(deck);
    }
}
