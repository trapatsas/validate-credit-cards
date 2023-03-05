package org.validator.creditCard.validation.strategy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// A concrete class that implements ValidationStrategy and provides Luhn algorithm implementation
public class LuhnStrategy implements ValidationStrategy {

    private static final Logger logger = LogManager.getLogger(LuhnStrategy.class);

    // An overridden method that validates a given number using Luhn algorithm
    @Override
    public boolean validate(String cardNumber) {
        int evenSum = 0;
        int oddSum = 0;

        // Loop over every second digit from right to left
        for (int i = cardNumber.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            digit *= 2;
            if (digit > 9) {
                digit -= 9;
            }
            evenSum += digit;
        }

        // Loop over every other digit from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            oddSum += digit;
        }

        // Return true if sum is divisible by 10; otherwise return false
        boolean result = (evenSum + oddSum) % 10 == 0;
        if (result) {
            logger.info("{} passes the Luhn validation", cardNumber);
        } else {
            logger.info("{} fails the Luhn validation", cardNumber);
        }
        return result;
    }
}


