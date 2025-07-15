package dsm.hackaton._8.domain.email.domain.repository;

import dsm.hackaton._8.domain.email.domain.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    Optional<EmailVerification> findByEmail(String email);

    boolean existsByEmail(String email);
}
