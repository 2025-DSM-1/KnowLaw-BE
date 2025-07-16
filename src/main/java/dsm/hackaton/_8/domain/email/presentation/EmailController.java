package dsm.hackaton._8.domain.email.presentation;

import dsm.hackaton._8.domain.email.presentation.dto.request.CheckAuthCodeRequest;
import dsm.hackaton._8.domain.email.presentation.dto.request.EmailVerifyRequest;
import dsm.hackaton._8.domain.email.presentation.dto.request.SendAuthCodeRequest;
import dsm.hackaton._8.domain.email.presentation.dto.response.CheckAuthCodeResponse;
import dsm.hackaton._8.domain.email.presentation.dto.response.EmailVerifyResponse;
import dsm.hackaton._8.domain.email.service.CheckAuthCodeService;
import dsm.hackaton._8.domain.email.service.CheckUsedEmailService;
import dsm.hackaton._8.domain.email.service.SendAuthCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final SendAuthCodeService sendAuthCodeService;

    private final CheckAuthCodeService checkAuthCodeService;

    private final CheckUsedEmailService checkUsedEmailService;

    @PostMapping("/send-authcode")
    public void sendAuthCode(@RequestBody @Valid SendAuthCodeRequest sendAuthCodeRequest) {
        sendAuthCodeService.execute(sendAuthCodeRequest);
    }

    @PostMapping("/check-authcode")
    public CheckAuthCodeResponse checkAuthCode(@RequestBody @Valid CheckAuthCodeRequest checkAuthCodeRequest) {
        return checkAuthCodeService.execute(checkAuthCodeRequest);
    }

    @PostMapping("/email-verify")
    public EmailVerifyResponse EmailVerify(@RequestBody @Valid EmailVerifyRequest emailVerifyRequest) {
        return checkUsedEmailService.execute(emailVerifyRequest);
    }
}
