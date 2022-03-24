package ru.iteco.accountbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.accountbank.model.ExternalInfo;
import ru.iteco.accountbank.service.ExternalService;

@RestController
class DefaultController {

    private final ExternalService externalService;

    DefaultController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/info")
    ExternalInfo getInfo() {
        return externalService.getInfo();
    }

}
