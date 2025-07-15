package dsm.hackaton._8.infrastructure.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LawSummaryResponse {
    private String lawSummary;
    private String backgroundInfo;
    private String example;
    private String agreeLogic;
    private String disagreeLogic;
}
