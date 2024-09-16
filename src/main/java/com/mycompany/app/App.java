package com.mycompany.app;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DiceRollerInterface diceRoller = new DiceRoller(new Random(), 6);
        IUserInterface userInterface = new ConsoleUserInterface(new ConsoleInterface());
        GuessingManager guessingManager = new(3, diceRoller, userInterface);
        guessingManager.Run();
    }
}
