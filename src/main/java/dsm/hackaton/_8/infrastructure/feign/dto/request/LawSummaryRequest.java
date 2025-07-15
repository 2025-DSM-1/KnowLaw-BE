package dsm.hackaton._8.infrastructure.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LawSummaryRequest {
    private String lawModifiedContent;
}
