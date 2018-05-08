package com.cmiot.api.exception;

/**
 * 客户端请求异常
 *
 * @author zhangst
 * @version 2018-5-3
 */
public class UserException extends RuntimeException {

    private int errorCode;
    public UserException(ApiStatus status) {
        this(status.getErrorInfo());
        this.errorCode = status.getErrorCode();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(ApiStatus status, String extraMessage) {
        this(status.getErrorCode() + ": " + extraMessage);
        this.errorCode = status.getErrorCode();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorInfo() {
        return getMessage();
    }
}
