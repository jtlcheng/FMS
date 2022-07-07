package com.cheng.system.util;

import com.cheng.system.enity.RoleEntity;
import com.cheng.system.enity.UserEntity;
import com.cheng.system.info.RoleInfo;
import com.cheng.system.info.UserInfo;
import com.cheng.system.service.impl.userServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
    /**
     * 将userInfo 转换成 UserEntity
     * @param userInfo
     * @return
     */
    static Logger logger= LoggerFactory.getLogger(userServiceImpl.class);
    /**
     * 将userInfo 转换成 UserEntity List
     * @param userInfos
     * @return
     */
    public static List<UserEntity> userInfosConvertUserEntity(List<UserInfo> userInfos){
        logger.info("system user service nizhuanList start"+userInfos);
        List<UserEntity> list=new ArrayList<>();
        for (UserInfo us : userInfos) {
            list.add(userInfoConvertUserEntity(us));
        }
        logger.info("system user service niZhuanList end:"+userInfos);
        return list;
    }
    public static UserEntity userInfoConvertUserEntity(UserInfo userInfo){
        logger.info("system user service changeover start: "+userInfo);
        UserEntity userEntity=new UserEntity();
        userEntity.setT1(userInfo.getT1());
        userEntity.setUpass(userInfo.getUpass());
        userEntity.setDesc(userInfo.getDesc());
        userEntity.setUaccount(userInfo.getUaccount());
        userEntity.setUmail(userInfo.getUmail());
        userEntity.setUname(userInfo.getUname());
        userEntity.setUid(userInfo.getUid());
        userEntity.setUtime(userInfo.getUtime());

        if (null!=userInfo.getRoleInfos()){
            List<RoleEntity> roles=new ArrayList<>();
            for (RoleInfo re : userInfo.getRoleInfos()) {
                userEntity.setRoles(roleInfosConvertRoleEntity(userInfo.getRoleInfos()));
            }
        }

        logger.info("system user service changeover end:"+userInfo);
        return userEntity;

    }
    /**
     * 将 userEntity转换成 UserInfo
     * @param userEntity
     * @return UserInfo
     */
    public static UserInfo userEntityConvertsUserInfo(UserEntity userEntity){
        logger.info("system user service transition start: "+userEntity);
        UserInfo userInfo=new UserInfo();
        userInfo.setT1(userEntity.getT1());
        userInfo.setUaccount(userEntity.getUaccount());
        userInfo.setDesc(userEntity.getDesc());
        userInfo.setUid(userEntity.getUid());
        userInfo.setUmail(userEntity.getUmail());
        userInfo.setUname(userEntity.getUname());
        userInfo.setUpass(userEntity.getUpass());
        userInfo.setUphone(userEntity.getUphone());
//        UserInfo.setuStatus(UserEntity.getuStatus());
        userInfo.setUtime(userEntity.getUtime());
        logger.info("system user service transition end: "+userInfo);

        return userInfo;
    }


    /**
     * 集合转换
     * @param roleInfos
     * @return
     */
    public static List<RoleEntity> roleInfosConvertRoleEntity(List<RoleInfo> roleInfos){
        List<RoleEntity> list=new ArrayList<>();
        for (RoleInfo roleInfo : roleInfos) {
            list.add(roleInoConvertRoleEntity(roleInfo));
        }
        return list;
    }
    /**
     * 转换RoleEntity
     * @param roleInfo
     * @return
     */
    public static RoleEntity roleInoConvertRoleEntity(RoleInfo roleInfo){
        RoleEntity roleEntity=new RoleEntity();

        roleEntity.setRid(roleInfo.getRid());
        roleEntity.setRname(roleInfo.getRname());
        roleEntity.setRype(roleInfo.getRype());
        roleEntity.setRdesc(roleInfo.getRdesc());
        //解决鸡生蛋蛋生鸡的问题；规避角色里面有用户，用户里面有角色
        if(null!=roleInfo.getUserInfos() && roleInfo.getUserInfos().size()>0
            && roleInfo.getUserInfos().get(0).getRoleInfos()==null){
            roleEntity.setUserEntities(ConvertUtil.userInfosConvertUserEntity(roleInfo.getUserInfos()));
        }
        return roleEntity;
    }
    /**
     * 转换RoleInfo
     * @param roleEntity
     * @return
     */
    public static RoleInfo roleEntityConvertRoleInfo(RoleEntity roleEntity){
        RoleInfo roleInfo=new RoleInfo();
        roleInfo.setRid(roleEntity.getRid());
        roleInfo.setRname(roleEntity.getRname());
        roleInfo.setRype(roleEntity.getRype());
        roleInfo.setRdesc(roleEntity.getRdesc());
        return roleInfo;
    }
}
