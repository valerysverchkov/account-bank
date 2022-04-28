package ru.iteco.accountbank.config.jwt;

public interface JwtProvider {

    String generateJwt(String username);
    boolean validateJwt(String token);
    String getUsername(String token);

}
