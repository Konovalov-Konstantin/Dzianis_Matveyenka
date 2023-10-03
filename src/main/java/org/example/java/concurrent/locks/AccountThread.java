package org.example.java.concurrent.locks;

public class AccountThread extends Thread {

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            lockAccounts();     // метод для блокирования аккаунтов
            try{
                if (accountFrom.takeOff(10)) {
                    accountTo.add(10);
                }
            }   finally {
                accountFrom.getLock().unlock();
                accountTo.getLock().unlock();
            }
        }
    }

    private void lockAccounts() {
        while (true) {  // пока не удалось захватить мониторы у обоих аккаунтов, перевод денег не выполнится
            boolean fromLockResult = accountFrom.getLock().tryLock();
            boolean toLockResult = accountTo.getLock().tryLock();
            if (fromLockResult && toLockResult) {   // если оба монитора захвачены - выходим из цикла
                break;
            }
            if (fromLockResult) {
                accountFrom.getLock().unlock();
            }
            if (toLockResult) {
                accountTo.getLock().unlock();
            }
        }
    }
}
