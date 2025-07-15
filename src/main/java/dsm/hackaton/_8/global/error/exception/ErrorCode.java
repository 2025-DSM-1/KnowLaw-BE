package dsm.hackaton._8.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    BAD_REQUEST(400, "bad request"),
    EMAIL_NOT_VERIFIED(400, "email not verified"),
    INVALID_TOKEN_EXCEPTION(401, "invalid token exception"),
    EXPIRED_TOKEN_EXCEPTION(401,"expired token exception"),
    PASSWORD_NOT_VALID(401, "password not valid"),
    REFRESH_TOKEN_NOT_FOUND(404, "refresh token not found"),
    LAW_NOT_FOUND(404, "law not found"),
    USER_NOT_FOUND(404, "user not found"),
    USER_ALREADY_EXISTS(409, "user already exists"),
    INTERNAL_SERVER_ERROR(500, "server error");

    private final int statusCode;
    private final String message;
}
