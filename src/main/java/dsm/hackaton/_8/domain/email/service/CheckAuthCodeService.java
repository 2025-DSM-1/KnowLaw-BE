package dsm.hackaton._8.domain.email.service;

import dsm.hackaton._8.domain.email.domain.EmailVerification;
import dsm.hackaton._8.domain.email.facade.EmailVerificationFacade;
import dsm.hackaton._8.domain.email.presentation.dto.requset.CheckAuthCodeRequest;
import dsm.hackaton._8.domain.email.presentation.dto.response.CheckAuthCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckAuthCodeService {

    private final EmailVerificationFacade emailVerificationFacade;

    @Transactional
    public CheckAuthCodeResponse execute(CheckAuthCodeRequest request) {
        EmailVerification emailVerification = emailVerificationFacade.getEmailVerificationByEmail(request.getEmail());

        if (emailVerification.getAuthCode().equals(request.getAuthCode())) {
            emailVerification.verify();
            return CheckAuthCodeResponse.builder().isVerified(true).build();
        }

        return CheckAuthCodeResponse.builder().isVerified(false).build();
    }
}
