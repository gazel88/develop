package net.srook.common.security.auth.dto;

import java.util.Map;

import net.srook.common.code.ApplicationDvcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private String id;
    private String rawPassword;
    private ApplicationDvcd applicationDvcd;
    private Long expireSeconds;
    private Map<String, Object> data;
}
