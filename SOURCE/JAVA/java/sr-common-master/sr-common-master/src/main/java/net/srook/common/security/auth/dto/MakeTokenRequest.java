
package net.srook.common.security.auth.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeTokenRequest {
    private Map<String, Object> data;
    private Long expireSeconds;
}
