package me.astri.casino.wheel;

import me.astri.casino.main.Utils;

import java.util.*;

public class Bet {
    private static final HashMap<String, Bet> betList = initBets();

    private final String url;
    private final int multiplier;
    private final ArrayList<Long> issues;

    public Bet(List<String> args) {
        issues = new ArrayList<>();
        this.url = args.get(1);
        this.multiplier = Integer.parseInt(args.get(2));
        Arrays.stream(args.get(3).split("/")).forEach(i -> issues.add(Long.parseLong(i)));
    }

    public Bet(Bet bet) {
        this.url = bet.url;
        this.multiplier = bet.multiplier;
        this.issues = bet.issues;
    }

    public static HashMap<String, Bet> initBets() {
        HashMap<String,Bet> betList = new HashMap<>();
        for(List<String> args : Utils.readCSV("WHEEL_BETS")) {
            betList.put(args.get(0),new Bet(args));
        }
        return betList;
    }

    public static Bet getBet(String betName) {
        return betList.get(betName);
    }

    public String getUrl() {
        return this.url;
    }

    public int getMultiplier() {
        return this.multiplier;
    }

    public ArrayList<Long> getIssues() {
        return this.issues;
    }
}
