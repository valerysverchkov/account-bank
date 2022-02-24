package ru.iteco.accountbank.service;

import java.util.List;
import ru.iteco.accountbank.model.BankBook;

public interface BankBookService {

    List<BankBook> getBankBooksById(Integer id);

}
