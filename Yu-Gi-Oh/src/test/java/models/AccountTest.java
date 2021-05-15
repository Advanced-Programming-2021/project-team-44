package models;

import models.cards.Card;
import models.cards.MonsterCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AccountTest {
    //Utils
    @Test
    void isUsernameValidTest() {
        String test1 = "matadysa";
        Assertions.assertTrue(Account.isUsernameValid(test1));

        String test2 = "mata dysa";
        Assertions.assertFalse(Account.isUsernameValid(test2));

        String test3 = "   ";
        Assertions.assertFalse(Account.isUsernameValid(test3));

        String test4 = "";
        Assertions.assertFalse(Account.isUsernameValid(test4));

        String test5 = "mata%^dysa";
        Assertions.assertFalse(Account.isUsernameValid(test5));

        String test6 = "ma12tadysa4";
        Assertions.assertTrue(Account.isUsernameValid(test6));

        String test7 = "ma  t 78 d 8 ysa";
        Assertions.assertFalse(Account.isUsernameValid(test7));
        isNicknameValidTest();
    }

    @Test
    void isNicknameValidTest() {
        String test1 = "matadysa";
        Assertions.assertTrue(Account.isNicknameValid(test1));

        String test2 = "mata dysa";
        Assertions.assertFalse(Account.isNicknameValid(test2));

        String test3 = "   ";
        Assertions.assertFalse(Account.isNicknameValid(test3));

        String test4 = "";
        Assertions.assertFalse(Account.isNicknameValid(test4));

        String test5 = "mata%^dysa";
        Assertions.assertTrue(Account.isNicknameValid(test5));

        String test6 = "ma12tadysa4";
        Assertions.assertTrue(Account.isNicknameValid(test6));

        String test7 = "ma  t 78 d 8 ysa";
        Assertions.assertFalse(Account.isNicknameValid(test7));
    }

    @Test
    void isPasswordValidTest() {
        String test1 = "matadysa";
        Assertions.assertTrue(Account.isPasswordValid(test1));

        String test2 = "mata dysa";
        Assertions.assertFalse(Account.isPasswordValid(test2));

        String test3 = "   ";
        Assertions.assertFalse(Account.isPasswordValid(test3));

        String test4 = "";
        Assertions.assertFalse(Account.isPasswordValid(test4));

        String test5 = "mata%^dysa";
        Assertions.assertTrue(Account.isPasswordValid(test5));

        String test6 = "ma12tadysa4";
        Assertions.assertTrue(Account.isPasswordValid(test6));

        String test7 = "ma  t 78 d 8 ysa";
        Assertions.assertFalse(Account.isPasswordValid(test7));
    }

    //Getters
    @Test
    void getAccountByUsernameTest(){
        Account test1 = new Account("matinKing","12345","matadysa");
        Assertions.assertEquals(test1,Account.getAccountByUsername("matinKing"));

        Account test2 = new Account("Otamendi","12345","Elcapitano");
        Assertions.assertEquals(test2, Account.getAccountByUsername("Otamendi"));
        Assertions.assertNotEquals(test2, Account.getAccountByUsername("matinKing"));

        Assertions.assertNull(Account.getAccountByUsername("null"));
    }

    @Test
    void getAccountByNicknameTest(){
        Account test1 = new Account("matinKing2","12345","matadysa2");
        Assertions.assertEquals(test1,Account.getAccountByNickname("matadysa2"));

        Account test2 = new Account("Otamendi2","12345","Elcapitano2");
        Assertions.assertEquals(test2, Account.getAccountByNickname("Elcapitano2"));
        Assertions.assertNotEquals(test2, Account.getAccountByNickname("matadysa2"));

        Assertions.assertNull(Account.getAccountByNickname("null"));
    }

    @Test
    void getUsernameTest(){
        Account test1 = new Account("matinKing3","12345","matadysa3");
        Assertions.assertEquals(test1.getUsername(), "matinKing3");
    }

    @Test
    void getNicknameTest(){
        Account test1 = new Account("matinKing3","12345","matadysa3");
        Assertions.assertEquals(test1.getNickname(), "matadysa3");
    }

    @Test
    void getPasswordTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        Assertions.assertEquals(test1.getPassword(), "karai2465");
    }

    @Test
    void getScoreTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        Assertions.assertEquals(test1.getScore(), 0);
    }

    @Test
    void getCoinTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        Assertions.assertEquals(test1.getCoin(), 0);
    }

    @Test
    void getStringForScoreboardTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        test1.increaseScore(300);
        Assertions.assertEquals(test1.getStringForScoreboard(), "karai1: 300");
    }

    @Test
    void getDeckByNameTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        Deck deck1 = new Deck("deck 1");
        Deck deck2 = new Deck("deck 2");
        test1.addDeck(deck1);
        test1.addDeck(deck2);
        Assertions.assertEquals(test1.getDeckByName("deck 1"), deck1);
        Assertions.assertEquals(test1.getDeckByName("deck 2"), deck2);
        Assertions.assertNull(test1.getDeckByName("deck 3"));
    }

    @Test
    void getCardByNameTest(){

    }

    @Test
    void showSpareCardsTest(){
        Account test1 = new Account("karaii", "karai2465", "karai13");
        Card monsterCard = new MonsterCard();
        monsterCard.setDescription("second player's ping will rise to 2000 or more");
        monsterCard.setName("Jahromi The Great");
        test1.addCard(monsterCard);
        Assertions.assertEquals(test1.showSpareCards(), "Jahromi The Great:second player's ping will rise to 2000 or more");
    }

    @Test
    void getOtherDecksTest(){
        Account test1 = new Account("karaii", "karai2465", "karai13");
        Deck deck1 = new Deck("active");
        Deck deck2 = new Deck("My Best");
        Deck deck3 = new Deck("Trash");
        test1.addDeck(deck1);
        test1.addDeck(deck2);
        test1.addDeck(deck3);
        test1.setActiveDeck(deck1);
        ArrayList<Deck> other = new ArrayList<>();
        other.add(deck2);
        other.add(deck3);
        Assertions.assertEquals(test1.getOtherDecks(), other);
    }

    //Setters
    @Test
    void setPasswordTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        test1.setPassword("1234");
        Assertions.assertEquals(test1.getPassword(), "1234");
        Assertions.assertNotEquals(test1.getPassword(), "karai2465");
    }

    @Test
    void setNicknameTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        test1.setNickname("karaiKing");
        Assertions.assertNotEquals(test1.getNickname(), "karai1");
        Assertions.assertEquals(test1.getNickname(), "karaiKing");
    }

    @Test
    void increaseScoreTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        test1.increaseScore(2000);
        Assertions.assertEquals(test1.getScore(), 2000);
    }

    @Test
    void increaseCoinTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        test1.increaseCoin(205);
        Assertions.assertEquals(test1.getCoin(), 205);
    }

    @Test
    void decreaseCoinTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        test1.increaseCoin(1000);
        test1.decreaseCoin(200);
        Assertions.assertEquals(test1.getCoin(), 800);
    }

    @Test
    void setActiveDeckTest(){
        Account test1 = new Account("karai", "karai2465", "karai1");
        Deck deck = new Deck("abas");
        test1.setActiveDeck(deck);
        Assertions.assertEquals(test1.getActiveDeck().getName(), deck.getName());
    }

    @Test
    void removeDeckTest(){
        Account test1 = new Account("karaii", "karai2465", "karai13");
        Deck deck = new Deck("Best");
        test1.addDeck(deck);
        Assertions.assertEquals(test1.getDeckByName("Best"), deck);
        test1.removeDeck(deck);
        Assertions.assertNull(test1.getDeckByName("Best"));
    }

    @Test
    void addOrRemoveCardTest(){
        Account test1 = new Account("karaii", "karai2465", "karai13");
        Card monsterCard = new MonsterCard();
        monsterCard.setName("Jahromi The Great");
        test1.addCard(monsterCard);
        Assertions.assertEquals(test1.getCardByName("Jahromi The Great"), monsterCard);
        Assertions.assertNull(test1.getCardByName("Rostam"));
        test1.removeCard(monsterCard);
        Assertions.assertNull(test1.getCardByName("Jahromi The Great"));
    }


}