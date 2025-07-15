package dsm.hackaton._8.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    BAD_REQUEST(400, "bad request"),
    INVALID_TOKEN_EXCEPTION(401, "invalid token exception"),
    USER_NOT_FOUND(404, "user not found"),
    INTERNAL_SERVER_ERROR(500, "server error");

    private final int statusCode;
    private final String message;
}
