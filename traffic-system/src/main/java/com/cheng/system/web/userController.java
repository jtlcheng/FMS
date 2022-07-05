package com.cheng.system.web;

import com.cheng.API.code.SystemCode;
import com.cheng.api.commons.responseResult;
import com.cheng.api.commons.responseResultFactory;
import com.cheng.system.enity.UserEntity;
import com.cheng.system.repository.UserRepository;
import com.cheng.system.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import com.cheng.api.commons.systemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class userController {
    final static int UPD_USER_ZERO=0;
    final static int UNAME_SIZE=2;
    final static int UACCOUNT_SIZE=4;
    final static int UPASS_SIZE=6;

    final static Logger logger= LoggerFactory.getLogger(userController.class);
    @Autowired
    userService userService;
    @Autowired
    UserRepository repository;

    @PostMapping("/addUser")
    public responseResult addUser(@RequestBody UserEntity userEntity){
        logger.info("system user addUser start");
        //添加失败
        if (systemUtils.isNull(userEntity)){
            logger.error("system user addUser UserEntity is null");
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
        }
      /*  if (systemUtils.isNull(UserEntity.getuStatus())){
            logger.error("system user addUser uStatus is null");
            logger.info("param:"+UserEntity);
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_UDESC_NULL,"描述为空");
            logger.info("system user addUser return msg:"+ responseResult);
            return responseResult;
        }*/
        //UNAME长度小小于2
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

    /**
     * 删除用户的请求
     * @param uid="1" 删除一个; uid="1,2,3" 删除多个用逗号隔开
     * @return是否成功
     */
    @PostMapping("/delUser")
    public responseResult delUser(String uid){
        logger.info("system user delUser start");
        //传过来的参数是空的
        if (systemUtils.isNullOrEmpty(uid)){
            logger.error("system user delUser uid is null");
            responseResult responseResult=responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_DEL_FAIL_PARAM_UID_NULL);
            logger.info("system user delUser return msg:"+responseResult);
            return responseResult;
        }
        //简单的逻辑判断 1：可以在这里判断 2：交给业务层判断
        logger.info("system user delUser userService start");
        boolean result =userService.delUser(uid);
        logger.info("system suer delUser userService end"+result);

        responseResult responseResult;
        if (result!=true){
            logger.error("system user delUser Fail");
            responseResult=responseResultFactory.buildResponseResult();
        }else {
            logger.info("system user delUser Success");
            responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_DEL_SUCCESS,"修改成功");
        }
        logger.info("system user delUser end,return:"+responseResult);
        return responseResult;
    }

    /**
     * 修改用户
     * @param entity
     * @return
     */
    @PostMapping("updUser")
    public responseResult updUser(@RequestBody UserEntity entity){
        logger.info("system user updUser start");
        //参数为空
        if (systemUtils.isNull(entity)){
            logger.error("system user updUser UserEntity is null");
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_UPD_FAIL_PARAM_NULL,"参数为空");
            logger.info("system user updUser return msg:"+ responseResult);
            return responseResult;

        }
        //没有传ID
        if (systemUtils.isNull(entity.getUid())||entity.getUid()==UPD_USER_ZERO){
            logger.error("system user updUser uId is null");
            responseResult responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_UPD_FAIL_PARAM_UID_NULL,"UID为空");
            logger.info("system user updUser return msg:"+ responseResult);
            return responseResult;

        }
        //简单的逻辑判断 1：可以在这里判断 2：交给业务层判断
        logger.info("system user updUser userService start");
        boolean result =userService.updUser(entity);
        logger.info("system suer updUser userService end"+result);

        responseResult responseResult;
        if (result!=true){
            logger.error("system user updUser Fail");
            responseResult=responseResultFactory.buildResponseResult();
        }else {
            logger.info("system user updUser Success");
            responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_UPD_SUCCESS,"修改成功");
        }
        logger.info("system user updUser end,return:"+responseResult);
        return responseResult;
    }

    /**
     * 查询所有用户信息
     * @param 
     * @return
     */
    @GetMapping("findAllUser")
    public responseResult<List<UserEntity>> findAllUser(){
        logger.info("system user query start");
        List<UserEntity> allUser = userService.findAllUser();
        responseResult <List<UserEntity>> responseResult=responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_SUCCESS,"查询成功",allUser);
        logger.info("system user query end");
        return responseResult;
    }

    /**
     * 根据条件查询
     * @param userEntity
     * @return 查询列表
     */
    @PostMapping("findUsersByWhere")
    public responseResult<List<UserEntity>> findUsersByWhere(UserEntity userEntity){
        List<UserEntity> allUserByWhere = userService.findAllUserByWhere(userEntity);
        logger.info("system user findUsersByWhere start :"+allUserByWhere);
        responseResult <List<UserEntity>> responseResult;
//        if (systemUtils.isNull(allUserByWhere) && allUserByWhere.size()>0)
            if (!systemUtils.isNull(allUserByWhere) && allUserByWhere.size()>0){
             responseResult=responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_SUCCESS,"查询成功",allUserByWhere);
        }else {
             responseResult = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_FAIL, "内容不存在", allUserByWhere);
        }
            logger.info("system user findUserByWhere end:"+allUserByWhere);
       return responseResult;
    }

    /**
     * 根据时间查询
     * @param userEntity
     * @return
     */
    @PostMapping("findUsersByTime")
    public responseResult<List<UserEntity>> findUsersByTime(UserEntity userEntity) {

        List<UserEntity> users = userService.findUsersByTime(userEntity.getStartTime(), userEntity.getEndTime());
        logger.info("system user findUsersByTime start :"+users);
        responseResult<List<UserEntity>> responseResults;
        if (!systemUtils.isNull(users) && users.size() > 0) {
            logger.info("system user findUsersByTime success");
            responseResults = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_SUCCESS, "查询成功", users);
        } else {
            logger.info("system user findUsersByTime fail");
            responseResults = responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_FAIL, "内容不存在", users);
        }
        logger.info("system user findUsersByTime end:"+users);
        return responseResults;
    }

    /**
     * 通用的分页查询
     * 排序，分页，条件查询
     * @param userEntity
     * @return
     */
    @PostMapping("queryUsers")
    public responseResult<List<UserEntity>> queryUsers(UserEntity userEntity){

        //分页查询+条件擦好像
        Map<String,Object>map=userService.queryUsers(userEntity);
        logger.info("system user findUsersByTime start :"+map);
        responseResult responseResult;
        ArrayList users = (ArrayList) map.get("users");
        System.out.println(users.size());

        if (users.size() != 0 ){
             responseResult=responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_SUCCESS,"查询成功",map);
             logger.info("system user findUsersByTime success");
        }else {
             responseResult=responseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_QUERY_FAIL,"没有这个内容",map);
             logger.info("system user findUsersByTime is null");
        }
        logger.info("system user findUsersByTime end :"+map);
        return responseResult;
    }

    //返回结果视图
    private responseResult returnResponseResult(boolean result,String code){
        responseResult responseResult;
        if (result!=true){
            logger.error("system user updUser Fail");
            responseResult=responseResultFactory.buildResponseResult();
        }else {
            logger.info("system user updUser Success");
            responseResult = responseResultFactory.buildResponseResult(code,"修改成功");
        }
        logger.info("system user returnResponseResult end,return:"+responseResult);
        return responseResult;
    }
}
