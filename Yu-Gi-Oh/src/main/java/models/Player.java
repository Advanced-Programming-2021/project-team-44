package models;

import controller.processors.DuelMenuProcessor;
import models.cards.Card;
import models.cards.MagicCard;
import models.cards.MonsterCard;
import models.utils.Utils;

import java.util.*;

public class Player {
    private Account account;
    private Board board;
    private int lp;
    private Deck deck;
    private ArrayList<Card> mainDeckCards;
    private HashMap<Integer, MonsterCard> monsterZone;
    private HashMap<Integer, MagicCard> magicZone;
    private HashMap<Integer, Card> handZone;
    private Card fieldZone;
    private ArrayList<Card> graveyardZone;
    private boolean isCheatActivated;

    public Player(Account account) {
        this.account = account;
        this.board = new Board(this);
        this.deck = (Deck) Utils.deepClone(this.account.getActiveDeck());
        this.mainDeckCards = this.deck.getMainDeckCards();
        Collections.shuffle(this.mainDeckCards);

        this.monsterZone = new HashMap<>();
        this.monsterZone.put(1, null);
        this.monsterZone.put(2, null);
        this.monsterZone.put(3, null);
        this.monsterZone.put(4, null);
        this.monsterZone.put(5, null);

        this.magicZone = new HashMap<>();
        this.magicZone.put(1, null);
        this.magicZone.put(2, null);
        this.magicZone.put(3, null);
        this.magicZone.put(4, null);
        this.magicZone.put(5, null);

        this.handZone = new HashMap<>();
        setHandCards();

        this.fieldZone = null;
        this.graveyardZone = new ArrayList<>();
    }

    public String getCommand(String dir) {
        String consoleMessage = account.getNickname() + "@" + dir + ":" + DuelMenuProcessor.phase + "$ ";
        System.out.print(consoleMessage);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim();
        scanner.close();
        return command;
    }

    //Getters and Setters
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Board getBoard() {
        return board;
    }

    public int getLp() {
        return lp;
    }

    public void increaseLp(int amount) {
        this.lp += amount;
    }

    public boolean isCheatActivated() {
        return isCheatActivated;
    }

    public void setCheatActivated(boolean cheatActivated) {
        isCheatActivated = cheatActivated;
    }

    public int getMainDeckCount() {
        return this.mainDeckCards.size();
    }

    public boolean ownsCard(Card givenCard) {
        for (int i = 1; i < 6; i++) {
            if (monsterZone.get(i) == givenCard) return true;
            if (magicZone.get(i) == givenCard) return true;
        }
        for (int i = 1; i < 7; i++) {
            if (handZone.get(i) == givenCard) return true;
        }
        if (fieldZone == givenCard) return true;
        return false;
    }

    public String getCardState(Card givenCard) {
        assert ownsCard(givenCard);
        for (int i = 1; i < 6; i++) {
            if (monsterZone.get(i) == givenCard) return board.getMonsterZoneState(i);
            if (magicZone.get(i) == givenCard) return board.getMagicZoneState(i);
        }
        if (fieldZone == givenCard) return board.getFieldZoneState();
        return null;
    }

    ////Monster Zone
    public MonsterCard getCardFromMonsterZone(int position) {
        return monsterZone.get(position);
    }

    public void setCardInMonsterZone(MonsterCard monsterCard, int position) {
        monsterZone.put(position, monsterCard);
    }

    public boolean isMonsterZoneFull() {
        for (int i = 1; i <= 5; i++)
            if (getCardFromMonsterZone(i) == null)
                return false;
        return true;

    }

    public int getFirstFreePositionInMonsterZone() {
        for (int i = 1; i <= 5; i++) {
            if (getCardFromMonsterZone(i) == null)
                return i;
        }
        return -1;
    }

    public int howManyMonstersInTheGame() {
        int sum = 0;
        for (Map.Entry<Integer, MonsterCard> entry : monsterZone.entrySet())
            if (entry.getValue() != null)
                sum++;
        return sum;
    }

    public void destroyMonster(int position) {
        graveyardZone.add(monsterZone.get(position));
        monsterZone.put(position, null);
        board.setMonsterZoneState(position, "E");
        board.update();
    }

    public boolean ifMonsterZoneContains(MonsterCard card) {
        return monsterZone.containsValue(card);
    }

    public int getMonsterCardIndex(MonsterCard card) {
        for (int i = 1; i < 6; i++) {
            if (monsterZone.get(i) == card) return i;
        }
        return -1;
    }

    public void activateSelfSummonEffect(MonsterCard card) {
        switch (card.getName()) {
            case "Command Knight" -> {

            }
        }
    } //TODO Summon Effect

    public void activateOpponentSummonEffect(MonsterCard card) {
        switch (card.getName()) {

        }
    } //TODO Summon Effect

    public boolean isMainDeckCardEmpty() {
        return mainDeckCards.size() == 0;
    }

    ////Magic Zone
    public MagicCard getCardFromMagicZone(int position) {
        return magicZone.get(position);
    }

    public void setCardInMagicZone(MagicCard magicCard, int position) {
        magicZone.put(position, magicCard);
    }

    public boolean isMagicZoneFull() {
        for (int i = 1; i <= 5; i++)
            if (getCardFromMagicZone(i) == null)
                return false;
        return true;
    }

    public int getFirstFreePositionInMagicZone() {
        for (int i = 1; i <= 5; i++) {
            if (getCardFromMagicZone(i) == null)
                return i;
        }
        return -1;
    }

    public void destroyMagic(int position) {
        magicZone.put(position, null);
    }

    public boolean ifMagicZoneContains(MagicCard card) {
        return magicZone.containsValue(card);
    }

    public int getMagicCardIndex(MagicCard card) {
        for (int i = 1; i < 6; i++) {
            if (magicZone.get(i) == card) return i;
        }
        return -1;
    }

    ////Hand Zone
    public Card getCardFromHandZone(int position) {
        return handZone.get(position);
    }

    public void setCardInHandZone(Card card, int position) {
        handZone.put(position, card);
    }

    public void removeCardFromHandZone(Card card) {
        assert ifHandContains(card);
        for (Map.Entry<Integer, Card> entry : handZone.entrySet()) {
            if (entry.getValue() == card) {
                handZone.put(entry.getKey(), null);
                return;
            }
        }
    }

    public boolean ifHandContains(Card card) {
        return handZone.containsValue(card);
    }

    public void setHandCards() {
        for (int i = 1; i <= 6; i++) {
            if (handZone.get(i) == null) {
                handZone.put(i, mainDeckCards.get(0));
                mainDeckCards.remove(0);
            }
        }
    }

    public int getFirstFreePositionInHandZone() {
        for (int i = 1; i <= 6; i++) {
            if (getCardFromHandZone(i) == null)
                return i;
        }
        return -1;
    }

    public int getHandZoneCount() {
        int count = 0;
        for (int i = 0; i < handZone.size(); i++)
            if (handZone.get(i) != null)
                count++;
        return count;
    }

    ////Field Zone
    public Card getCardFromFieldZone() {
        return fieldZone;
    }

    public void setFieldZone(Card fieldZone) {
        this.fieldZone = fieldZone;
    }

    ////Graveyard
    public ArrayList<Card> getGraveyardZone() {
        return graveyardZone;
    }
}
