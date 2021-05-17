package me.astri.casino.casino;

import me.astri.casino.main.Language;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class Player {
    public static final HashMap<String,Player> playerList = initPlayerList();
    private Language language;
    private final String id;
    private final BankAccount bankAccount;

    public Player(Language language, String id) {
        this.language = language;
        this.id = id;
        this.bankAccount = new BankAccount();
    }

    public Player(User user) {
        this(Language.ENGLISH,user.getId());
    }

    public static Player getPlayer(String id) {
        return playerList.get(id);
    }

    public static void addPlayer(Player player) {
        playerList.put(player.getId(),player);
    }

    //setters
    public Player setLanguage(Language language) {
        this.language = language;
        return this;
    }

    //getters
    public Language getLanguage() {
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
