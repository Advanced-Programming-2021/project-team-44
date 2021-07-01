package models.utils.comparators;

import models.Account;

import java.util.Comparator;

public class AccountSortByNickname implements Comparator<Account> {
    @Override
    public int compare(Account account1, Account account2) {
        return account1.getNickname().compareTo(account2.getNickname());
    }
}