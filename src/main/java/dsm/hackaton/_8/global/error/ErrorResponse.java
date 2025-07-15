package dsm.hackaton._8.global.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private Integer status;

    public static ErrorResponse of(String message,int status) {
        return ErrorResponse.builder()
                .message(message)
                .status(status)
                .build();
    }
}
