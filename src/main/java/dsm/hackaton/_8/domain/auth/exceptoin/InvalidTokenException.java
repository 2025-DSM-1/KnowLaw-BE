package dsm.hackaton._8.domain.auth.exceptoin;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class InvalidTokenException extends DSMException {

    public static final DSMException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN_EXCEPTION);
    }
}