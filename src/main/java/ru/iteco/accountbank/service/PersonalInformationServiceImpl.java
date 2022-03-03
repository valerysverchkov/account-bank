package ru.iteco.accountbank.service;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.iteco.accountbank.model.PersonalInfo;

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
            System.out.println("Contains 'N'");
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
