package dsm.hackaton._8.domain.email.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class EmailSendFailedException extends DSMException {
    public static final DSMException EXCEPTION = new EmailSendFailedException();

    private EmailSendFailedException() {
        super(ErrorCode.EMAIL_SEND_FAILED);
    }
}
