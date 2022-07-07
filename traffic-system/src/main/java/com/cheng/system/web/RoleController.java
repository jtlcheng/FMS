package com.cheng.system.web;

import com.cheng.API.code.SystemCode;
import com.cheng.api.commons.responseResult;
import com.cheng.api.commons.responseResultFactory;
import com.cheng.system.enity.RoleEntity;
import com.cheng.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    final static Logger logger= LoggerFactory.getLogger(userController.class);
    @Autowired
    RoleService roleService;

    @PostMapping("addRole")
    public responseResult addRole(RoleEntity roleEntity){

        boolean result = roleService.addRole(roleEntity);
        if (result){
            return returnResponseResult(true, SystemCode.SYSTEM_ROLE_ADD_SUCCESS);
        }else {
            return returnResponseResult(false, SystemCode.SYSTEM_ROLE_ERROR_ADD_FAIL);
        }
    }
    @GetMapping(" ")
    public responseResult queryAllRole(){
        List<RoleEntity> roleEntities = roleService.queryAllRole();
       return  responseResultFactory.buildResponseResult(SystemCode.SYSTEM_ROLE_QUERY_SUCCESS,"查询成功",roleEntities);
    }
    //返回结果视图
    private responseResult returnResponseResult(boolean result,String code){
        responseResult responseResult;
        if (result){
            logger.info("system user return responseResult Success: "+ code);
            responseResult = responseResultFactory.buildResponseResult(code,"添加成功");
        }else {
            logger.error("system user responseResult Fail:"+code);
            responseResult= responseResultFactory.buildResponseResult(code,"添加失败");
        }
        logger.info("system user returnResponseResult end,return:"+responseResult);
        return responseResult;
    }
}
