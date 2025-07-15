package dsm.hackaton._8.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DSMException extends RuntimeException {

    private final ErrorCode errorCode;
}
