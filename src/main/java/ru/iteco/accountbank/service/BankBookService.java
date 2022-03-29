package ru.iteco.accountbank.service;

import java.util.List;
import ru.iteco.accountbank.model.BankBookDto;

public interface BankBookService {

    BankBookDto findById(Integer id);
    List<BankBookDto> findByUserId(Integer userId);
    BankBookDto create(BankBookDto bankBookDto);
    BankBookDto update(BankBookDto bankBookDto);
    void delete(Integer id);
    void deleteByUserId(Integer userId);

}
