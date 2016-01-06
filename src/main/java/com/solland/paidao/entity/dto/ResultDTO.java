package com.solland.paidao.entity.dto;

import java.io.Serializable;

/**
 * @author Shibo Sun
 * 2016/1/4.
 */
@SuppressWarnings("serial")
public class ResultDTO<T> implements Serializable {
    public static final String ERRCODE_NONE = "0";
    public static final String ERRCODE_CONSTRAINT_VIOLATION = "-1";
    public static final String ERRCODE_PROCESSING = "-2";
    public static final String ERRCODE_UNKOWN = "-9";
    private boolean success = true;
    protected String errCode = "0";
    protected String errMsg;
    protected T data;

    public ResultDTO() {
    }

    public ResultDTO(T data) {
        this.data = data ;
    }

    public ResultDTO (boolean success ,String errCode , String errMsg) {
        this.success = success ;
        this.errCode = errCode ;
        this.errMsg = errMsg ;
    }

    public ResultDTO (T data, boolean success, String errCode, String errMsg) {
    	this.data = data ;
    	this.success = success ;
    	this.errCode = errCode ;
    	this.errMsg = errMsg ;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

