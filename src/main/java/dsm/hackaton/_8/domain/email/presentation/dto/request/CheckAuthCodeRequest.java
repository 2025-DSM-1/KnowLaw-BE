package dsm.hackaton._8.domain.email.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CheckAuthCodeRequest {

    @NotBlank(message = "email은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "email 형식")
    private String email;

    @NotBlank(message = "공백 또는 띄어쓰기를 허용하지 않습니다.")
    private String authCode;
}
