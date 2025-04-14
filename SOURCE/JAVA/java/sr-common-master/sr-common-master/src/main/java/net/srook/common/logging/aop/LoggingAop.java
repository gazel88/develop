package net.srook.common.logging.aop;

import static net.srook.common.utils.ApiUtils.getClientIP;
import static net.srook.common.utils.ApiUtils.getRequestApiUri;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAop {
    @Before("execution(* kr.co.kumsung..*Controller*.*(..))")
    public void logRequestUri(final JoinPoint joinPoint) {
        try {
            loggingApiRequestInfo(joinPoint);
        } catch (Exception e) {
            log.warn("AOP Logging Exception: {}", e.getMessage(), e);
        }
    }

    @Around("execution(* com.fasterxml.jackson.databind.ObjectMapper.readValue(..))")
    public Object logBeforeService(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            loggingApiRequestInfo(joinPoint);
            throw e;
        }
    }

    private void loggingApiRequestInfo(final JoinPoint joinPoint) {
        try {
            final HttpServletRequest request = getHttpServletRequest();
            log.info("[APIRequest::header] client-ip=\"{}\", method=\"{}\", uri=\"{}\"", getClientIP(request), request.getMethod(),
                    getRequestApiUri(request));
            log.debug("[APIRequest::data] request-data=\"{}\"", Arrays.toString(joinPoint.getArgs()));
        } catch (IllegalStateException e) {
            log.info("[APIRequest::header] No HttpServletRequest.");
        }
    }

    private HttpServletRequest getHttpServletRequest() throws IllegalStateException {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
