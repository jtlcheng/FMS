package com.cheng.system.web;

import com.cheng.API.code.SystemCode;
import com.cheng.api.commons.responseResult;
import com.cheng.api.commons.responseResultFactory;
import com.cheng.system.enity.userEntity;
import com.cheng.system.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cheng.api.commons.systemUtils;
import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class userController {
    final static int UNAME_SIZE=2;
    final static int UACCOUNT_SIZE=4;
    final static int UPASS_SIZE=6;

    final static Logger logger= LoggerFactory.getLogger(userController.class);
    @Autowired
    userService userService;
    @PostMapping("/addUser")
    public responseResult addUser(@RequestBody userEntity userEntity){
        logger.info("system user addUser start");
        //添加失败
        if (systemUtils.isNull(userEntity)){
            logger.error("system user addUser userEntity is null");
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NUll,"参数为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;

        }//名字为空，添加失败
        if (systemUtils.isNullOrEmpty(userEntity.getUname())){
            logger.error("system user addUser uName is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UNAME_NULL,"名字为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//账号为空，添加失败
        if(systemUtils.isNullOrEmpty(userEntity.getUaccount())){
            logger.error("system user addUser uAccount is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_ACCOUNT_NULL,"账号为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//密码为空，添加失败
        if(systemUtils.isNullOrEmpty(userEntity.getUpass())){
            logger.error("system user addUser uPass is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UPASS_NULL,"密码为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//邮箱为空，添加失败
        if(systemUtils.isNullOrEmpty(userEntity.getUmail())){
            logger.error("system user addUser uMail is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UMAIL_NULL,"邮箱为空为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//电话号码为空，添加失败
        if(systemUtils.isNullOrEmpty(userEntity.getUphone())){
            logger.error("system user addUser uPhone is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UPHONE_NULL,"电话号码为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//T1为空，添加失败
        if(systemUtils.isNullOrEmpty(userEntity.getT1())){
            logger.error("system user addUser T1 is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_T1_NULL,"T1为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//Desc为空，添加失败
        if(systemUtils.isNullOrEmpty(userEntity.getDesc())){
            logger.error("system user addUser Desc is null");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UDESC_NULL,"描述为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//UNAME长度小小于2
        if (userEntity.getUname().trim().length()<UNAME_SIZE){
            logger.error("system user addUser uName length is < 2");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UNAME_SIZE,"字符长度小于2");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//UACCOUNT长度小于2
        if (userEntity.getUaccount().trim().length()<UACCOUNT_SIZE){
            logger.error("system user addUser uAccount length is < 4");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UACCOUNT_SIZE,"账号长度小于4");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }//UPASS长度小于6
        if (userEntity.getUpass().trim().length()<=UPASS_SIZE){
            logger.error("system user addUser uPass length is < 6");
            logger.info("param:"+userEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UPASS_SIZE,"密码长度小于等于6");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }
        //密码加密操作
        //千万b不要再这里记录密码；违反安全规范
        logger.info("system user addUser pass digest");
        userEntity.setUpass(DigestUtils.md5DigestAsHex(userEntity.getUpass().getBytes()));
        //保存用户信息
        logger.info("system user addUser userService addUser start");
        boolean result = userService.addUser(userEntity);
        logger.info("system user addUser userService addUser end");

        //添加成功
        responseResult responseResult;
        if (result!=true){
            responseResult=responseResultFactory.buildResponseResult();

        }else {
            responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL,"添加成功");

        }
            logger.info("system user addUser end,return:"+responseResult);
            return responseResult;
    }
}
