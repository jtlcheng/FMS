package com.cheng.system.service;

import com.cheng.system.enity.RoleEntity;

import java.util.List;

public interface RoleService {
    /**
     * 添加角色
     * @param roleEntity
     * @return
     */
     boolean addRole(RoleEntity roleEntity);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleEntity> queryAllRole();
}
