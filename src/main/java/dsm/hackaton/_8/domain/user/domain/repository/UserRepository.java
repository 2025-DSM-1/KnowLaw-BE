package dsm.hackaton._8.domain.user.domain.repository;

import dsm.hackaton._8.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {

    Optional<User> findByEmail(String email);
}
