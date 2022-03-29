package ru.iteco.accountbank.controller;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.accountbank.model.UserDto;
import ru.iteco.accountbank.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUser() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto userDto = userService.getById(id);
        ResponseCookie userId = ResponseCookie.from("userId", userDto.getId().toString()).maxAge(600).secure(true).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, userId.toString())
                .body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

}
