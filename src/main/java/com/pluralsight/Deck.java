package com.pluralsight;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
        String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        for (String suit: suits) {
            for (String value: values) {
                Card card = new Card(suit, value);
                cards.add(card);
            }
        }
    }

    public int getSize() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    //IDK if this is gonna work lol
    public void sort() {
        cards.sort(Comparator.comparing(Card::getSuit));
        cards.sort(Comparator.comparing(Card::getValue));
    }

    public void addCard(Card newCard) {
        boolean dupe = false;
        for (Card c : cards) {
            if (newCard.getValue().equalsIgnoreCase(c.getValue()) && newCard.getSuit().equalsIgnoreCase(c.getSuit())) {
                dupe = true;
                break;
            }
        }
        if (!dupe) cards.add(newCard);
    }

    public Card dealCard() {
        shuffle();
        if (!cards.isEmpty()) {
            Card c = cards.remove(0);
            return c;
        } else {
            return null;
        }
    }
}