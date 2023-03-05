package org.validator.creditCard.validation.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.validator.creditCard.config.ConfigurationManager;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MasterCardCreditCardValidator extends AbstractValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(MasterCardCreditCardValidator.class);
    private static final String MASTERCARD_PATTERN = ConfigurationManager.INSTANCE.getCreditCardPattern("mastercard");

    private final ValidationStrategy strategy;

    public MasterCardCreditCardValidator(String input, ValidationStrategy strategy) throws Exception {
        super(input);
        Pattern pattern = Pattern.compile(MASTERCARD_PATTERN);
        Matcher matcher = pattern.matcher(getNumber());
        /*
        If this implementation is called from the RegexCreditCardFactory, this check always passes, but
        it is still needed here for any use case where this implementation is called outside the specific factory
        to make sure that this class only accepts valid MasterCard cards
        */
        if (!matcher.matches()) {
            throw new InvalidMasterCardCardNumberException("Invalid MasterCard card number", 1500);
        }
        /*
        This is where extra validation checks specific for MasterCard credit cards
        can be added. There are no meaningful tests here at the moment, but I add
        this test that always passes as a placeholder for demonstration.
         */
        if (number.length() != 16) {
            throw new InvalidMasterCardCardNumberException("Invalid MasterCard card number - Length should be 16 digits", 1501);
        }
        this.strategy = strategy;
        logger.info("Card with number {} is a MasterCard card, using strategy: {}", input, strategy.getClass().getSimpleName());
    }

    public boolean isValid() {
        return strategy.validate(getNumber());
    }

    @Override
    public String toString() {
        return String.format("MasterCard Credit Card Validator: %s", getNumber());
    }
}