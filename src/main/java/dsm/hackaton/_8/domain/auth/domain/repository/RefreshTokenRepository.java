package dsm.hackaton._8.domain.auth.domain.repository;

import dsm.hackaton._8.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
