package com.cheng.system.service;

import com.cheng.system.enity.userEntity;


public interface userService {

    /**
     * 添加用户
     * @param userEntity 前端传过来的参数信息
     * @return true 添加保存成功
     */
     boolean addUser(userEntity userEntity);

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
    boolean updUser(userEntity userEntity);
}
