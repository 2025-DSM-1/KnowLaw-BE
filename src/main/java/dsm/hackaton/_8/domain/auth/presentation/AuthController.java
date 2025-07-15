package dsm.hackaton._8.domain.auth.presentation;

import dsm.hackaton._8.domain.auth.presentation.dto.request.LoginRequest;
import dsm.hackaton._8.domain.auth.presentation.dto.request.SignUpRequest;
import dsm.hackaton._8.domain.auth.presentation.dto.response.TokenResponse;
import dsm.hackaton._8.domain.auth.service.LoginService;
import dsm.hackaton._8.domain.auth.service.SignUpService;
import dsm.hackaton._8.domain.auth.service.TokenRefreshService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignUpService signUpService;

    private final LoginService loginService;

    private final TokenRefreshService tokenRefreshService;

    @PostMapping("/sign-up")
    public void signup(@RequestBody @Valid SignUpRequest request) {
        signUpService.execute(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

    @PostMapping("/refresh")
    public TokenResponse tokenRefresh(@RequestHeader("refresh-token") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }
}
