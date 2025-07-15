package dsm.hackaton._8.domain.email.service;

import dsm.hackaton._8.domain.email.domain.EmailVerification;
import dsm.hackaton._8.domain.email.domain.repository.EmailVerificationRepository;
import dsm.hackaton._8.domain.email.facade.EmailVerificationFacade;
import dsm.hackaton._8.domain.email.presentation.dto.requset.SendAuthCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendAuthCodeService {

    private final EmailService emailService;

    private final EmailVerificationRepository emailVerificationRepository;

    private final EmailVerificationFacade emailVerificationFacade;

    @Transactional
    public void execute(SendAuthCodeRequest request) {
        String email = request.getEmail();
        String authCode = generateAuthCode();

        if (emailVerificationRepository.existsByEmail(email)) {
            EmailVerification emailVerification = emailVerificationFacade.getEmailVerificationByEmail(email);
            emailVerification.updateAuthCode(authCode);
        } else {
            emailVerificationRepository.save(new EmailVerification(email, authCode));
        }

        emailService.sendEmail(email, authCode);
    }

    private String generateAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
