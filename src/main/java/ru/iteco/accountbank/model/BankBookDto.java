package ru.iteco.accountbank.model;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import ru.iteco.accountbank.validation.Create;
import ru.iteco.accountbank.validation.Currency;
import ru.iteco.accountbank.validation.Update;

@Data
@Builder
public class BankBookDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;
    private Integer userId;
    @NotBlank(message = "Not blank!")
    private String number;
    @Min(value = 0L)
    private BigDecimal amount;
    @Currency
    private String currency;

}
