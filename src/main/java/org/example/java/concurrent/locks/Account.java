package org.example.java.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    /** Locks - замена syncronized блокам и методам из многопоточности */
    private final Lock lock = new ReentrantLock();
    private int id;
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void add (int money) {
        this.money += money;
    }

    public boolean takeOff(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        }
        return false;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Account{" +
                "lock=" + lock +
                ", id=" + id +
                ", money=" + money +
                '}';
    }
}
