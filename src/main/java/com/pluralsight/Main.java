package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Player> players = populatePlayers();
        Blackjack game = new Blackjack(players);
        game.runGame();
        Input.closeScanner();
    }

    public static List<Player> populatePlayers() {
        List<Player> players = new ArrayList<>();
        boolean playersComplete = false;
        do {
            String PLAYER_MENU = """
                    Enter the name of your next player.
                    Enter DONE to finish entering players and start the game!
                    """;
            String userInput = Input.getInputFromConsole(PLAYER_MENU);
            if (userInput.equalsIgnoreCase("done")) {
                playersComplete = true;
            } else {
                players.add(new Player(userInput));
            }
        } while (!playersComplete);
        return players;
    }
}
