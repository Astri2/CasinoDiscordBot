package me.astri.casino.wheel;

public class PlayerBet extends Bet {
    private final long amount;

    public PlayerBet(int amount, Bet bet) {
        super(bet);
        this.amount = amount;
    }

    public long getAmount() {
        return this.amount;
    }
}
