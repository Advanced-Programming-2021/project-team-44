package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}