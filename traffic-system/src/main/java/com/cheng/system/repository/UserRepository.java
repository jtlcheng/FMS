package com.cheng.system.repository;

import com.cheng.system.info.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 负责数据查询的接口
 */

public interface UserRepository extends JpaRepository<UserInfo,Long>, JpaSpecificationExecutor<UserInfo> {
    //批量更新
    @Transactional
    @Modifying
    @Query("update UserInfo set ustatus =1 where uid in (?1)")
    public int updates(Collection<Long> ids);

    /**
     * 条件查询 根据电话号码
     * @param phone
     * @return
     */
    @Query("select ui from UserInfo ui where ui.uphone=?1")
    List<UserInfo> findUsers1(String phone);

    /**
     * 模糊查询 根据名字
     * @param email
     * @return
     */
    @Query(nativeQuery = true,value = "select * from t_user where t_user.umail like '%?1%'")
    List<UserInfo> findUsers2(String email);

    List<UserInfo> findAllByUtimeBetween(Date t1, Date t2);
}
