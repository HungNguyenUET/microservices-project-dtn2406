package vti.dtn.auth_service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
    private int status;
    private String message;
    private int userId;
    private String accessToken;
    private String refreshToken;
}
