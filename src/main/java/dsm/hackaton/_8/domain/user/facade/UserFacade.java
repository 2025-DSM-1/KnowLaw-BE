package dsm.hackaton._8.domain.user.facade;

import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.domain.repository.UserRepository;
import dsm.hackaton._8.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}