package dsm.hackaton._8.domain.auth.service;

import dsm.hackaton._8.domain.auth.exceptoin.PasswordNotValidException;
import dsm.hackaton._8.domain.auth.presentation.dto.request.LoginRequest;
import dsm.hackaton._8.domain.auth.presentation.dto.response.TokenResponse;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import dsm.hackaton._8.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserFacade userFacade;

    public TokenResponse execute(LoginRequest request) {
        User user = userFacade.getUserByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordNotValidException.EXCEPTION;
        }

        return jwtProvider.generateToken(user.getEmail());
    }
}
