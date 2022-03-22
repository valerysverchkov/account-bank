package ru.iteco.accountbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.accountbank.service.external.ExternalService;

@RestController
class DefaultController {

    private final ExternalService externalService;

    DefaultController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/info")
    String getInfo() {
        return externalService.getInfo();
    }

}
