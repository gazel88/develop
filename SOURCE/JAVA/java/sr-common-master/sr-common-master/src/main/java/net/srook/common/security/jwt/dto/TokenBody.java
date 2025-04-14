package net.srook.common.security.jwt.dto;

import static java.util.Optional.ofNullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.srook.common.exception.SRNotFoundTokenBodyElementException;
import net.srook.common.utils.TypeUtils;

import lombok.ToString;

@ToString
public class TokenBody {
    private Map<String, Object> claims;

    public TokenBody() {
        this.claims = new HashMap<>();
    }

    private TokenBody(final Map<String, Object> claims) {
        this.claims = claims;
    }

    public static TokenBody of(final Map<String, Object> claims) {
        return new TokenBody(Collections.unmodifiableMap(claims));
    }

    public void put(final String key, final Object value) {
        this.claims.put(key, value);
    }

    public Object get(final String key) {
        return ofNullable(this.claims.get(key))
                .orElseThrow(() -> new SRNotFoundTokenBodyElementException(key + "요소를 찾을 수 없습니다."));
    }

    public Object getOrNull(final String key) {
        return this.claims.get(key);
    }

    public Long getLong(String key) {
        return TypeUtils.toLong(this.claims.get(key));
    }

    public String getString(String key) {
        return TypeUtils.toString(this.get(key));
    }

    public String getStringOrEmpty(String key) {
        return TypeUtils.toString(ofNullable(this.getOrNull(key))
                .orElse(""));
    }

    public Map<String, Object> toMap() {
        return Collections.unmodifiableMap(this.claims);
    }
}
