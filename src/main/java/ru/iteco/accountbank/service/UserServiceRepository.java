package ru.iteco.accountbank.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.AddressDto;
import ru.iteco.accountbank.model.UserDto;
import ru.iteco.accountbank.model.entity.AddressEntity;
import ru.iteco.accountbank.model.entity.UserEntity;
import ru.iteco.accountbank.repository.AddressRepository;
import ru.iteco.accountbank.repository.UserRepository;

@Slf4j
@Component
@Primary
public class UserServiceRepository implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserServiceRepository(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Integer id) {
        UserEntity userEntity = userRepository.getById(id);
        log.info("User from repo: {}", userEntity);
        AddressEntity address = userEntity.getAddress();
        log.info("User from address: {}", address.getUser());
        return mapToDto(userEntity);
    }

    @Override
    public UserDto create(UserDto userDto) {
        UserEntity userEntity = mapToEntity(userDto);
        return mapToDto(userRepository.save(userEntity));
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow(() -> new RuntimeException("User not found!"));
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        AddressDto addressDto = userDto.getAddress();
        AddressEntity address = userEntity.getAddress();
        if (addressDto != null && address != null) {
            address.setCountry(addressDto.getCountry());
            address.setCity(addressDto.getCity());
            address.setStreet(addressDto.getStreet());
            address.setHome(addressDto.getHome());
        }
        return mapToDto(userRepository.save(userEntity));
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress() != null ?
                        AddressDto.builder()
                            .country(userEntity.getAddress().getCountry())
                            .city(userEntity.getAddress().getCity())
                            .street(userEntity.getAddress().getStreet())
                            .home(userEntity.getAddress().getHome())
                            .build()
                        : null)
                .build();
    }

    private UserEntity mapToEntity(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .address(userDto.getAddress() != null ?
                        AddressEntity.builder()
                                .country(userDto.getAddress().getCountry())
                                .city(userDto.getAddress().getCity())
                                .street(userDto.getAddress().getStreet())
                                .home(userDto.getAddress().getHome())
                                .build()
                        : null)
                .build();
    }

}
