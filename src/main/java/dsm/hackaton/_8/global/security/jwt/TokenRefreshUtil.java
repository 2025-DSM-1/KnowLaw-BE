package dsm.hackaton._8.global.security.jwt;

import dsm.hackaton._8.domain.auth.domain.RefreshToken;
import dsm.hackaton._8.domain.auth.domain.repository.RefreshTokenRepository;
import dsm.hackaton._8.domain.auth.exceptoin.InvalidTokenException;
import dsm.hackaton._8.domain.auth.exceptoin.RefreshTokenNotFoundException;
import dsm.hackaton._8.domain.auth.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenRefreshUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtProvider jwtProvider;

    public TokenResponse tokenRefresh(String token) {
        if (jwtProvider.isNotRefreshToken(token)) {
            throw InvalidTokenException.EXCEPTION;
        }

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String email = refreshToken.getEmail();

        return jwtProvider.generateToken(email);
    }
}
