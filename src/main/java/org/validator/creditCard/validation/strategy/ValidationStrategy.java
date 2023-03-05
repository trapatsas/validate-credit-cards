package org.validator.creditCard.validation.strategy;

// An interface that defines common behavior for validation strategies
public interface ValidationStrategy {

    // An abstract method that validates a given number using an algorithm
    boolean validate(String number);
}
