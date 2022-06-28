package com.cheng.system.repository;

import com.cheng.system.enity.userEntity;
import com.cheng.system.info.userInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 负责数据查询的接口
 */
public interface userRepository extends JpaRepository<userInfo,Long> {
}
