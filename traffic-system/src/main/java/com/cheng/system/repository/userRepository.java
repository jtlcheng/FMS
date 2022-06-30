package com.cheng.system.repository;

import com.cheng.system.enity.userEntity;
import com.cheng.system.info.userInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * 负责数据查询的接口
 */

public interface userRepository extends JpaRepository<userInfo,Long> {
    //批量更新
    @Transactional
    @Modifying
    @Query("update userInfo set ustatus =1 where uid in (?1)")
    public int updates(Collection<Long> ids);
}
