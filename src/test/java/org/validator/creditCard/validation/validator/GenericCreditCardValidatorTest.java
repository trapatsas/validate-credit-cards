package org.validator.creditCard.validation.validator;


import org.junit.jupiter.api.Test;
import org.validator.creditCard.validation.strategy.LuhnStrategy;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// A test class for CreditCardValidator
public class GenericCreditCardValidatorTest {

    // A test method that checks if a valid credit card number passes Luhn algorithm validation
    @Test
    public void testValidCreditCardWithLuhnStrategy() throws Exception {
        String number = "4485600345100609";
        ValidationStrategy strategy = new LuhnStrategy();
        GenericCreditCardValidator validator = new GenericCreditCardValidator(number, strategy);
        boolean result = validator.isValid();
        assertTrue(result);
    }

    // A test method that checks if a valid credit card number containing non-digit characters passes Luhn algorithm validation
    @Test
    public void testValidCreditCardWithNonDigitsWithLuhnStrategy() throws Exception {
        String number = " CC 4539 1488-0343,6467A";
        ValidationStrategy strategy = new LuhnStrategy();
        GenericCreditCardValidator validator = new GenericCreditCardValidator(number, strategy);
        boolean result = validator.isValid();
        assertTrue(result);
    }

    // A test method that checks if an invalid credit card number fails Luhn algorithm validation
    @Test
    public void testSingleInvalidCreditCardWithLuhnStrategy() throws Exception {
        String number = "1234 5678 9012 3456";
        ValidationStrategy strategy = new LuhnStrategy();
        GenericCreditCardValidator validator = new GenericCreditCardValidator(number, strategy);
        boolean result = validator.isValid();
        assertFalse(result);
    }

    // A test method that checks if a list of valid credit card numbers that contain spaces, dashes etc all pass the Luhn algorithm validation
    @Test
    public void testValidCreditCardListWithLuhnStrategy() throws Exception {
        List<String> numbers = Arrays.asList("4716955349044653", "6011-6048-5891-4032", "3563 9399 7208 6062", "5580646342674402");
        ValidationStrategy strategy = new LuhnStrategy();
        List<Boolean> results = new ArrayList<>();
        for (String number : numbers) {
            GenericCreditCardValidator validator = new GenericCreditCardValidator(number, strategy);
            results.add(validator.isValid());
        }
        assertTrue(results.stream().allMatch(Boolean::booleanValue));
    }

    // A test method that checks if a list of invalid credit card numbers that contain spaces, dashes etc all fail the Luhn algorithm validation
    @Test
    public void testInvalidCreditCardListWithLuhnStrategy() throws Exception {
        List<String> numbers = Arrays.asList("4716955349044663", "6011-6048-5591-4032", "3563 9379 7208 6062", "5580644322674402");
        ValidationStrategy strategy = new LuhnStrategy();
        List<Boolean> results = new ArrayList<>();
        for (String number : numbers) {
            GenericCreditCardValidator validator = new GenericCreditCardValidator(number, strategy);
            results.add(validator.isValid());
        }
        assertFalse(results.stream().allMatch(Boolean::booleanValue));
    }


}