package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源 实体类
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 */
@Entity
@Table(name = "sys_resource")
public class Resource extends BaseEntity{

    private String name; //资源名
    private String code; //资源代码
    private String type; //资源类型
    private String value; //资源值
    private Resource parent; //父资源
    private Set<Resource> children; //多个子资源
    private String icon; //子功能图标
    private int seq;//用于排序的序号

    public Resource() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @OneToMany(mappedBy = "parent")
    public Set<Resource> getChildren() {
        return children;
    }

    public void setChildren(Set<Resource> children) {
        this.children = children;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne
    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public void addChild(Resource child){
        if(children == null){
            children = new HashSet<Resource>();
        }
        //添加到集合中
        children.add(child);
        child.setParent(this);
    }

}