package ru.iteco.accountbank.service;

import java.util.List;
import ru.iteco.accountbank.model.UserDto;

public interface UserService {

    List<UserDto> getAll();
    UserDto getById(Integer id);
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    void delete(Integer id);

}
