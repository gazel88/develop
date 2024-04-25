package net.srook.common.security.auth.infra;

import static net.srook.common.utils.ApiUtils.fromUriString;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.srook.common.apiclient.ApiClient;
import net.srook.common.apiclient.ApiClientFactory;
import net.srook.common.code.ApplicationDvcd;
import net.srook.common.exception.SRAuthorizationException;
import net.srook.common.security.auth.dto.*;
import net.srook.common.security.jwt.infra.JwtTokenExtractor;

@Component
public class SRAuthDefaultImpl implements SRAuth {
    private static final String AUTH_SERVER_API_V1_AUTHENTICATION = "/api/auth/v1/authentication";
    private static final String AUTH_SERVER_API_V1_AUTHORIZATION = "/api/auth/v1/authorization";
    private static final String AUTH_SERVER_API_V1_REFRESH = "/api/auth/v1/refresh";
    private static final String AUTH_SERVER_API_V1_TOKEN = "/api/auth/v1/token";
    private final ApiClient authServerApiClient;
    private final JwtTokenExtractor jwtTokenExtractor;

    public SRAuthDefaultImpl(final ApiClientFactory factory, final JwtTokenExtractor jwtTokenExtractor) {
        this.authServerApiClient = factory.authServerApi();
        this.jwtTokenExtractor = jwtTokenExtractor;
    }

    @Override
    public AuthenticationResponse authentication(final String userId, final String rawPassword, final ApplicationDvcd applicationDvcd,
                                                 final Long expireSeconds, final Map<String, Object> data) {
        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(userId, rawPassword, applicationDvcd, expireSeconds, data);
        final AuthenticationResponse response = new AuthenticationResponse();
        this.authServerApiClient.postMono(fromUriString(AUTH_SERVER_API_V1_AUTHENTICATION).toUriString(),
                        authenticationRequest, AuthenticationResponse.class)
                .blockOptional()
                .ifPresent(loginResponse -> response.setToken(loginResponse.getToken()));
        return response;
    }

    @Override
    public AuthorizationResponse authorization(final List<String> authorizationValues, final ApplicationDvcd requestApplication) {
        final String token = this.jwtTokenExtractor.findBearerToken(authorizationValues);
        final String url = fromUriString(AUTH_SERVER_API_V1_AUTHORIZATION).queryParam("token", token).toUriString();
        return this.authServerApiClient.findOneMono(url, AuthorizationResponse.class)
                .blockOptional()
                .orElseThrow(() -> new SRAuthorizationException("인가 요청 결과가 없습니다."));
    }

    @Override
    public AuthenticationResponse refreshToken(final List<String> authorizationValues, final Long expireSeconds) {
        final String token = this.jwtTokenExtractor.findBearerToken(authorizationValues);
        final AuthenticationResponse response = new AuthenticationResponse();
        this.authServerApiClient.postMono(AUTH_SERVER_API_V1_REFRESH, new RefreshTokenRequest(token, expireSeconds),
                        RefreshTokenResponse.class)
                .blockOptional()
                .ifPresent(refreshTokenResponse -> response.setToken(refreshTokenResponse.getRefreshToken()));
        return response;
    }

    @Override
    public AuthenticationResponse makeToken(final Map<String, Object> data, final Long expireSeconds) {
        return this.authServerApiClient.postMono(AUTH_SERVER_API_V1_TOKEN, new MakeTokenRequest(data, expireSeconds),
                        AuthenticationResponse.class)
                .blockOptional()
                .orElseThrow(() -> new SRAuthorizationException("토큰 생성에 실패했습니다."));
    }
}
