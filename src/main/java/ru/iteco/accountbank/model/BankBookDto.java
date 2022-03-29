package ru.iteco.accountbank.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankBookDto {

    private Integer id;
    private Integer userId;
    private String number;
    private BigDecimal amount;
    private String currency;

}
