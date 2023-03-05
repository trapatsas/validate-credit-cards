package org.validator.creditCard.validation.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.validator.creditCard.config.ConfigurationManager;
import org.validator.creditCard.validation.strategy.ValidationStrategy;

public class RegexCreditCardFactory implements CreditCardFactory {
    private static final Logger logger = LogManager.getLogger(RegexCreditCardFactory.class);

    private final String mastercardPattern;
    private final String amexPattern;

    public RegexCreditCardFactory() {
        this.mastercardPattern = ConfigurationManager.INSTANCE.getCreditCardPattern("mastercard");
        this.amexPattern = ConfigurationManager.INSTANCE.getCreditCardPattern("american_express");
    }

    @Override
    public AbstractValidator validateCreditCard(String cardNumber, ValidationStrategy strategy) throws Exception {
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }

        switch (getCardType(cardNumber)) {
            case AMEX:
                logger.trace("American Express selected");
                return new AmericanExpressCreditCardValidator(cardNumber, strategy);
            case MASTERCARD:
                logger.trace("MasterCard selected");
                return new MasterCardCreditCardValidator(cardNumber, strategy);
            default:
                logger.trace("Generic Credit Card selected");
                return new GenericCreditCardValidator(cardNumber, strategy);
        }
    }

    private CardType getCardType(String cardNumber) {
        if (cardNumber.matches(amexPattern)) {
            return CardType.AMEX;
        } else if (cardNumber.matches(mastercardPattern)) {
            return CardType.MASTERCARD;
        } else {
            return CardType.GENERIC;
        }
    }

    private enum CardType {
        AMEX,
        MASTERCARD,
        GENERIC
    }
}
