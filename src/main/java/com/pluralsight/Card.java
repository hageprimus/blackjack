package com.pluralsight;

public class Card {
    public static final String SPADES = "\u2660";
    public static final String CLUBS = "\u2663";
    public static final String HEARTS = "\u2665";
    public static final String DIAMONDS = "\u2666";

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";

    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String BLACK_BACKGROUND = "\u001B[40m";

    private String suit;
    private String value;
    private boolean faceUp;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return faceUp ? suit : "#";
    }

    public String getValue() {
        return faceUp ? value : "#";
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        faceUp = !faceUp;
    }

    public String display() {
        //Can't display a face down card
        if (!isFaceUp()) return "##";

        //Formatting
        String suitIcon = "";
        String suitColor = "";
        switch (suit.toLowerCase()) {
            case "spades" -> {
                suitIcon = SPADES;
                suitColor = BLACK;
            }
            case "clubs" -> {
                suitIcon = CLUBS;
                suitColor = BLACK;
            }
            case "hearts" -> {
                suitIcon = HEARTS;
                suitColor = RED;
            }
            case "diamonds" -> {
                suitIcon = DIAMONDS;
                suitColor = RED;
            }
        }
        return suitColor + WHITE_BACKGROUND + value + suitIcon + BLACK_BACKGROUND + RESET;
    }
}
