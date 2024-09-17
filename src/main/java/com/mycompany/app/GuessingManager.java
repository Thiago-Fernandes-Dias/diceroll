package com.mycompany.app;

public class GuessingManager {
    private final int defaultAttempts = 3;

    private final DiceRoller diceRoller;
    private final UserInterface userInterface;
    private int attempts;

    public GuessingManager(int attempts, DiceRoller diceRoller, UserInterface userInterface) {
        this.diceRoller = diceRoller;
        this.userInterface = userInterface;
        if (attempts <= 0) {
            userInterface.showMessage(String.format(Messages.invalidQtyOfAttempts, defaultAttempts));
            this.attempts = defaultAttempts;
        }
        else {
            this.attempts = attempts;
        }
    }

    public void run() {
        diceRoller.rollDice();
        userInterface.showMessage(String.format(Messages.diceRolled, attempts));
        for (int i = 0; i < attempts; i++)
        {
            int userChoice = userInterface.readInteger(Messages.guessTheNumber);
            if (userChoice == diceRoller.getRollResult())
            {
                userInterface.showMessage(Messages.win);
                return;
            }
            userInterface.showMessage(Messages.wrongChoice);
        }
        userInterface.showMessage(Messages.lose);
    }
}