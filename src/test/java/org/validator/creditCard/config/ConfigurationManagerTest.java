package org.validator.creditCard.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationManagerTest {

    @Test
    void getMasterCardPattern() {
        String MASTERCARD_PATTERN = ConfigurationManager.INSTANCE.getCreditCardPattern("mastercard");
        assertTrue(MASTERCARD_PATTERN.length() > 4);
    }

    @Test
    void getAmExPattern() {
        String AMEX_PATTERN = ConfigurationManager.INSTANCE.getCreditCardPattern("american_express");
        assertTrue(AMEX_PATTERN.length() > 4);
    }

    @Test
    void getNonExistingPattern() {
        assertThrows(IllegalArgumentException.class, () -> ConfigurationManager.INSTANCE.getCreditCardPattern("unknown_card"));
    }
}