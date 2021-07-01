package models.utils.comparators;

import models.Account;

import java.util.Comparator;

public class AccountSortByScore implements Comparator<Account> {
    @Override
    public int compare(Account account1, Account account2) {
        return account2.getScore() - account1.getScore();
    }
}
