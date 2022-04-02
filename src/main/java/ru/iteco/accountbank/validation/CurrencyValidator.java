package ru.iteco.accountbank.validation;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final Set<String> CURRENCY = Set.of("RUB", "EUR", "USD", "GBP");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return CURRENCY.contains(value);
    }

}
