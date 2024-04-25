package net.srook.common.security.auth.dto;

import java.util.Map;

import net.srook.common.code.ApplicationDvcd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationResponse {
    private String userId;
    private String userName;
    private String memberNo;
    private ApplicationDvcd applicationDvcd;
    private Map<String, Object> data;
}
