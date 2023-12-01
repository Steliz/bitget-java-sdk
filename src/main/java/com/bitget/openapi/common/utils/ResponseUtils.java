package com.bitget.openapi.common.utils;

import com.bitget.openapi.common.exception.BitgetApiException;
import com.bitget.openapi.dto.response.ResponseResult;
import com.google.gson.Gson;

public class ResponseUtils {
    public static final String SUCCESS = "200";

    public static <T> ResponseResult<T> handleResponse(ResponseResult<T> response) {
        if (!SUCCESS.equals(response.getHttpCode())) {
            throw new BitgetApiException(new Gson().toJson(response), response.getHttpCode(), response.getCode(), response.getMsg());
        }

        return response;
    }
}
