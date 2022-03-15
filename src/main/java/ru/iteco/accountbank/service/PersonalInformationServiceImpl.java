package ru.iteco.accountbank.service;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.iteco.accountbank.model.PersonalInfo;

@Slf4j
@Service
@Lazy
public class PersonalInformationServiceImpl implements PersonalInformationService {

    @Value("${name}")
    private String name;

    public PersonalInformationServiceImpl() {
    }

    @PostConstruct
    public void init() {
        if (name.contains("N")) {
            log.info("Contains 'N'");
        }
    }


    @Override
    public PersonalInfo getPersonalInfoById(Integer id) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName(name);
        personalInfo.setUserId(id);
        return personalInfo;
    }

}
