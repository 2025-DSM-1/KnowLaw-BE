package dsm.hackaton._8.domain.law.presentatoin.dto.response;

import lombok.Builder;

@Builder
public class QueryLawLogicResponse {

    private Long lawId;

    private String agreeLogic;

    private String disagreeLogic;
}
