package dsm.hackaton._8.domain.law.presentatoin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QueryLawLogicResponse {

    private Long lawId;

    private String agreeLogic;

    private String disagreeLogic;
}
