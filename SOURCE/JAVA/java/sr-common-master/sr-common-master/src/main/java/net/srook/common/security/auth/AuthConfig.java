package net.srook.common.security.auth;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.srook.common.security.auth.infra.AuthenticationPrincipalArgumentResolver;
import net.srook.common.security.auth.service.AuthService;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    private final AuthService authService;

    public AuthConfig(final AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver(this.authService));
    }
}
