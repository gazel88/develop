package net.srook.common.security.jwt;

import static net.srook.common.utils.ValidateUtils.isNullOrEmpty;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.srook.common.security.jwt.infra.JwtTokenExtractor;
import net.srook.common.security.jwt.infra.JwtTokenProvider;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtConfig {
    @Value("${security.jwt.token.expire-length.app-login:60000}")
    private Long validityInMilliseconds;
    @Value("${security.jwt.token.secret-key:}")
    private String secretKey;

    @Bean
    public JwtParser jwtParser(final Key key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    @Bean
    public Key privateKey() {
        if (isNullOrEmpty(this.secretKey)) {
            return Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }
        return Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(final Key key) {
        return new JwtTokenProvider(key, this.validityInMilliseconds);
    }

    @Bean
    public JwtTokenExtractor jwtTokenExtractor(final JwtParser jwtParser) {
        return new JwtTokenExtractor(jwtParser);
    }
}
