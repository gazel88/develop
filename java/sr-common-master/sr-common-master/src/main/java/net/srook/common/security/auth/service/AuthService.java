package net.srook.common.security.auth.service;

import java.util.List;

public interface AuthService {
    Object extractToken(final List<String> httpHeaderValues);
}
