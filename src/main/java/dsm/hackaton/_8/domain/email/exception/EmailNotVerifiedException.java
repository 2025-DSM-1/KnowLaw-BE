package dsm.hackaton._8.domain.email.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class EmailNotVerifiedException extends DSMException {

    public static DSMException EXCEPTION = new EmailNotVerifiedException();

    private EmailNotVerifiedException() {
        super(ErrorCode.EMAIL_NOT_VERIFIED);
    }
}