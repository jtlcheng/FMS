package com.cheng.system.enity;

import lombok.Data;

import java.util.Date;

/**
 * 前端传过来的实体类
 */
public class userEntity {
    private long uid;
    private String uname;
    private String uaccount;
    private String upass;
    private String umail;
    private String uphone;
    private String t1;
    private String udesc;


    //我们在查询功能的时候会使用到
    private Date startTime;
    private Date endTime;


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

    @Override
    public String toString() {
        return "userEntity{" +
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
