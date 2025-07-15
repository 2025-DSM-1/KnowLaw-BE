package dsm.hackaton._8.domain.auth.service;

import dsm.hackaton._8.domain.auth.presentation.dto.response.TokenResponse;
import dsm.hackaton._8.global.security.jwt.TokenRefreshUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final TokenRefreshUtil tokenRefreshUtil;

    public TokenResponse execute(String refreshToken) {
        return tokenRefreshUtil.tokenRefresh(refreshToken);
    }
}
