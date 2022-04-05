package ru.iteco.accountbank.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final String AT = "@";
    private static final String DOT = ".";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.contains(AT) && s.contains(DOT);
    }
}
