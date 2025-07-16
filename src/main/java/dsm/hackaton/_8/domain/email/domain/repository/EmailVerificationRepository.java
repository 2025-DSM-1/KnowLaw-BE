package dsm.hackaton._8.domain.email.domain.repository;

import dsm.hackaton._8.domain.email.domain.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    Optional<EmailVerification> findByEmail(String email);

    boolean existsByEmail(String email);
}
