package ru.iteco.accountbank.service;

import ru.iteco.accountbank.model.AccountInfo;

public interface AccountService {

    AccountInfo getAccountInfoById(Integer id);

    String getPersonalInfoClass();

}
