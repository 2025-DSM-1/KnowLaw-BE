package dsm.hackaton._8.domain.auth.exceptoin;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class ExpiredTokenException extends DSMException {

    public static final DSMException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN_EXCEPTION);
    }
}
