package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import models.utils.comparators.CardSortByName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Account {
    public static final ArrayList<Account> accounts;

    static {
        accounts = new ArrayList<>();
    }

    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coin;
    private Deck activeDeck;
    private ArrayList<Card> spareCards;
    private ArrayList<Deck> decks;
    private String profilePicture;

    public Account() {
        this.username = null;
        this.password = null;
        this.nickname = null;
        this.activeDeck = null;
        this.spareCards = new ArrayList<>();
        this.decks = new ArrayList<>();
    }

    public Account(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
        this.coin = 50000;
        this.activeDeck = null;
        this.spareCards = new ArrayList<>();
        this.decks = new ArrayList<>();
        accounts.add(this);
    }

    public static synchronized String initializeAccounts() {
        if (accounts.size() == 0) {
            synchronized (accounts) {
                File accountsDirectory = new File("src/main/resources/static/accounts");
                File[] accountsFiles = accountsDirectory.listFiles();
                if (accountsFiles == null)
                    return "Accounts JSON files missing!";
                for (File file : accountsFiles) {
                    String accountJson;
                    try {
                        accountJson = Files.readString(Paths.get(file.getPath()));
                    } catch (IOException e) {
                        return "JSON files can't be accessed!";
                    }
                    accounts.add(Account.deserialize(accountJson));
                }
                return "Accounts loaded successfully";
            }
        }
        return "";
    }

    public static String saveAccounts() {
        synchronized (accounts) {
            for (Account account : accounts) {
                String accountFilePath = "src/main/resources/static/accounts/" + account.getUsername() + ".json";
                File accountFile = new File(accountFilePath);
                try {
                    FileWriter writer = new FileWriter(accountFile.getPath(), false);
                    String jsonData = account.serialize();
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse accounts JSON files";
                }
            }
            return "Accounts data saved successfully";
        }
    }

    public static Account deserialize(String accountSerialized) {
        AccountDeepSerialized accountDeepSerialized = (new Gson()).fromJson(accountSerialized, AccountDeepSerialized.class);
        Deck activeDeck = Deck.deserialize(accountDeepSerialized.activeDeckSerialized);
        Type collectionType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> spareCardsDeepSerialized = (new Gson()).fromJson(accountDeepSerialized.spareCardsSerialized, collectionType);
        ArrayList<String> decksDeepSerialized = (new Gson()).fromJson(accountDeepSerialized.decksSerialized, collectionType);
        ArrayList<Card> spareCards = new ArrayList<>();
        ArrayList<Deck> decks = new ArrayList<>();
        for (String cardSerialized : spareCardsDeepSerialized) {
            try {
                spareCards.add((new Gson()).fromJson(cardSerialized, MonsterCard.class));
            } catch (Exception e) {
                spareCards.add((new Gson()).fromJson(cardSerialized, MagicCard.class));
            }
        }
        for (String deckSerialized : decksDeepSerialized)
            decks.add(Deck.deserialize(deckSerialized));
        Account output = new Account();
        output.setUsername(accountDeepSerialized.username);
        output.setPassword(accountDeepSerialized.password);
        output.setNickname(accountDeepSerialized.nickname);
        output.setScore(accountDeepSerialized.score);
        output.setCoin(accountDeepSerialized.coin);
        output.setActiveDeck(activeDeck);
        output.setSpareCards(spareCards);
        output.setDecks(decks);
        output.setProfilePicture(accountDeepSerialized.profilePicture);
        return output;
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

    public String serialize() {
        String activeDeckSerialized;
        if (activeDeck != null) activeDeckSerialized = activeDeck.serialize();
        else activeDeckSerialized = null;
        ArrayList<String> spareCardsDeepSerialized = new ArrayList<>();
        ArrayList<String> decksDeepSerialized = new ArrayList<>();
        for (Card card : this.spareCards)
            spareCardsDeepSerialized.add(card.serialize());
        for (Deck deck : this.decks)
            decksDeepSerialized.add(deck.serialize());
        String spareCardsSerialized = (new Gson()).toJson(spareCardsDeepSerialized);
        String decksSerialized = (new Gson()).toJson(decksDeepSerialized);
        AccountDeepSerialized accountDeepSerialized = new AccountDeepSerialized(this.username, this.password, this.nickname, this.score, this.coin, activeDeckSerialized, spareCardsSerialized, decksSerialized, this.profilePicture);
        return (new Gson()).toJson(accountDeepSerialized);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //Setters
    public String getProfilePicture() {
        if (this.profilePicture == null) this.profilePicture = "placeholder.png";
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpareCards(ArrayList<Card> spareCards) {
        this.spareCards = spareCards;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoin() {
        return this.coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public Deck getActiveDeck() {
        return this.activeDeck;
    }

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
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
}

class AccountDeepSerialized {
    protected String username;
    protected String password;
    protected String nickname;
    protected int score;
    protected int coin;
    protected String activeDeckSerialized;
    protected String spareCardsSerialized;
    protected String decksSerialized;
    protected String profilePicture;

    public AccountDeepSerialized(String username, String password, String nickname, int score, int coin, String activeDeckSerialized, String spareCardsSerialized, String decksSerialized, String profilePicture) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = score;
        this.coin = coin;
        this.activeDeckSerialized = activeDeckSerialized;
        this.spareCardsSerialized = spareCardsSerialized;
        this.decksSerialized = decksSerialized;
        this.profilePicture = profilePicture;
    }
}