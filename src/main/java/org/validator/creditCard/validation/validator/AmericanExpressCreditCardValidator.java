package org.validator.creditCard.validation.validator;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.validator.creditCard.config.ConfigurationManager;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Define a class that extends AbstractValidator and implements Validator
public class AmericanExpressCreditCardValidator extends AbstractValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(AmericanExpressCreditCardValidator.class);
    private static final String AMEX_PATTERN = ConfigurationManager.INSTANCE.getCreditCardPattern("american_express");

    private final ValidationStrategy strategy;

    public AmericanExpressCreditCardValidator(String input, ValidationStrategy strategy) throws Exception {
        super(input);
        Pattern pattern = Pattern.compile(AMEX_PATTERN);
        Matcher matcher = pattern.matcher(getNumber());
        /*
        If this implementation is called from the RegexCreditCardFactory, this check always passes, but
        it is still needed here for any use case where this implementation is called outside the specific factory
        to make sure that this class only accepts valid American Express cards
        */
        if (!matcher.matches()) {
            throw new InvalidAmericanExpressCardNumberException("Invalid American Express card number", 1300);
        }
        /*
        This is where extra validation checks specific for American Express credit cards
        can be added. There are no meaningful tests here at the moment, but I add
        this test that always passes as a placeholder for demonstration.
         */
        if (!getNumber().startsWith("3")) {
            throw new InvalidAmericanExpressCardNumberException("Invalid American Express card number - does not start with 3", 1301);
        }
        this.strategy = strategy;
        logger.info("Card with number {} is a American Express card, using strategy: {}", input, strategy.getClass().getSimpleName());
    }

    public boolean isValid() {
        return strategy.validate(getNumber());
    }

    @Override
    public String toString() {
        return String.format("American Express Credit Card Validator: %s", getNumber());
    }
}