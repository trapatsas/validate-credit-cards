package org.validator.creditCard.validation.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// A superclass that defines common attributes and methods for validators
public abstract class AbstractValidator implements Validator {
    private static final Logger logger = LogManager.getLogger(AbstractValidator.class);
    protected final String number;

    protected AbstractValidator(String input) throws Exception {
        logger.info("Validating card with number: {}", input);

        if (input == null || input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty", 1000);
        }

        // Use the replaceAll method with the regex "\\D+" to replace all non-digit characters with an empty string
        // This way only valid digits are extracted from the input string
        number = input.replaceAll("\\D+", "");

        // Credit card numbers must be between 10 and 19 digits long, and are usually 16 digits long.
        // Source: https://www.thebalancemoney.com/what-do-the-numbers-on-your-credit-card-mean-4588401
        if (number.length() < 10 || number.length() > 19) {
            throw new InvalidLengthException("The number's length is not between 10 and 19", 1001);
        }
        // If no exception is thrown, print a success message
        logger.info("Input {} is valid", number);
    }

    public String getNumber() {
        return number;
    }

    // Other methods to be implemented by concrete classes
}


