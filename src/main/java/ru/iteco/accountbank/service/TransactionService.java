package ru.iteco.accountbank.service;

import java.math.BigDecimal;
import ru.iteco.accountbank.model.BankBookDto;
import ru.iteco.accountbank.model.UserDto;

public interface TransactionService {

    Boolean transferBetweenBankBook(BankBookDto sourceBankBook, BankBookDto targetBankBook, BigDecimal amount);
    Boolean transferBetweenUser(UserDto sourceUser, UserDto targetUser, BigDecimal amount, String currency);

}
