package ru.iteco.accountbank.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CurrencyValidator.class)
public @interface Currency {

    String message() default "Некорректная валюта!"; //ключ ValidationMessages.properties
    Class<?>[] groups() default { }; //группа проверки
    Class<? extends Payload>[] payload() default { }; //полезная нагрузка

}
