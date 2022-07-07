package com.cheng.system.service.impl;

import com.cheng.api.commons.systemUtils;
import com.cheng.system.enity.RoleEntity;
import com.cheng.system.info.RoleInfo;
import com.cheng.system.repository.RoleRepository;
import com.cheng.system.service.RoleService;
import com.cheng.system.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;

    /**
     * 添加角色
     * @param roleEntity
     * @return
     */
    @Override
    public boolean addRole(RoleEntity roleEntity) {
        RoleInfo save = repository.save(ConvertUtil.roleEntityConvertRoleInfo(roleEntity));
        if (!systemUtils.isNull(save) && save.getRid()!=0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<RoleEntity> queryAllRole() {
        List<RoleInfo> all = repository.findAll();

        return ConvertUtil.roleInfosConvertRoleEntity(all);
    }
}
