package dsm.hackaton._8.domain.law.presentatoin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawResponse {

    private Long lawId;
    private Long lawSerialNumber;
    private String lawTitle;
    private String lawContent;
    private String promulgationDate;
    private String enforcementDate;
}
