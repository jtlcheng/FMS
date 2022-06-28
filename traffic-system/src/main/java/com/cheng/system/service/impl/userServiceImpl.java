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


@Service
public class userServiceImpl implements userService {

    final int ZERO=0;
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
            logger.error("system user service addUser success");
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
        logger.info("system user service transition end: "+userInfo);

        return userInfo;
    }
}
