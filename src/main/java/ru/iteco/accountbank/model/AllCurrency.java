package ru.iteco.accountbank.model;

import java.util.Map;
import lombok.Data;

@Data
public class AllCurrency {

    private String base;
    private String date;
    private Map<String, String> rates;

}
