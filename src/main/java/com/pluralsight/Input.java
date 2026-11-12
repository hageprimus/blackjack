package com.pluralsight;

import java.util.Scanner;

public class Input {
    private Input(){};

    private static final Scanner s = new Scanner(System.in);

    public static String getInputFromConsole(String display) {
        System.out.println(display);
        String output = s.nextLine().trim();
        return output;
    }

    public static void closeScanner() {
        s.close();
    }
}
