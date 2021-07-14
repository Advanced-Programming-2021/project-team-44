package controller.threads;

import models.Account;

public class DataSaver extends Thread{
    public DataSaver() {
        super();
        this.setDaemon(true);
        start();
    }

    @Override
    public synchronized void run() {
        while (true) {
            Account.saveAccounts();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
