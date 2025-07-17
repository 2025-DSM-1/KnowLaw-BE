package dsm.hackaton._8.domain.vote.domain.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class LawAlreadyVoteException extends DSMException {

    public static final DSMException EXCEPTION = new LawAlreadyVoteException();

    private LawAlreadyVoteException() {
        super(ErrorCode.LAW_ALREADY_VOTE);
    }
}
