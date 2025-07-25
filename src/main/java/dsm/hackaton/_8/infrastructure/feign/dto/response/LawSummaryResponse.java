package dsm.hackaton._8.infrastructure.feign.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dsm.hackaton._8.domain.law.presentatoin.dto.response.LawSummaryContentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LawSummaryResponse {
    private String lawContent;
    private List<LawSummaryContentResponse> lawSummaryContent;
    private String backgroundInfo;
    private String example;
    private String agreeLogic;
    private String disagreeLogic;
}
