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

    @PostMapping("/auth-code")
    public void sendAuthCode(@RequestBody @Valid SendAuthCodeRequest request) {
        sendAuthCodeService.execute(request);
    }

    @PostMapping("/auth-code/verify")
    public CheckAuthCodeResponse checkAuthCode(@RequestBody @Valid CheckAuthCodeRequest request) {
        return checkAuthCodeService.execute(request);
    }

    @PostMapping("/verify")
    public EmailVerifyResponse EmailVerify(@RequestBody @Valid EmailVerifyRequest request) {
        return checkUsedEmailService.execute(request);
    }
}
