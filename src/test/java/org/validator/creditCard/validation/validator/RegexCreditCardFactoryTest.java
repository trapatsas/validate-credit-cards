package org.validator.creditCard.validation.validator;

import org.junit.jupiter.api.Test;
import org.validator.creditCard.validation.strategy.LuhnStrategy;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import static org.junit.jupiter.api.Assertions.*;

class RegexCreditCardFactoryTest {

    @Test
    void testValidMasterCard01WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "5537076553127227";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof MasterCardCreditCardValidator);
    }

    @Test
    void testValidMasterCard02WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "5580646342674402";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof MasterCardCreditCardValidator);
    }

    @Test
    void testValidAmEx01WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "376102792936197";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof AmericanExpressCreditCardValidator);
    }

    @Test
    void testValidAmEx02WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "347060116846136";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof AmericanExpressCreditCardValidator);
    }

    @Test
    void testValidVisa01WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "4532888711880762";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof GenericCreditCardValidator);
    }

    @Test
    void testValidVisa02WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "4485600345100609";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof GenericCreditCardValidator);
    }

    @Test
    void testValidVisa03WithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "4716955349044653";
        ValidationStrategy strategy = new LuhnStrategy();
        assertTrue(regexCreditCardFactory.validateCreditCard(number, strategy) instanceof GenericCreditCardValidator);
    }

    @Test
    void testEndToEndWithLuhnStrategy() throws Exception {
        RegexCreditCardFactory regexCreditCardFactory = new RegexCreditCardFactory();
        String number = "376102792936197";
        ValidationStrategy strategy = new LuhnStrategy();
        boolean result = regexCreditCardFactory.validateCreditCard(number, strategy).isValid();
        assertTrue(result);
    }
}