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
@Table(name = "sys_resources")
public class Resources extends BaseEntity{

    enum Type{
        FOLDER, //功能或模块的文件夹
        URL //URL资源
    }


    private String name; //资源名
    private String code; //资源代码
    private Type type; //资源类型
    private String value; //资源值
    private Resources parent; //父资源
    private Set<Resources> children; //多个子资源
    private String icon; //子功能图标
    private int seq;//用于排序的序号

    public Resources() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @OneToMany(mappedBy = "parent")
    public Set<Resources> getChildren() {
        return children;
    }

    public void setChildren(Set<Resources> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "res_code", unique = true, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "res_type", unique = true, nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(name = "res_value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @ManyToOne
    public Resources getParent() {
        return parent;
    }

    public void setParent(Resources parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", parent=" + parent +
                ", children=" + children +
                ", icon='" + icon + '\'' +
                ", seq=" + seq +
                '}';
    }

    public void addChild(Resources child){
        if(children == null){
            children = new HashSet<Resources>();
        }
        //添加到集合中
        children.add(child);
        child.setParent(this);
    }

}