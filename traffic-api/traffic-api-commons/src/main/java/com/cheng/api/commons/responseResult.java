package com.cheng.api.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//每次请求都返回这个
public class responseResult<T> {
    //当前响应的状态码
    String resultCode;
    //当前响应的信息(错误的时候会写上)
    String resultMsg;
    //具体的结果
    T result;

    public responseResult(String resultCode, String resultMsg, T result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

    public responseResult(String resultCode) {
        this.resultCode = resultCode;
    }

    public responseResult(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


}
