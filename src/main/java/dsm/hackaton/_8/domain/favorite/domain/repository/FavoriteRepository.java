package dsm.hackaton._8.domain.favorite.domain.repository;

import dsm.hackaton._8.domain.favorite.domain.Favorite;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Favorite findByLawAndUser(Law law, User user);

    List<Favorite> findAllByUser(User user);

    List<Favorite> findAllByLaw(Law law);
}
