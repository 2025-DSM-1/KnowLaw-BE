package dsm.hackaton._8.domain.law.presentatoin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawSummaryContentResponse {
    private String summaryElement;
}

