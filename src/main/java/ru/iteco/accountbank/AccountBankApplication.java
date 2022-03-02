package ru.iteco.accountbank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.iteco.accountbank.model.AccountInfo;
import ru.iteco.accountbank.service.AccountService;
import ru.iteco.accountbank.service.AccountServiceImpl;
import ru.iteco.accountbank.service.BankBookService;
import ru.iteco.accountbank.service.BankBookServiceImpl;
import ru.iteco.accountbank.service.PersonalInformationService;
import ru.iteco.accountbank.service.PersonalInformationServiceImpl;

@ComponentScan
@PropertySource("classpath:application.properties")
public class AccountBankApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AccountBankApplication.class);
        AccountService accountService = applicationContext.getBean(AccountService.class);
        AccountInfo accountInfo = accountService.getAccountInfoById(1);
        System.out.println(accountInfo);
    }

}
