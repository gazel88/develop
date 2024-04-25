package net.srook.common.security.auth.infra;

import static net.srook.common.security.jwt.infra.JwtTokenExtractor.AUTHORIZATION;
import static net.srook.common.utils.HttpHeadersUtils.getHttpHeaderValues;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import net.srook.common.security.auth.service.AuthService;
import net.srook.common.security.jwt.annotation.AuthenticationPrincipal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
    private final AuthService authService;

    public AuthenticationPrincipalArgumentResolver(final AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return this.authService.extractToken(getHttpHeaderValues(nativeRequest, AUTHORIZATION));
    }
}
