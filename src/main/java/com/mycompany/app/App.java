package com.mycompany.app;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DiceRoller diceRoller = new DiceRollerImpl(new Random(), 6);
        UserInterface userInterface = new ConsoleUserInterface(new ConsoleInterfaceImpl());
        GuessingManager guessingManager = new GuessingManager(3, diceRoller, userInterface);
        guessingManager.run();
    }
}
