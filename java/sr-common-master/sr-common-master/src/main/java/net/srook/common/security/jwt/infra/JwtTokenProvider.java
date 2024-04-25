package net.srook.common.security.jwt.infra;

import static net.srook.common.utils.DateUtils.toDate;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;

public class JwtTokenProvider {
    private final Key key;
    private final Long validityInMilliseconds;

    public JwtTokenProvider(final Key key, final Long validityInMilliseconds) {
        this.key = key;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createTokenBy(Map<String, Object> claims) {
        return createTokenBy(claims, this.validityInMilliseconds);
    }

    public String createTokenBy(Map<String, Object> claims, final Long validityInMilliseconds) {
        LocalDateTime now = LocalDateTime.now();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(toDate(now))
                .setExpiration(makeExpiredDate(now, validityInMilliseconds))
                .signWith(key)
                .compact();
    }

    private Date makeExpiredDate(LocalDateTime now, Long validityInMilliseconds) {
        return toDate(now.plus(validityInMilliseconds, ChronoUnit.MILLIS));
    }
}
