package ru.iteco.accountbank.service;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.iteco.accountbank.model.AccountInfo;
import ru.iteco.accountbank.model.BankBook;
import ru.iteco.accountbank.model.PersonalInfo;

@Slf4j
@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private final PersonalInformationService personalInformationService;
    private final Map<String, BankBookService> bankBookServices;

    public AccountServiceImpl(@Lazy PersonalInformationService personalInformationService,
                              Map<String, BankBookService> bankBookServices) {
        this.personalInformationService = personalInformationService;
        this.bankBookServices = bankBookServices;
    }

    @Override
    public AccountInfo getAccountInfoById(Integer id) {
        AccountInfo accountInfo = new AccountInfo();
        PersonalInfo personalInfo = personalInformationService.getPersonalInfoById(id);
        accountInfo.setPersonalInfo(personalInfo);
        log.info("BankBook services: {}", bankBookServices);
        for (Map.Entry<String, BankBookService> bankBookServiceEntry : bankBookServices.entrySet()) {
            BankBookService bankBookService = bankBookServiceEntry.getValue();
            List<BankBook> bankBooks = bankBookService.getBankBooksById(id);
            log.info("Bank books: {}", bankBooks);
            if (!bankBooks.isEmpty()) {
                accountInfo.setBankBooks(bankBooks);
            }
        }
        return accountInfo;
    }

    public String getPersonalInfoClass() {
        return personalInformationService.getClass().toString();
    }

}
