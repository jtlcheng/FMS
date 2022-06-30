package com.cheng.system.service.impl;

import com.cheng.api.commons.systemUtils;
import com.cheng.system.enity.userEntity;
import com.cheng.system.info.userInfo;
import com.cheng.system.repository.userRepository;
import com.cheng.system.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class userServiceImpl implements userService {

    final int ZERO=0;
    final int uStatus=1;
    @Autowired
    userRepository repository;
    Logger logger= LoggerFactory.getLogger(userServiceImpl.class);
    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    @Override
    public boolean addUser(userEntity userEntity) {
        userInfo user=null;
        logger.info("system user service addUser start:"+userEntity);
        logger.info("system user service addUser userRepository save start");
        try {
            user = repository.save(zhuanHuan(userEntity));
            logger.info("system user service addUser userRepository save end");
            logger.info("user:"+user);
        }catch (Exception exception){
            logger.error("system user service addUser Fail");
            return false;
        }

        if (!systemUtils.isNull(user) && user.getUid()!=ZERO ){
            logger.info("system user service addUser success");
            return true;
        }
            logger.error("system user service addUser fail");
            return false;

    }

    /**
     * 删除用户
     * @param uid
     * @return true 删除成功
     */
    @Override
    public boolean delUser(String uid) {
        //删除一条删除多条的业务逻辑
        String[] ids = uid.split(",");

        if (systemUtils.isNull(ids)||ids.length==0){
            return false;
        }
    //删除一条
        if (ids.length==1){
            //先查询后更新
            try {
                long id=Long.parseLong(ids[0]);
                userInfo userInfo = repository.findById(id).get();
                userInfo.setUstatus(uStatus);
                repository.save(userInfo);
                return true;
            }catch (Exception e){
                return false;
            }


        }else {//删除多条
            try {
                Set<Long> sets=new HashSet<>();
                for (String id : ids) {
                    sets.add(Long.valueOf(id));
                }
                repository.updates(sets);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    /**
     * 修改用户
     * @param userEntity 前端传过来的参数
     * @return true 删除成功
     */
    @Override
    public boolean updUser(userEntity userEntity) {

        //2根据你前段来的信息进行修改

        //代表这个信息不是空;需要修改
        try {   //1去数据库里面查询有没有这个记录
            userInfo userInfo = repository.findById(userEntity.getUid()).get();
            if (!systemUtils.isNullOrEmpty(userEntity.getUname())){
                userInfo.setUname(userEntity.getUname());
            }//手机修改
            if (!systemUtils.isNullOrEmpty(userEntity.getUphone())){
                userInfo.setUphone(userEntity.getUphone());
            }//邮箱修改
            if (!systemUtils.isNullOrEmpty(userEntity.getUmail())){
                userInfo.setUmail(userEntity.getUmail());
            }


            userInfo save = repository.save(userInfo);
            logger.info("system user upd success");
            return true;
        }catch (Exception e){
            logger.error("system user upd fail");
            return false;
        }
    }

    /**
     * 将 userEntity转换成 userInfo
     * @param userEntity
     * @return userInfo
     */
    private userInfo zhuanHuan(userEntity userEntity){
        logger.info("system user service transition start: "+userEntity);
        userInfo userInfo=new userInfo();
        userInfo.setT1(userEntity.getT1());
        userInfo.setUaccount(userEntity.getUaccount());
        userInfo.setDesc(userEntity.getDesc());
        userInfo.setUid(userEntity.getUid());
        userInfo.setUmail(userEntity.getUmail());
        userInfo.setUname(userEntity.getUname());
        userInfo.setUpass(userEntity.getUpass());
        userInfo.setUphone(userEntity.getUphone());
//        userInfo.setuStatus(userEntity.getuStatus());
        logger.info("system user service transition end: "+userInfo);

        return userInfo;
    }
}
