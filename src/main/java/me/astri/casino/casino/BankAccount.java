package me.astri.casino.casino;

public class BankAccount {
    private long balance;

    public BankAccount() {
        this.balance = 500;
    }

    public void withdraw(long value) {
        if(value <= 0) throw new IllegalArgumentException("Value must be positive");
        if(value > balance)
            throw new IllegalArgumentException("You don't have enough $");
        this.balance -= value;
    }

    public long getBalance() {
        return this.balance;
    }

    public void reset() {
        this.balance=0;
    }

    public void set(long value) {
        this.balance = value;
    }

    public void add(long value) {
        if(value <= 0) throw new IllegalArgumentException("Value must be positive");
        balance+=value;
    }
}
