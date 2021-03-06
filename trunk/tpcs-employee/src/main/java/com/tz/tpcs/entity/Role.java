package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
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
    
    //add by chen feng 
    private String[] resourcesArray;//资源集合的names 用于解析json 不持久化到数据库
    /** 空参构造 */
    public Role() {
    }

    /** 有参构造 */
    public Role(String name, String code, String desc, Boolean isSystem, int seq) {
        this.name = name;
        this.code = code;
        this.desc = desc;
        this.isSystem = isSystem;
        this.seq = seq;
    }

    @Column(name = "role_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "role_code", unique = true, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "role_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "role_seq")
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    /** 多对多 */
    @ManyToMany
    //定义中间表名，字段名
    @JoinTable(name="sys_role_to_res",
            joinColumns={@JoinColumn(name="role_id")},
            inverseJoinColumns={@JoinColumn(name="res_id")})
    @OrderBy(value="seq ASC")//通过关联查询按照seq升序排序
    public Set<Resources> getResources() {
        if(resources == null){
            resources = new LinkedHashSet<>();//LinkedHashSet 保持资源唯一且有序
        }
        return resources;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }

    @Column(name = "role_is_system", nullable = false)
    public Boolean isIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean system) {
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
                '}';
    }

    @Transient
	public String[] getResourcesArray() {
		return resourcesArray;
	}

	public void setResourcesArray(String[] resourcesArray) {
		this.resourcesArray = resourcesArray;
	}
	
}
