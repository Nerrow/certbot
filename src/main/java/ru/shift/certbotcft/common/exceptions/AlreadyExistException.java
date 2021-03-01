package ru.shift.certbotcft.common.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

public class AlreadyExistException extends DataAccessException {

    private final HttpStatus errorHttpStatus = HttpStatus.BAD_REQUEST;

    public AlreadyExistException(String msg) {
        super(msg);
    }

    public HttpStatus getErrorHttpStatus() {
        return errorHttpStatus;
    }

    public AlreadyExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}