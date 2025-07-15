package dsm.hackaton._8.domain.law.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class LawNotFoundException extends DSMException {

    public static final DSMException EXCEPTION = new LawNotFoundException();

    private LawNotFoundException() {
        super(ErrorCode.LAW_NOT_FOUND);
    }
}
