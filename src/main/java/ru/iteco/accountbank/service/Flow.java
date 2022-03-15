package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;

@Component
@Slf4j
public class Flow {

    private final ExternalService externalService;
    private final Process process;

    public Flow(ExternalService externalService, Process process) {
        this.externalService = externalService;
        this.process = process;
    }

    public void run(Integer id){
        ExternalInfo externalInfo = externalService.getExternalInfo(id);
        if (externalInfo.getInfo() != null) {
            process.run(externalInfo);
        } else {
            log.info("Not run process: {}", externalInfo);
        }
    }
}
