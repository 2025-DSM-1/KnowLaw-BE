package dsm.hackaton._8.domain.comment.exception;

import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;

public class UserVoteNotFoundException extends DSMException {

    public static final DSMException EXCEPTION = new UserVoteNotFoundException();

    private UserVoteNotFoundException() {
        super(ErrorCode.USER_VOTE_NOT_FOUND);
    }
}
