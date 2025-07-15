package dsm.hackaton._8.domain.email.domain.repository;

import dsm.hackaton._8.domain.email.domain.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepository extends JpaRepository<Long, EmailVerification> {
}
