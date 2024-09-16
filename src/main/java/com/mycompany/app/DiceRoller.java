package com.mycompany.app;

import java.util.Random;

public class DiceRoller implements DiceRollerInterface {
    private final Random random;
    private final int sides;

    public DiceRoller(Random random, int sides) throws IllegalArgumentException {
        this.random = random;
        if (sides < 2) {
            throw new IllegalArgumentException("Invalid qty. of sides. It must be bigger than 2");
        }
        this.sides = sides;
    }

    private int rollResult = 1;

    @Override
    public int getRollResult() {
        return rollResult;
    }

    @Override
    public void rollDice() {
        int num = random.nextInt(2, sides + 1);
        rollResult = num;
    }
}
