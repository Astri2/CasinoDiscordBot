package me.astri.casino.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Lang {
    ENGLISH("\uD83C\uDDFA\uD83C\uDDF8","English"),
    FRENCH("\uD83C\uDDEB\uD83C\uDDF7","French"),
    GERMAN("\uD83C\uDDE9\uD83C\uDDEA","German");

    public String emoji;
    public String name;
    public String getEmoji() {
        return this.emoji;
    }
    public String getName() {
        return this.name;
    }

    Lang(String a, String b) {
        this.emoji = a;
        this.name = b;
    }

    public static Lang getEnumFromEmoji(String emote) {
        for(Lang language : Lang.values()) {
            if(language.emoji.equalsIgnoreCase(emote))
                return language;
        }
        return null;
    }

    public static Lang getEnumFromName(String name) {
        if(Lang.valueOf(name.toUpperCase()) != null)
            return Lang.valueOf(name.toUpperCase());

        for(Lang language : Lang.values()) {
            for(Lang lang : Lang.values()) {
               if(lang.name.equalsIgnoreCase(name))
                   return lang;
            }
        }
        return null;
    }

    public static HashMap<Lang,HashMap<String,String>> langsMap = new HashMap<>();

    public static String get(Lang lang, String key, String ... variables) {
        if(langsMap.get(lang) == null)
            loadLang(lang);
        String str = langsMap.get(lang).get(key);
        for(int i = 0 ; i < variables.length ; i++)
            str = str.replace("{"+i+'}',variables[i]);
        return str;
    }

    private static void loadLang(Lang lang) {
        ArrayList<List<String>> CSV_lang = Utils.readCSV(lang.toString());
        HashMap<String,String> langMap = new HashMap<>();
        langsMap.put(lang,langMap);
        for(List<String> args : CSV_lang)
            langMap.put(args.get(0),args.get(1));
    }
}

