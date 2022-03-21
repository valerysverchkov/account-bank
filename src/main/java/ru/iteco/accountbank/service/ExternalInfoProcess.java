package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import ru.iteco.accountbank.model.ExternalInfo;
import ru.iteco.accountbank.model.annotation.CheckRequest;

@Slf4j
public class ExternalInfoProcess implements Process {

    @Value("${id-not-process}")
    private Integer id;

    @CheckRequest
    @Override
    public boolean run(ExternalInfo externalInfo) {
        if (id.equals(externalInfo.getId())) {
            log.info("Process not need: {}", externalInfo);
            throw new RuntimeException("External Info with id: " + externalInfo.getId() + " not process.");
        }
        log.info("Process with: {}", externalInfo);
        return true;
    }

}
