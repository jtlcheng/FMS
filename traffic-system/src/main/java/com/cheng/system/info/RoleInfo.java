package com.cheng.system.info;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_role")
public class RoleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;
    @Column
    private String rname;
    @Column
    private int rype;
    @Column
    private String rdesc;
    @ManyToMany(mappedBy = "roleInfos")
    private List<UserInfo> userInfos;

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public int getRype() {
        return rype;
    }

    public void setRype(int rype) {
        this.rype = rype;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }
}
