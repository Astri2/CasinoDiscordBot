package me.astri.casino.casino;

public class BankAccount {
    private int balance;

    public BankAccount() {
        this.balance = 500;
    }

    public boolean withdraw(int value) {
        if(value <= 0) throw new IllegalArgumentException("Value must be positive");
        if(value > balance)
            return false;
        this.balance -= value;
        return true;
    }

    public int getBalance() {
        return this.balance;
    }

    public void reset() {
        this.balance=0;
    }

    public void set(int value) {
        this.balance = value;
    }

    public void add(int value) {
        if(value <= 0) throw new IllegalArgumentException("Value must be positive");
        balance+=value;
    }
}
