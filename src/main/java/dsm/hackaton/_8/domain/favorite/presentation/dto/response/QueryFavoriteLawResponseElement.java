package dsm.hackaton._8.domain.favorite.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryFavoriteLawResponseElement {

    private Long lawId;

    private String lawTitle;
}
