package me.astri.casino.wheel;

import me.astri.casino.casino.Player;

public class PlayerBet {
    private final int amount;
    private final Bet bet;

    public PlayerBet(int amount, String betType, String betValue) {
        this.amount = amount;
        String betName = "wheel.bet" + "." + betType + "." + betValue;
        this.bet = Bet.getBet(betName);
    }

    public PlayerBet(int amount, Bet bet) {
        this.amount = amount;
        this.bet = bet;
    }

    public int getAmount() {
        return this.amount;
    }

    public Bet getBet() {
        return this.bet;
    }
}
