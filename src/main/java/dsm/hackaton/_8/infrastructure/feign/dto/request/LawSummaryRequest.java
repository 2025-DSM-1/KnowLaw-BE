package dsm.hackaton._8.infrastructure.feign.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LawSummaryRequest {

    @NotBlank(message = "lawModifiedContent는 필수입니다.")
    private String lawModifiedContent;
}
