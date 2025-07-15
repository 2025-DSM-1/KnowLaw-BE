package dsm.hackaton._8.domain.auth.exceptoin;


import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class PasswordNotValidException extends DSMException {

    public static DSMException EXCEPTION = new PasswordNotValidException();

    private PasswordNotValidException() {
        super(ErrorCode.PASSWORD_NOT_VALID);
    }
}
