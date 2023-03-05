package org.validator.creditCard.validation.strategy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LuhnStrategyTest {

    @Test
    public void testWithValidNumber() {
        String number = "123456789155";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(strategy.validate(number));
    }

    @Test
    public void testWithInvalidNumber() {
        String number = "1010101010100030";
        ValidationStrategy strategy = new LuhnStrategy();
        assertFalse(strategy.validate(number));
    }
}