package com.mycompany.app;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.stubbing.OngoingStubbing;

import java.util.stream.Stream;

public class GuessingManagerTests {
    private UserInterface userInterface = mock(UserInterface.class);
    private DiceRoller diceRoller = mock(DiceRoller.class);

    @BeforeEach
    public void setUp() {
        reset(userInterface);
        reset(diceRoller);
    }

    @ParameterizedTest
    @MethodSource
    public void run_ShallWriteTheCorrectMessages_IfUserWinsInTheFirstTry(int tries, int correctGuess) {
        setupCorrectGuessAndUserAttempts(correctGuess, new int[] { correctGuess });
        GuessingManager cut = new GuessingManager(tries, diceRoller, userInterface);
        cut.run();
        verifyDiceRolledAndWelcomeMessage(tries);
        verify(userInterface, atMostOnce()).readInteger(Messages.guessTheNumber);
        verify(userInterface, atMostOnce()).showMessage(Messages.win);
    }

    @ParameterizedTest
    @MethodSource
    public void run_ShallWriteTheCorrectMessages_IfUserWinsInTheLastTry(int tries, int correctGuess,
            int[] userAttempts) {
        setupCorrectGuessAndUserAttempts(correctGuess, userAttempts);
        GuessingManager cut = new GuessingManager(tries, diceRoller, userInterface);
        cut.run();
        verifyDiceRolledAndWelcomeMessage(tries);
        verify(userInterface, times(userAttempts.length - 1)).showMessage(Messages.wrongChoice);
        verify(userInterface, times(userAttempts.length)).readInteger(Messages.guessTheNumber);
        verify(userInterface, atMostOnce()).showMessage(Messages.win);
    }

    @ParameterizedTest
    @MethodSource
    public void run_ShallWriteTheCorrectMessages_IfTheUserLoses(int tries, int correctGuess, int[] userAttempts) {
        setupCorrectGuessAndUserAttempts(correctGuess, userAttempts);
        GuessingManager cut = new GuessingManager(tries, diceRoller, userInterface);
        cut.run();
        verifyDiceRolledAndWelcomeMessage(tries);
        verify(userInterface, times(userAttempts.length)).showMessage(Messages.wrongChoice);
        verify(userInterface, atMostOnce()).showMessage(Messages.lose);
    }

    private static Stream<Arguments> run_ShallWriteTheCorrectMessages_IfTheUserLoses() {
        return Stream.of(
                arguments(3, 4, new int[] { 1, 2, 6 }),
                arguments(4, 5, new int[] { 1, 2, 3, 4 }),
                arguments(5, 6, new int[] { 1, 2, 3, 4, 5 }));
    }

    private static Stream<Arguments> run_ShallWriteTheCorrectMessages_IfUserWinsInTheFirstTry() {
        return Stream.of(
                arguments(5, 4),
                arguments(4, 3),
                arguments(3, 3),
                arguments(2, 4));
    }

    private static Stream<Arguments> run_ShallWriteTheCorrectMessages_IfUserWinsInTheLastTry() {
        return Stream.of(
                arguments(3, 4, new int[] { 1, 2, 4 }),
                arguments(4, 5, new int[] { 1, 2, 3, 5 }),
                arguments(5, 6, new int[] { 1, 2, 3, 4, 6 }));
    }

    private void setupCorrectGuessAndUserAttempts(int correctGuess, int[] userAttempts) {
        when(diceRoller.getRollResult()).thenReturn(correctGuess);
        OngoingStubbing<Integer> readIntegerStubbing = when(userInterface.readInteger(any(String.class)));
        for (int attempt : userAttempts) {
            readIntegerStubbing = readIntegerStubbing.thenReturn(attempt);
        }
    }

    private void verifyDiceRolledAndWelcomeMessage(int tries) {
        InOrder inOrder = inOrder(diceRoller, userInterface);
        inOrder.verify(diceRoller).rollDice();
        inOrder.verify(userInterface).showMessage(String.format(Messages.diceRolled, tries));
    }
}
