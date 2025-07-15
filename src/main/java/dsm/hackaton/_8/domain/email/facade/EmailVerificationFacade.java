package dsm.hackaton._8.domain.email.facade;

import dsm.hackaton._8.domain.email.domain.EmailVerification;
import dsm.hackaton._8.domain.email.domain.repository.EmailVerificationRepository;
import dsm.hackaton._8.domain.email.exception.EmailNotVerifiedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailVerificationFacade {

    private final EmailVerificationRepository emailVerificationRepository;

    public EmailVerification getEmailVerificationByEmail(String email) {
        return emailVerificationRepository.findByEmail(email).orElseThrow(() -> EmailNotVerifiedException.EXCEPTION);
    }
}
