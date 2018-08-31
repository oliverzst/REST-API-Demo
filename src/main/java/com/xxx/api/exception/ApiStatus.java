package com.xxx.api.exception;

public enum ApiStatus {

    //请在下面添加状态码
    SUCCESS(0, "success"),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(1, "internal server error"),

    //客户端请求相关错误
    NAME_EXIST(2001, "name exists"),
    NAME_NOT_EXIST(2002, "name not exists"),
    USERNAME_OR_PASSWORD_ERROR(2003, "username or password is error"),
    LACK_PARAMETER(2004, "lack necessary parameter"),
    INVALID_PARAMETER(2005, "invalid parameter"),
    NO_OPERATION_PERMISSION(2006, "no operation permission"),
    NOT_SUCH_REQUEST(2007, "no such request is existed"),
    DATETIME_PARSE_ERROR(2008, "datetime parse error");

    private int errorCode;
    private String errorInfo;
    ApiStatus(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
