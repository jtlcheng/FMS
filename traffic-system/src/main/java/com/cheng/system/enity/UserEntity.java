package com.cheng.system.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 前端传过来的实体类
 */
public class UserEntity {
    private long uid;
    private String uname;
    private String uaccount;
    private String upass;
    private String umail;
    private String uphone;
    private String t1;
    private String udesc;

    Date date =new Date();
    private Date utime=date;

    //角色编号:添加或修改的时候，需要去给用户指定角色信息 1,2,3,4,5
    private String roleStr;

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    private List<RoleEntity> roles;


    //给前台展示去展示的角色信息
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    //我们在查询功能的时候会使用到
    //我们在查询的时候会用到
    //和前端约定 1:时间戳 2:字符串日期
    private String startTime;
    private String endTime;

    private String sortType="ASC";

    private String ustatus="0";

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    //假设做两个组合
    private String sort;//组合排序规则；uTime,uAccount

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    private int currentPage=0;//当前页; 默认是从0开始
    private int pageSize=3;//每页记录数

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }


    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }


    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }


    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }


    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }


    public String getDesc() {
        return udesc;
    }

    public void setDesc(String desc) {
        this.udesc = desc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", uaccount='" + uaccount + '\'' +
//                ", upass='" + upass + '\'' +
                ", umail='" + umail + '\'' +
                ", uphone='" + uphone + '\'' +
                ", t1='" + t1 + '\'' +
                ", udesc='" + udesc + '\'' +

                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }


}
