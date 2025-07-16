package dsm.hackaton._8.domain.email.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String authCode;

    @Column(nullable = false)
    private Boolean isVerified = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public EmailVerification(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
        this.isVerified = false;
        this.createdAt = LocalDateTime.now();
    }

    public void updateAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void verify() {
        this.isVerified = true;
    }
}
