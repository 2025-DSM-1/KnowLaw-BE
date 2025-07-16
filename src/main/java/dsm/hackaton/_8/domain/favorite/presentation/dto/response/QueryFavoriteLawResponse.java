package dsm.hackaton._8.domain.favorite.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryFavoriteLawResponse {

    private List<QueryFavoriteLawResponseElement> laws;
}
