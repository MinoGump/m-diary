package com.mino.mdiary.util;

import com.mino.mdiary.entity.vo.ResponseInfo;

import static com.mino.mdiary.common.Constants.COMMON_FAIL_CODE;
import static com.mino.mdiary.common.Constants.COMMON_SUCCESS_CODE;

public class ResponseUtil {

    public static ResponseInfo getFailedResponse(int code, String message) {
        return new ResponseInfo(false, code, message, null);
    }

    public static ResponseInfo getFailedResponse() {
        return new ResponseInfo(false, COMMON_FAIL_CODE, null, null);
    }



    public static ResponseInfo getSuccessfulResponse() {
        return new ResponseInfo(true, COMMON_SUCCESS_CODE, null, null);
    }

    public static ResponseInfo getSuccessfulResponse(String message, String data) {
        return new ResponseInfo(true, COMMON_SUCCESS_CODE, message, data);
    }
}
