package dsm.hackaton._8.domain.law.presentatoin.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryLawDetailResponse {

    private Long lawId;

    private String lawTitle;

    private List<LawSummaryContentResponse> lawSummaryContent;

    private String lawStatus;

    private LocalDate propositionDate;

    private String backgroundInfo;

    private String example;
}
