package net.srook.common.security.jwt.infra;

import static java.util.Optional.ofNullable;
import static net.srook.common.utils.DateUtils.toDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import net.srook.common.exception.SRAuthorizationException;
import net.srook.common.exception.SRNotSignedTokenException;
import net.srook.common.security.jwt.dto.TokenBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;

public class JwtTokenExtractor {
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_TYPE = "Bearer";
    public static final String ACCESS_TOKEN_TYPE = JwtTokenExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";
    private final JwtParser jwtParser;

    public JwtTokenExtractor(final JwtParser jwtParser) {
        this.jwtParser = jwtParser;
    }

    public Optional<TokenBody> extractTokenBody(final List<String> authorizationValues) {
        return authorizationValues.stream()
                .filter(this::isBearerType)
                .findFirst()
                .map(this::getTokenBy)
                .map(this::toTokenBodyBy);
    }

    public Optional<TokenBody> extractTokenBody(final String authorizationValue) {
        return ofNullable(authorizationValue)
                .filter(this::isBearerType)
                .map(this::getTokenBy)
                .map(this::toTokenBodyBy);
    }

    public TokenBody toTokenBodyBy(String token) {
        validateToken(token);
        final Claims body = this.jwtParser
                .parseClaimsJws(token)
                .getBody();
        return TokenBody.of(body);
    }

    public void validateToken(final String token) {
        validateTokenIsSigned(token);
        validateTokenIsBeforeExpired(token);
    }

    public String getTokenBy(String authorizationValue) {
        String authHeaderValue = getValueWithoutTokenType(authorizationValue);
        int commaIndex = authHeaderValue.indexOf(",");
        if (commaIndex > 0) {
            authHeaderValue = authHeaderValue.substring(0, commaIndex);
        }
        return authHeaderValue;
    }

    public String findBearerToken(final List<String> httpHeaderValues) {
        return httpHeaderValues.stream()
                .filter(this::isBearerType)
                .findFirst()
                .orElseThrow(() -> new SRAuthorizationException("토큰이 존재하지 않습니다."));
    }

    public boolean isBearerType(String value) {
        return value.toLowerCase()
                .startsWith(TOKEN_TYPE.toLowerCase());
    }

    private void validateTokenIsSigned(String token) {
        if (!this.jwtParser.isSigned(token)) {
            throw new SRNotSignedTokenException("서명되지 않은 토큰입니다.");
        }
    }

    private void validateTokenIsBeforeExpired(String token) {
        if (isExpiredToken(token)) {
            throw new SRAuthorizationException("토큰의 유효기간이 만료되었습니다.");
        }
    }

    private boolean isExpiredToken(String token) {
        try {
            return this.jwtParser
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(toDate(LocalDateTime.now()));
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        } catch (Exception e) {
            throw new SRAuthorizationException("토큰 파싱에 실패했습니다.", e);
        }
    }

    private String getValueWithoutTokenType(String value) {
        return value.substring(TOKEN_TYPE.length()).trim();
    }
}
