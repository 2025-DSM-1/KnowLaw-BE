package dsm.hackaton._8.domain.user.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class UserNotFoundException extends DSMException {

    public static final DSMException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}