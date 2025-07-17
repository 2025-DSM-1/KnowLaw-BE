package dsm.hackaton._8.domain.favorite.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class AlreadyFavoriteException extends DSMException {

    public static final DSMException EXCEPTION = new AlreadyFavoriteException();

    private AlreadyFavoriteException() {
        super(ErrorCode.ALREADY_FAVORITE);
    }
}
