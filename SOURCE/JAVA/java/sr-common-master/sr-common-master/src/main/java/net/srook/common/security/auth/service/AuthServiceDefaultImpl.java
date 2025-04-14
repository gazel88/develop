package net.srook.common.security.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceDefaultImpl implements AuthService {
    @Override
    public Object extractToken(final List<String> httpHeaderValues) {
        log.info("AuthServiceDefaultImpl");
        return null;
    }
}
