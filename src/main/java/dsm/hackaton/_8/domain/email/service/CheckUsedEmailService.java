package dsm.hackaton._8.domain.email.service;

import dsm.hackaton._8.domain.email.presentation.dto.request.EmailVerifyRequest;
import dsm.hackaton._8.domain.email.presentation.dto.response.EmailVerifyResponse;
import dsm.hackaton._8.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckUsedEmailService {

    private final UserRepository userRepository;

    public EmailVerifyResponse execute(EmailVerifyRequest request) {
        boolean exists = userRepository.existsByEmail(request.getEmail());

        return EmailVerifyResponse.builder().success(!exists).build();
    }
}