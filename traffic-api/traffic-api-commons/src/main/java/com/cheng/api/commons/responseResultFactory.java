package com.cheng.api.commons;

import com.cheng.API.code.SystemCode;

public class responseResultFactory<T>{
    /**
     * 构建一个通用的成功返回结果
     * @return
     */
    public static responseResult buildResponseResult(){
        return new responseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS);
    }
    public static responseResult buildResponseResult(String code){
        return new responseResult(code);
    }
    public static responseResult buildResponseResult(String resultCode,String resultMsg){
        return new responseResult(resultCode,resultMsg);
    }
    public static <T> responseResult buildResponseResult(String resultCode,String resultMsg,T obj){
        return new responseResult(resultCode,resultMsg,obj);
    }
}
