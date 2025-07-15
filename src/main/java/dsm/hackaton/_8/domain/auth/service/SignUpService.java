package dsm.hackaton._8.domain.auth.service;

import dsm.hackaton._8.domain.auth.exceptoin.UserAlreadyExistsException;
import dsm.hackaton._8.domain.auth.presentation.dto.request.SignUpRequest;
import dsm.hackaton._8.domain.email.exception.EmailNotVerifiedException;
import dsm.hackaton._8.domain.email.facade.EmailVerificationFacade;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final EmailVerificationFacade emailVerificationFacade;

    public void execute(SignUpRequest request) {
        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());

        if (userRepository.existsByEmail(email)) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        if (!emailVerificationFacade.getEmailVerificationByEmail(email).getIsVerified()) {
            throw EmailNotVerifiedException.EXCEPTION;
        }

        User user = User.builder()
                .name(request.getName())
                .email(email)
                .password(password)
                .build();

        userRepository.save(user);
    }
}
