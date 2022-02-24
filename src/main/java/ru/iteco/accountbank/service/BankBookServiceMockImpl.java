package ru.iteco.accountbank.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.iteco.accountbank.model.BankBook;

@Service
public class BankBookServiceMockImpl implements BankBookService {
    @Override
    public List<BankBook> getBankBooksById(Integer id) {
        return new ArrayList<BankBook>();
    }
}
