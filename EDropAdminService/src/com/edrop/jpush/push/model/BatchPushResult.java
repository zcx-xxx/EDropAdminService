package com.edrop.jpush.push.model;

import cn.jiguang.common.resp.BaseResult;
import cn.jiguang.common.resp.ResponseWrapper;
//import cn.jpush.api.report.MessageDetailResult;
//import cn.jpush.api.report.ReceivedsResult;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
//import lombok.Data;

import java.lang.reflect.Type;
//import java.util.List;
import java.util.Map;

public class BatchPushResult extends BaseResult {

    /**
	 * @Fields field:field:
	 */
	private static final long serialVersionUID = 1L;

	private static final Type RESULT_TYPE = new TypeToken<Map<String, PushResult>>() {}.getType();

    private Map<String, PushResult> batchPushResult;

    public class PushResult {
        @Expose public long msg_id;
        @Expose public Error error;
    }

    public class Error {
        @Expose String message;
        @Expose int code;

        public String getMessage() {
            return this.message;
        }

        public int getCode() {
            return this.code;
        }
    }

    public static BatchPushResult fromResponse(ResponseWrapper responseWrapper) {

        BatchPushResult result = new BatchPushResult();
        if (responseWrapper.isServerResponse()) {
            result.batchPushResult = _gson.fromJson(responseWrapper.responseContent, RESULT_TYPE);
        }

        result.setResponseWrapper(responseWrapper);
        return result;
    }

    public Map<String, PushResult> getBatchPushResult() {
        return batchPushResult;
    }

}
