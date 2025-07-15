package dsm.hackaton._8.domain.law.presentatoin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawResponse {

    private Long lawId;
    private int lawSerialNumber;
    private String lawTitle;
    private String lawContent;
    private LocalDate promulgationDate;
    private LocalDate enforcementDate;
}
