package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class AccountTest {
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
}