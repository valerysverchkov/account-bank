package ru.iteco.accountbank.model;

import lombok.Data;

@Data
public class UserAuthDto {

    private String login;
    private String password;

}
