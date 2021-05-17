package me.astri.casino.main;


public enum Language {
    ENGLISH("EN", "english"),
    FRENCH("FR", "français"),
    GERMAN("GE", "german");

    private final String a;
    private final String b;

    Language(String a, String b) {
        this.a = a;
        this.b = b;
    }

    String getIdentifier() {
        return a;
    }

    String getLanguageName() {
        return b;
    }
}

