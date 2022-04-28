package ru.iteco.accountbank.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.iteco.accountbank.config.jwt.JwtProvider;
import ru.iteco.accountbank.model.UserAuthDto;
import ru.iteco.accountbank.service.UserAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAuthService userAuthService;
    private final JwtProvider jwtProvider;

    public AuthController(UserAuthService userAuthService, JwtProvider jwtProvider) {
        this.userAuthService = userAuthService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/create")
    public String create(@RequestBody UserAuthDto userAuthDto) {
        return userAuthService.create(userAuthDto);
    }

    @GetMapping("/login")
    public ResponseEntity<Void> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        byte[] decodeAuthByte = Base64.getDecoder().decode(auth.substring(6));
        String decodeAuthStr = new String(decodeAuthByte, StandardCharsets.UTF_8);
        String[] logpass = decodeAuthStr.split(":");
        String jwt = jwtProvider.generateJwt(logpass[0]);
        return ResponseEntity.ok()
                .header("access_token", jwt)
                .build();
    }

}
