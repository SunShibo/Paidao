package com.solland.paidao.entity.dto;

/**
 * Created by sunshibo on 2016/3/16.
 */
public class ResultDTOBuilder {

    public ResultDTOBuilder() {
    }

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> instance = getInstance("", "", true, data);
        return instance;
    }

    public static ResultDTO failure(String errCode, String errMsg) {
        ResultDTO<String> instance = getInstance(errCode, errMsg, false, "");
        return instance;
    }

    public static <T> ResultDTO<T> getInstance(String errCode, String errMsg, boolean success, T data) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setData(data);
        resultDTO.setErrCode(errCode);
        resultDTO.setErrMsg(errMsg);
        resultDTO.setSuccess(success);
        return resultDTO;
    }
}
