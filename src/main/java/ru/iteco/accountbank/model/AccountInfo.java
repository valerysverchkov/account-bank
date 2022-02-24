package ru.iteco.accountbank.model;

import java.util.List;
import lombok.Data;

@Data
public class AccountInfo {

    private PersonalInfo personalInfo;
    private List<BankBook> bankBooks;

}
