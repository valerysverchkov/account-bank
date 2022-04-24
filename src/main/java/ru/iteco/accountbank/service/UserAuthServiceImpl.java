package ru.iteco.accountbank.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.UserAuthDto;
import ru.iteco.accountbank.model.entity.UserAuthEntity;
import ru.iteco.accountbank.repository.UserAuthRepository;

@Component
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAuthServiceImpl(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String create(UserAuthDto userAuthDto) {
        UserAuthEntity userAuth = UserAuthEntity.builder()
                .username(userAuthDto.getLogin())
                .password(
                        passwordEncoder.encode(userAuthDto.getPassword())
                )
                .build();
        return userAuthRepository.save(userAuth).getUsername();
    }
}
