package me.astri.casino.casino;

import me.astri.casino.main.Lang.Language;
import net.dv8tion.jda.api.entities.Member;

import java.util.HashMap;

public class Player {
    private static final HashMap<String,Player> playerList = initPlayerList();
    private Language language;
    private final String id;
    private final BankAccount bankAccount;
    private String name;

    public Player(Language language, String id, String name) {
        this.language = language;
        this.id = id;
        this.bankAccount = new BankAccount();
        this.name = name;
    }

    public Player(Member member) {
        this(Language.ENGLISH,member.getId(),member.getEffectiveName());
    }

    public static Player getPlayer(String id) {
        return playerList.get(id);
    }

    //setters
    public Player setLanguage(Language language) {
        this.language = language;
        return this;
    }
    public Player setName(String name) {
        if(name.isBlank()) throw new IllegalArgumentException("Name may not be blank!");
        this.name = name;
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
    public String getName() {
        return this.name;
    }

    //init
    private static HashMap<String,Player> initPlayerList() {
        Player p = new Player(Language.ENGLISH,"317236712440856576","astri");
        HashMap<String,Player> list = new HashMap<>();
        list.put(p.id,p);
        return list; //TODO db
    }
}
