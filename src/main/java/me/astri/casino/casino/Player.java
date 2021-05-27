package me.astri.casino.casino;

import me.astri.casino.main.Lang;

import java.util.HashMap;

public class Player {
    public static final HashMap<String,Player> playerList = initPlayerList();

    private Lang language;
    private final String id;
    private final BankAccount bankAccount;

    public Player(String id) {
        this.language = Lang.ENGLISH;
        this.id = id;
        this.bankAccount = new BankAccount();
    }

    public static Player getPlayer(String id) {
        Player player;
        if(playerList.get(id) == null) {
            player = new Player(id);
            playerList.put(id, player);
        } else
            player = playerList.get(id);
        return player;
    }

    //setters
    public Player setLanguage(Lang language) {
        this.language = language;
        return this;
    }

    //getters
    public Lang getLang() {
        return this.language;
    }
    public String getId() {
        return this.id;
    }
    public BankAccount getBank() {
        return this.bankAccount;
    }

    //init
    private static HashMap<String,Player> initPlayerList() {
        return new HashMap<>(); //TODO db
    }
}
