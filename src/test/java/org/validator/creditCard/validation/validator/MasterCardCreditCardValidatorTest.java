package org.validator.creditCard.validation.validator;

import org.junit.jupiter.api.Test;
import org.validator.creditCard.validation.strategy.LuhnStrategy;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MasterCardCreditCardValidatorTest {
    @Test
    public void testValidMasterCardListWithLuhnStrategy() throws Exception {
        List<String> numbers = Arrays.asList("5537 0765 5312 7227", "5580 6463 4267 4402", "2720 5113 7811 2586", "2222 4200 0000 1113", "5425 2334 3010 9903");
        ValidationStrategy strategy = new LuhnStrategy();
        List<Boolean> results = new ArrayList<>();
        for (String number : numbers) {
            MasterCardCreditCardValidator validator = new MasterCardCreditCardValidator(number, strategy);
            results.add(validator.isValid());
        }
        assertTrue(results.stream().allMatch(Boolean::booleanValue));
    }

    @Test
    public void testInvalidMasterCardListWithLuhnStrategy() throws Exception {
        List<String> numbers = Arrays.asList("5537 0765 5312 7327", "5580 6463 4277 4402", "2720 5214 7811 2586", "2222 4200 1100 1113", "5425 2334 4020 9903");
        ValidationStrategy strategy = new LuhnStrategy();
        List<Boolean> results = new ArrayList<>();
        for (String number : numbers) {
            MasterCardCreditCardValidator validator = new MasterCardCreditCardValidator(number, strategy);
            results.add(validator.isValid());
        }
        assertFalse(results.stream().allMatch(Boolean::booleanValue));
    }

    @Test
    public void testNonMasterCardListWithLuhnStrategy() {
        List<String> numbers = Arrays.asList("5037 0765 5312 7327", "5680 6463 4277 4402", "2721 5214 7811 2586", "2220 4200 1100 1113", "6425 2334 4020 9903");
        ValidationStrategy strategy = new LuhnStrategy();
        for (String number : numbers) {
            assertThrows(InvalidMasterCardCardNumberException.class, () -> new MasterCardCreditCardValidator(number, strategy));
        }
    }
}
