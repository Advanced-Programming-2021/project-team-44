package controller.threads;

import models.Account;

public class DataSaver extends Thread{
    @Override
    public void run() {
        while (true) {
            Account.saveAccounts();
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
