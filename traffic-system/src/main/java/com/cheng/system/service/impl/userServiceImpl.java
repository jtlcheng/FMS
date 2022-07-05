package com.cheng.system.service.impl;

import com.cheng.api.commons.systemUtils;
import com.cheng.system.enity.UserEntity;
import com.cheng.system.info.UserInfo;
import com.cheng.system.repository.UserRepository;
import com.cheng.system.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class userServiceImpl implements userService {

    final int ZERO=0;
    final int uStatus=1;
    @Autowired
    UserRepository repository;
    Logger logger= LoggerFactory.getLogger(userServiceImpl.class);
    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    @Override
    public boolean addUser(UserEntity userEntity) {
        UserInfo user=null;
        logger.info("system user service addUser start:"+userEntity);
        logger.info("system user service addUser UserRepository save start");
        try {
            user = repository.save(zhuanHuan(userEntity));
            logger.info("system user service addUser UserRepository save end");
            logger.info("user:"+user);
        }catch (Exception exception){
            logger.error("system user service addUser Fail");
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
     * 删除用户
     * @param uid
     * @return true 删除成功
     */
    @Override
    public boolean delUser(String uid) {
        //删除一条删除多条的业务逻辑
        String[] ids = uid.split(",");

        if (systemUtils.isNull(ids)||ids.length==0){
            return false;
        }
    //删除一条
        if (ids.length==1){
            //先查询后更新
            try {
                long id=Long.parseLong(ids[0]);
                UserInfo userInfo = repository.findById(id).get();
                userInfo.setUstatus(uStatus);
                repository.save(userInfo);
                return true;
            }catch (Exception e){
                return false;
            }


        }else {//删除多条
            try {
                Set<Long> sets=new HashSet<>();
                for (String id : ids) {
                    sets.add(Long.valueOf(id));
                }
                repository.updates(sets);
                return true;
            }catch (Exception e){
                return false;
            }
        }
    }

    /**
     * 修改用户
     * @param userEntity 前端传过来的参数
     * @return true 删除成功
     */
    @Override
    public boolean updUser(UserEntity userEntity) {

        //2根据你前段来的信息进行修改

        //代表这个信息不是空;需要修改
        try {   //1去数据库里面查询有没有这个记录
            UserInfo userInfo = repository.findById(userEntity.getUid()).get();
            if (!systemUtils.isNullOrEmpty(userEntity.getUname())){
                userInfo.setUname(userEntity.getUname());
            }//手机修改
            if (!systemUtils.isNullOrEmpty(userEntity.getUphone())){
                userInfo.setUphone(userEntity.getUphone());
            }//邮箱修改
            if (!systemUtils.isNullOrEmpty(userEntity.getUmail())){
                userInfo.setUmail(userEntity.getUmail());
            }


            UserInfo save = repository.save(userInfo);
            logger.info("system user upd success");
            return true;
        }catch (Exception e){
            logger.error("system user upd fail");
            return false;
        }
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserEntity> findAllUser() {
        logger.info("system user service findAllUser start:");
        List<UserInfo> allUserInfo = repository.findAll();
        logger.info("system user service findAllUser end");
        return niZhuanList(allUserInfo);

    }
    /**
     * 条件查询
     * @param userEntity
     * @return
     */
    public List<UserEntity> findAllUserByWhere(UserEntity userEntity){
        logger.info("system user service findAllUserByWhere start :"+ userEntity);
        //将前端来的实体类转换成数据库用的实体类
        UserInfo userInfo = zhuanHuan(userEntity);
        //匹配器
        ExampleMatcher matcher=ExampleMatcher.matching()
                .withMatcher("uname",m->m.contains())
                .withMatcher("umail",m->m.contains())
                .withIgnorePaths("uid");
        //根据实体类去构建查询条件
        Example<UserInfo> example=Example.of(userInfo,matcher);
        //根据咱们的条件去查询条件
        List<UserInfo> all = repository.findAll(example);
        logger.info("system user service findAllUserByWhere end :"+userEntity);
        return niZhuanList(all);

    }
    /**
     *
     * @param t1 起始时间
     * @param t2 结束时间
     * @return
     */
    @Override
    public List<UserEntity> findUsersByTime(String t1, String t2) {
        logger.info("system user service findAllUserByWhere start :"+ t1,t2);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<UserInfo> users = null;
        try {
            users = repository.findAllByUtimeBetween(sdf.parse(t1),sdf.parse(t2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.info("system user service findAllUserByWhere end :"+t1,t2);

        return niZhuanList(users);
    }

    @Override
    public Map<String, Object> queryUsers(UserEntity userEntity) {
        logger.info("system user service queryUsers start :"+ userEntity);
        //分页的对象 排序
        // 默认的分页规则
        Pageable of=PageRequest.of(userEntity.getCurrentPage(),userEntity.getPageSize());
        //如果需要排序，按照指定的字段排序
        if (!systemUtils.isNullOrEmpty(userEntity.getSort())){
            String [] sorts=null;
            sorts=userEntity.getSort().split(",");

            if ("ASC".equals(userEntity.getSortType())){
                of=PageRequest.of(userEntity.getCurrentPage(),userEntity.getPageSize(), Sort.Direction.ASC,sorts);
            }else {
                of=PageRequest.of(userEntity.getCurrentPage(),userEntity.getPageSize(), Sort.Direction.DESC,sorts);
            }
        }

        //条件查询
        Specification<UserInfo> spec=new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //自定义交换的对象
                Predicate predicate=cb.conjunction();
                //根据邮箱查询
                if (!systemUtils.isNullOrEmpty(userEntity.getUmail())){
                    predicate.getExpressions().add(cb.equal(root.get("umail"),userEntity.getUmail()));
                }
                //根据手机是否模糊匹配查询
                if (!systemUtils.isNullOrEmpty(userEntity.getUphone())){
                    predicate.getExpressions().add(cb.like(root.get("uphone").as(String.class),"%"+userEntity.getUphone()+"%"));
                }
                if (!systemUtils.isNullOrEmpty(userEntity.getUstatus())&& userEntity.getUstatus().equals("0")){
                    predicate.getExpressions().add(cb.equal(root.get("ustatus"),userEntity.getUstatus()));
                }
                //起始时间
                if (!systemUtils.isNullOrEmpty(userEntity.getStartTime())){
                    predicate.getExpressions().add(
                            cb.greaterThanOrEqualTo(root.get("utime").as(String.class),userEntity.getStartTime()));
                }
                //终止时间
                if (!systemUtils.isNullOrEmpty(userEntity.getEndTime())){
                    predicate.getExpressions().add(
                            cb.lessThanOrEqualTo(root.get("utime").as(String.class),userEntity.getEndTime()));
                }
                return predicate;
            }
        };
        //根据指定的分页和条件查询规则查询当前页内容
        Page<UserInfo> page=repository.findAll(spec,of);
        //定义我们的数据库查询出来的数据
        List<UserInfo> content=page.getContent();
        //转成我们前端使用的对象
        List<UserEntity> users=niZhuanList(content);
        //通过页内容，构建数据
        Map<String,Object> map=new HashMap<>();
        map.put("users",users);//查询到的列表信息
        map.put("totalPage",page.getTotalPages());//总页数
        map.put("currentPage",userEntity.getCurrentPage());
        map.put("pageSize",userEntity.getPageSize());
        logger.info("system user service queryUsers end :"+ userEntity);
        return map;
    }

    /**
     * 将userInfo 转换成 UserEntity List
     * @param userInfos
     * @return
     */
    public List<UserEntity>niZhuanList(List<UserInfo> userInfos){
        logger.info("system user service nizhuanList start"+userInfos);
        List<UserEntity> list=new ArrayList<>();
        for (UserInfo us : userInfos) {
            list.add(niZhuang(us));
        }
        logger.info("system user service niZhuanList end:"+userInfos);
        return list;
    }
    /**
     * 将userInfo 转换成 UserEntity
     * @param userInfo
     * @return
     */
    private UserEntity niZhuang(UserInfo userInfo){
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
        logger.info("system user service changeover end:"+userInfo);
        return userEntity;

    }
    /**
     * 将 userEntity转换成 UserInfo
     * @param userEntity
     * @return UserInfo
     */
    public UserInfo zhuanHuan(UserEntity userEntity){
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
        logger.info("system user service transition end: "+userInfo);

        return userInfo;
    }
}
