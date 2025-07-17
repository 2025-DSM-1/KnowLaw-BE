package dsm.hackaton._8.domain.favorite.service;

import dsm.hackaton._8.domain.favorite.domain.Favorite;
import dsm.hackaton._8.domain.favorite.domain.repository.FavoriteRepository;
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
public class DeleteFavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final LawRepository lawRepository;
    private final UserFacade userFacade;

    @Transactional
    public void deleteFavorite(Long lawId) {
        User user = userFacade.getCurrentUser();
        Law law = lawRepository.findById(lawId).orElseThrow(() -> LawNotFoundException.EXCEPTION);

        Favorite favorite = favoriteRepository.findByLawAndUser(law, user);
        favoriteRepository.delete(favorite);
    }
}
