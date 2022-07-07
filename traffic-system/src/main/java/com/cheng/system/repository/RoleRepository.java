package com.cheng.system.repository;

import com.cheng.system.info.RoleInfo;
import com.cheng.system.info.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 负责数据查询的接口
 */

public interface RoleRepository extends JpaRepository<RoleInfo,Long>, JpaSpecificationExecutor<RoleInfo> {


}
