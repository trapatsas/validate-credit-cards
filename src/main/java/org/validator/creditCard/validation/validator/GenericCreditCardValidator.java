package org.validator.creditCard.validation.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

// A subclass that validates credit card numbers using different strategies
public class GenericCreditCardValidator extends AbstractValidator {

    private static final Logger logger = LogManager.getLogger(GenericCreditCardValidator.class);

    private final ValidationStrategy strategy;

    public GenericCreditCardValidator(String input, ValidationStrategy strategy) throws Exception {
        super(input);
        this.strategy = strategy;
        logger.info("Using strategy: {}", strategy.getClass().getSimpleName());
    }

    // A method that validates the credit card number using the strategy
    public boolean isValid() {
        return strategy.validate(getNumber());
    }
}


