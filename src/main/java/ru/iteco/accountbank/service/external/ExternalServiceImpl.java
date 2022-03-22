package ru.iteco.accountbank.service.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class ExternalServiceImpl implements ExternalService {
    @Override
    public String getInfo() {
        log.info("Call get Info!");
        return "INFO!";
    }
}
