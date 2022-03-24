package ru.iteco.accountbank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Integer id;
    private String name;
    private String email;

}
