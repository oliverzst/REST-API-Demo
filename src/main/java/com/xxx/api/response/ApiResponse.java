package com.xxx.api.response;

import com.xxx.api.exception.ApiStatus;
import com.xxx.api.exception.UserException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;

public class ApiResponse {

    @NonNull
    private int errorCode;
    @NonNull
    private String errorInfo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cursor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ApiResponse(Object data) {
        this.errorCode = ApiStatus.SUCCESS.getErrorCode();
        this.errorInfo = ApiStatus.SUCCESS.getErrorInfo();
        this.data = data;
    }

    public ApiResponse(Object data, String cursor) {
        this.errorCode = ApiStatus.SUCCESS.getErrorCode();
        this.errorInfo = ApiStatus.SUCCESS.getErrorInfo();
        this.cursor = cursor;
        this.data = data;
    }

    public ApiResponse(ApiStatus status) {
        this.errorCode = status.getErrorCode();
        this.errorInfo = status.getErrorInfo();
    }

    public ApiResponse(UserException e) {
        this.errorCode = e.getErrorCode();
        this.errorInfo = e.getErrorInfo();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
