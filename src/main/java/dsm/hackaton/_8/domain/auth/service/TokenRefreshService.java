package dsm.hackaton._8.domain.auth.service;

import dsm.hackaton._8.domain.auth.domain.RefreshToken;
import dsm.hackaton._8.domain.auth.domain.repository.RefreshTokenRepository;
import dsm.hackaton._8.domain.auth.exceptoin.RefreshTokenNotFoundException;
import dsm.hackaton._8.domain.auth.presentation.dto.response.TokenResponse;
import dsm.hackaton._8.global.security.jwt.JwtProperties;
import dsm.hackaton._8.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        TokenResponse tokens = jwtProvider.generateToken(refreshToken.getEmail());
        refreshToken.updateToken(tokens.getRefreshToken(), jwtProperties.getRefreshExp());

        return tokens;
    }
}
