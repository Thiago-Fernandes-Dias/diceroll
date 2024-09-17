package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DiceRollerImplTests {
    private Random random;

    @BeforeEach
    public void setUp() {
        random = Mockito.mock(Random.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, -3, 0, 1})
    public void constructor_ShallThrowsAnIllegalArgumentException_IfSidesIsLessThan2(int invalidSidesQty) {
        assertThrows(IllegalArgumentException.class, () -> new DiceRollerImpl(random, invalidSidesQty));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 6})
    public void constructor_ShallThrowsNothing_ForAValidQtyOfSides(int validSidesQty) {
        assertDoesNotThrow(() -> new DiceRollerImpl(random, validSidesQty));
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 9, 10})
    public void rollDice_ShallSetTheRollResult_WhenCalled(int sides) {
        when(random.nextInt(2, sides + 1)).thenReturn(6);
        DiceRollerImpl cut = new DiceRollerImpl(random, sides);
        cut.rollDice();
        assertEquals(cut.getRollResult(), 6);
    }
}
