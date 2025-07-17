package dsm.hackaton._8.domain.favorite.service;

import dsm.hackaton._8.domain.favorite.domain.Favorite;
import dsm.hackaton._8.domain.favorite.domain.repository.FavoriteRepository;
import dsm.hackaton._8.domain.favorite.exception.AlreadyFavoriteException;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.law.domain.repository.LawRepository;
import dsm.hackaton._8.domain.law.exception.LawNotFoundException;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddFavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final UserFacade userFacade;

    private final LawRepository lawRepository;

    @Transactional
    public void execute(Long lawId) {
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);
        User user = userFacade.getCurrentUser();

        boolean alreadyFavorite = favoriteRepository.existsByUserAndLaw(user, law);
        if (alreadyFavorite) {
            throw AlreadyFavoriteException.EXCEPTION;
        }

        Favorite favorite = Favorite.builder()
                .user(user)
                .law(law)
                .build();

        favoriteRepository.save(favorite);
    }
}
