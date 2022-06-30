package com.cheng.API.code;

/**
 * 系统管理模块的错误码
 * 区间:10000-15555
 */
public interface SystemCode {
    //系统通用成功状态码
    String TRAFFIC_SYSTEM_SUCCESS="000000";
    //系统通用失败状态码
    String TRAFFIC_SYSTEM_FAIL="000001";


    /**
     * 错误 提示 警告
     */
    //用户管理 10000-10999
    //10000-10499 错误的提示
            //添加
    String SYSTEM_USER_ERROR_ADD_FAIL = "10000";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NUll ="10001";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UNAME_NULL ="10002";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_ACCOUNT_NULL ="10003";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UPASS_NULL ="10004";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UMAIL_NULL ="10005";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UPHONE_NULL ="10006";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_T1_NULL ="10007";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UDESC_NULL ="10008";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UNAME_SIZE ="10009";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UACCOUNT_SIZE ="10010";
    String SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UPASS_SIZE ="10011";

    //删除
    String SYSTEM_USER_ERROR_DEL_SUCCESS = "10030";
    String SYSTEM_USER_ERROR_DEL_FAIL_PARAM_UID_NULL="10031";

    //修改
    String SYSTEM_USER_ERROR_UPD_SUCCESS = "10050";
    String SYSTEM_USER_ERROR_UPD_FAIL_PARAM_NULL="10051";
    String SYSTEM_USER_ERROR_UPD_FAIL_PARAM_UID_NULL="10052";


    //10500-10999 成功的提示
    int SYSTEM_USER_INFO_ADD_SUCCESS=10500;

    //角色管理 11000-11999
    int SYSTEM_ROLE_ERROR_ADD_FAIL = 11000;
    //权限管理 12000-12999
}
