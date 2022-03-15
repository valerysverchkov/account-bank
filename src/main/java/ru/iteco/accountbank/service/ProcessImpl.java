package ru.iteco.accountbank.service;

import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;

@Component
public class ProcessImpl implements Process {
    @Override
    public boolean run(ExternalInfo externalInfo) {
        return false;
    }
}
