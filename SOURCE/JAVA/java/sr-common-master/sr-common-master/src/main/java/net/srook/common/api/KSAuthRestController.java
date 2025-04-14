package net.srook.common.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.srook.common.dto.ErrorResponse;
import net.srook.common.dto.TokenAuthRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface KSAuthRestController extends KSRestController {
    String API_TOKEN_AUTH = "/token-auth";
    @Operation(summary = "토큰 유효성 인증", description = "토큰이 유효한지 인증하는 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증된 토큰",
                    content = @Content(
                            schema = @Schema(implementation = HttpStatus.class),
                            mediaType = APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "400", description = "인증되지 않은 토큰",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "500", description = "서버 오류(인증 실패)",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = APPLICATION_JSON_VALUE
                    )
            )
    })
    @Tag(name = "권한")
    @PostMapping(API_TOKEN_AUTH)
    ResponseEntity<HttpStatus> verifyTokenAuthentication(@RequestBody final TokenAuthRequest tokenAuthRequest);
}
