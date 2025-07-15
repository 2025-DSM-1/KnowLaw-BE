package dsm.hackaton._8.domain.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<Long, RefreshToken> {
}
