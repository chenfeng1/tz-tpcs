package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色 实体类
 *
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity{

    private String name; //角色名,比如:管理员
    private String code; //角色代码,比如:ADMIN
    private String desc; //描述
    private Boolean isSystem;// 是否为系统内置角色
    private int seq; //排序

    private Set<Resources> resources;//资源集合

    public Role() {
    }

    public Role(String name, String code, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "r_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @ManyToMany
    //定义中间表名，字段名
    @JoinTable(name="sys_role_to_res",
            joinColumns={@JoinColumn(name="role_id")},
            inverseJoinColumns={@JoinColumn(name="res_id")})
    public Set<Resources> getResources() {
        return resources;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }

    @Column(nullable = false)
    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", isSystem=" + isSystem +
                ", seq=" + seq +
                ", resources=" + resources +
                '}';
    }
}