package ru.iteco.accountbank.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.iteco.accountbank.model.BankBook;
import ru.iteco.accountbank.model.annotation.InjectRandom;

@Service
public class BankBookServiceImpl implements BankBookService {

    @InjectRandom(max = 100)
    private Integer number;

    @Override
    public List<BankBook> getBankBooksById(Integer id) {
        BankBook bankBook = new BankBook();
        bankBook.setNumber(number.longValue());
        bankBook.setUserId(id);
        ArrayList<BankBook> bankBooks = new ArrayList<BankBook>();
        bankBooks.add(bankBook);
        return bankBooks;
    }

}
