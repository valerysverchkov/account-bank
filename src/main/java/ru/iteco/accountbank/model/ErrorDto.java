package ru.iteco.accountbank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String status;
    private String message;

}
