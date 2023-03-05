package org.validator.creditCard.validation.validator;

import org.validator.creditCard.validation.strategy.ValidationStrategy;

public interface CreditCardFactory {
    AbstractValidator validateCreditCard(String cardNumber, ValidationStrategy strategy) throws Exception;
}
