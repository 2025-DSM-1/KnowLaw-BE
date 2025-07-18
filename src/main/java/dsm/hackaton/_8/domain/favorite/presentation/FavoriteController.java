package dsm.hackaton._8.domain.favorite.presentation;

import dsm.hackaton._8.domain.favorite.presentation.dto.response.QueryFavoriteLawResponse;
import dsm.hackaton._8.domain.favorite.service.AddFavoriteService;
import dsm.hackaton._8.domain.favorite.service.DeleteFavoriteService;
import dsm.hackaton._8.domain.favorite.service.QueryFavoriteLawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final AddFavoriteService addFavoriteService;

    private final DeleteFavoriteService deleteFavoriteService;

    private final QueryFavoriteLawService queryFavoriteLawService;

    @PostMapping("/{law-id}")
    public void addFavorite(@PathVariable("law-id") Long lawId) {
        addFavoriteService.execute(lawId);
    }

    @DeleteMapping("/{law-id}")
    public void deleteFavorite(@PathVariable("law-id") Long lawId) {
        deleteFavoriteService.deleteFavorite(lawId);
    }

    @GetMapping
    public QueryFavoriteLawResponse queryFavoriteLaw() {
        return queryFavoriteLawService.execute();
    }
}
