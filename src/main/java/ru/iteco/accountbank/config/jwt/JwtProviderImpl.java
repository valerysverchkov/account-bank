package ru.iteco.accountbank.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProviderImpl implements JwtProvider {

    private final int expiration;
    private final String secret;

    public JwtProviderImpl(@Value("${security.jwt.expiration}") int expiration,
                           @Value("${security.jwt.secret}") String secret) {
        this.expiration = expiration;
        this.secret = secret;
    }

    @Override
    public String generateJwt(String username) {
        Date expirationDate = Date.from(Instant.now().plusSeconds(expiration));
        return Jwts.builder()
                .claim(Claims.SUBJECT, username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public boolean validateJwt(String token) {
        try {
            Jwt jwt = Jwts.parser().setSigningKey(secret).parse(token);
            return jwt != null;
        } catch (Exception e) {
            log.error("Invalid JWT", e);
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

}
