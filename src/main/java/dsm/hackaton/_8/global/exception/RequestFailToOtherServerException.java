package dsm.hackaton._8.global.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class RequestFailToOtherServerException extends DSMException {

    public static final DSMException EXCEPTION = new RequestFailToOtherServerException();

    private RequestFailToOtherServerException() {
        super(ErrorCode.REQUEST_FAIL_TO_OTHER_SERVER);
    }
}
