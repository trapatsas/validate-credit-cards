package org.validator.creditCard.validation.validator;

import org.junit.jupiter.api.Test;
import org.validator.creditCard.validation.strategy.LuhnStrategy;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AmericanExpressCreditCardValidatorTest {
    @Test
    public void testValidAmericanExpressListWithLuhnStrategy() throws Exception {
        List<String> numbers = Arrays.asList("376102792936197", "347060116846136", "373309495650081", "374245455400126", "378282246310005");
        ValidationStrategy strategy = new LuhnStrategy();
        List<Boolean> results = new ArrayList<>();
        for (String number : numbers) {
            AmericanExpressCreditCardValidator validator = new AmericanExpressCreditCardValidator(number, strategy);
            results.add(validator.isValid());
        }
        assertTrue(results.stream().allMatch(Boolean::booleanValue));
    }

    @Test
    public void testInvalidAmericanExpressListWithLuhnStrategy() throws Exception {
        List<String> numbers = Arrays.asList("376102792937197", "347060116847236", "373309495651181", "374245450000126", "378281146310005");
        ValidationStrategy strategy = new LuhnStrategy();
        List<Boolean> results = new ArrayList<>();
        for (String number : numbers) {
            AmericanExpressCreditCardValidator validator = new AmericanExpressCreditCardValidator(number, strategy);
            results.add(validator.isValid());
        }
        assertFalse(results.stream().allMatch(Boolean::booleanValue));
    }

    @Test
    public void testNonAmericanExpressListWithLuhnStrategy() {
        List<String> numbers = Arrays.asList("5037 0765 5312 7327", "5680 6463 4277 4402", "2721 5214 7811 2586", "2220 4200 1100 1113", "6425 2334 4020 9903");
        ValidationStrategy strategy = new LuhnStrategy();
        for (String number : numbers) {
            assertThrows(InvalidAmericanExpressCardNumberException.class, () -> new AmericanExpressCreditCardValidator(number, strategy));
        }
    }
}
