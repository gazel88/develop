package net.srook.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class TokenAuthRequest {
    private String token;

    protected TokenAuthRequest() {
    }

    public TokenAuthRequest(final String token) {
        this.token = token;
    }
}
