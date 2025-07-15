package dsm.hackaton._8.domain.email.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckAuthCodeResponse {

    private Boolean isVerified;
}