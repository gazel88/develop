package net.srook.common.security.auth.infra;

import java.util.List;
import java.util.Map;

import net.srook.common.code.ApplicationDvcd;
import net.srook.common.security.auth.dto.AuthenticationResponse;
import net.srook.common.security.auth.dto.AuthorizationResponse;

public interface SRAuth {

    AuthenticationResponse authentication(final String userId, final String rawPassword, final ApplicationDvcd applicationDvcd, final Long expireSeconds, final Map<String, Object> data);

    AuthorizationResponse authorization(final List<String> authorizationValues, final ApplicationDvcd requestApplication);

    AuthenticationResponse refreshToken(final List<String> authorizationValues, final Long expireSeconds);

    AuthenticationResponse makeToken(final Map<String, Object> data, final Long expireSeconds);
}
