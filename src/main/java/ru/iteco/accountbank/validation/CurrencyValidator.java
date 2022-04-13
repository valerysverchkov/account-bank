package ru.iteco.accountbank.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ru.iteco.accountbank.repository.CurrencyRepository;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private final CurrencyRepository currencyRepository;

    public CurrencyValidator(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return currencyRepository.findByName(value) != null;
    }

}
