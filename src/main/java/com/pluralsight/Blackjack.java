package com.pluralsight;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
    private List<Player> players;
    private Deck deck;
    private boolean complete;

    public Blackjack() {
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.complete = false;
    }

    public Blackjack(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.complete = false;
    }

    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void runGame() {
        do {
            //Deal 2 cards to each player to start
            for (int i = 0; i < 2; i++) {
                for (Player p : players) {
                    p.getHand().addCard(deck.dealCard());
                }
            }

            //For each player, display their hand and ask if they want to hit or stay
            for (Player p : players) {
                System.out.println("\n" + p.getName());
                System.out.println(p.getHand().display());
                System.out.println("Value: " + p.getHand().getHandValue());
                String userInput = Input.getInputFromConsole("Press H to hit, or press S to stay.");
                while (!userInput.equalsIgnoreCase("h") && !userInput.equalsIgnoreCase("s")) {
                    System.out.println("Invalid input!");
                    userInput = Input.getInputFromConsole("Press H to hit, or press S to stay.");
                }
                if (userInput.equalsIgnoreCase("h")) {
                    Card newCard = deck.dealCard();
                    p.getHand().addCard(newCard);
                    System.out.println(p.getName() + " chose to hit. " +
                        newCard.display() + " has been added to the hand with a new value of " + p.getHand().getHandValue() + ".");
                } else {
                    System.out.println(p.getName() + " chose to stay. Moving to next player.");
                }
            }

            //Get the highest value among the players without going over 21
            int highest = 0;
            for (Player p : players) {
                var value = p.getHand().getHandValue();
                if (value > highest && value <= 21)
                    highest = value;
            }
            //Get a list of players who have that hand value to account for ties
            List<Player> winners = new ArrayList<>();
            for (Player p : players) {
                if (highest == p.getHand().getHandValue())
                    winners.add(p);
            }
            //Print out a list of players
            if (!winners.isEmpty()) {
                System.out.println("\n" + "The following players have won the game with a hand value of " + highest + ":");
                for (Player p : winners) {
                    System.out.println(p.getName());
                }
            } else {
                System.out.println("\n" + "Everyone busted out! You guys suck at blackjack.");
            }

            //Return all cards to the deck and repeat the game until the players are satisfied
            String exitMenu = """
                    Enter DONE to end the game.
                    Enter anything else to play another round.
                    """;
            String userInput = Input.getInputFromConsole(exitMenu);
            if (userInput.equalsIgnoreCase("done")) {
                complete = true;
            } else {
                //Return all cards to the deck
                for (Player p : players) {
                    for (Card c : p.getHand().getCards()) {
                        deck.addCard(c);
                    }
                    p.getHand().getCards().clear();
                }
            }
        } while (!complete);
    }
}
