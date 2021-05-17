package me.astri.casino.wheel;

import me.astri.casino.main.Utils;

import java.util.*;

public class Bet {
    private static final HashMap<String, Bet> betList = initBets();

    private final String url;
    private final int multiplier;
    private final ArrayList<Integer> issues = new ArrayList<>();

    public Bet(List<String> args) {
        this.url = args.get(1);
        this.multiplier = Integer.parseInt(args.get(2));
        Arrays.stream(args.get(3).split("/")).forEach(i -> issues.add(Integer.parseInt(i)));
    }

    public static HashMap<String, Bet> initBets() {
        ArrayList<List<String>> CSVBets = Utils.readCSV("WHEEL_BETS");
        HashMap<String,Bet> betList = new HashMap<>();
        for(List<String> args : CSVBets) {
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

    public ArrayList<Integer> getIssues() {
        return this.issues;
    }
}
