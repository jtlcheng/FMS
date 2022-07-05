package com.cheng.system.service;

import com.cheng.system.enity.UserEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface userService {

    /**
     * 添加用户
     * @param userEntity 前端传过来的参数信息
     * @return true 添加保存成功
     */
     boolean addUser(UserEntity userEntity);

    /**
     *删除用户信息
     * @param uid 前端传过来的id
     * @return true 删除成功
     */
    boolean delUser(String uid);

    /**
     * 修改用户信息
     * @param userEntity 前端传过来的参数
     * @return true 修改成功
     */
    boolean updUser(UserEntity userEntity);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<UserEntity> findAllUser();

    /**
     * 条件查询
     * @param userEntity
     * @return
     */
    List<UserEntity> findAllUserByWhere(UserEntity userEntity);

    /**
     * 时间查询
     * @param t1 起始时间
     * @param t2 结束时间
     * @return
     */
    List<UserEntity> findUsersByTime(String t1, String t2);

    /**
     * 分页查询
     * 条件查询
     * @param userEntity
     * @return
     */
    Map<String,Object> queryUsers(UserEntity userEntity);
}
