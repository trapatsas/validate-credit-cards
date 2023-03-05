package org.validator.creditCard.validation.validator;

import org.junit.jupiter.api.Test;
import org.validator.creditCard.validation.strategy.LuhnStrategy;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    @Test
    public void testEmptyCreditCardWithLuhnStrategy() {
        String number = "";
        ValidationStrategy strategy = new LuhnStrategy();
        assertThrows(EmptyInputException.class, () -> new GenericCreditCardValidator(number, strategy));
    }

    @Test
    public void testLessLengthCreditCardWithLuhnStrategy() {
        String number = "123456789";
        ValidationStrategy strategy = new LuhnStrategy();
        assertThrows(InvalidLengthException.class, () -> new GenericCreditCardValidator(number, strategy));
    }

    @Test
    public void testMoreLengthCreditCardWithLuhnStrategy() {
        String number = "12345678901234567890";
        ValidationStrategy strategy = new LuhnStrategy();
        assertThrows(InvalidLengthException.class, () -> new GenericCreditCardValidator(number, strategy));
    }

    @Test
    public void testNoDigitsCreditCardWithLuhnStrategy() {
        String number = "This is not a CC";
        ValidationStrategy strategy = new LuhnStrategy();
        assertThrows(InvalidLengthException.class, () -> new GenericCreditCardValidator(number, strategy));
    }

}
