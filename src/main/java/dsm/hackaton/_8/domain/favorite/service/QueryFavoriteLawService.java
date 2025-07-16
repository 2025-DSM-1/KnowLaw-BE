package dsm.hackaton._8.domain.favorite.service;

import dsm.hackaton._8.domain.favorite.domain.Favorite;
import dsm.hackaton._8.domain.favorite.domain.repository.FavoriteRepository;
import dsm.hackaton._8.domain.favorite.presentation.dto.response.QueryFavoriteLawResponse;
import dsm.hackaton._8.domain.favorite.presentation.dto.response.QueryFavoriteLawResponseElement;
import dsm.hackaton._8.domain.law.domain.Law;
import dsm.hackaton._8.domain.user.domain.User;
import dsm.hackaton._8.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryFavoriteLawService {

    private final FavoriteRepository favoriteRepository;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryFavoriteLawResponse execute() {
        User user = userFacade.getCurrentUser();

        List<Favorite> favorites = favoriteRepository.findAllByUser(user);

        List<QueryFavoriteLawResponseElement> elements =  favorites.stream()
                .map(favorite -> {
                    Law law = favorite.getLaw();
                    return new QueryFavoriteLawResponseElement(law.getId(), law.getLawTitle());
                }).toList();

        return new QueryFavoriteLawResponse(elements);
    }
}
