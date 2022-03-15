package ru.iteco.accountbank.service;

import ru.iteco.accountbank.model.ExternalInfo;

public interface Process {

    boolean run(ExternalInfo externalInfo);

}
