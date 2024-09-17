package com.mycompany.app;

import java.util.Random;

public class DiceRollerImpl implements DiceRoller {
    private final Random random;
    private final int sides;

    public DiceRollerImpl(Random random, int sides) throws IllegalArgumentException {
        this.random = random;
        if (sides < 2) {
            throw new IllegalArgumentException("Invalid quantity. of sides. It must be bigger than 2");
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
