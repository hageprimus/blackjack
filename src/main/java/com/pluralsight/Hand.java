package com.pluralsight;

import java.util.List;
import java.util.ArrayList;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
    }

    public String display() {
        StringBuilder output = new StringBuilder();
        for (Card c : cards) {
            output.append(c.display()).append("|");
        }
        return "HAND: " + output.toString();
    }

    public int getHandValue() {
        int value = 0;
        for (Card c : cards) {
            var oldFaceUp = true;
            if (!c.isFaceUp()) {
                oldFaceUp = false;
                c.flip();
            }
            value += getCardValue(c, value);
            if (oldFaceUp != c.isFaceUp()) c.flip();
        }
        return value;
    }

    private int getCardValue(Card c, int curVal) {
        int value = 0;
        switch (c.getValue().toLowerCase()) {
            case "a" ->             value = curVal + 11 > 21 ? 1 : 11;
            case "q", "k", "j" ->   value = 10;
            default ->              value = Integer.parseInt(c.getValue());
        }
        return value;
    }
}
