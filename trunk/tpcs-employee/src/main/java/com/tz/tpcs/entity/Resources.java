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
public class Resources extends BaseEntity {

    /**
     * 资源类型
     */
    public enum Type{
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
    private int seq; //用于排序的序号
    private boolean show; //需在导航栏中显示

    /** 空参构造 */
    public Resources() {
    }

    /** 有参构造 */
    public Resources(String name, String code, Type type, String value,
                     Resources parent, String icon,int seq) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.value = value;
        this.parent = parent;
        this.icon = icon;
        this.seq = seq;
    }

    /** 有参构造 */
    public Resources(String name, String code, Type type, String value,
                     Resources parent, String icon, int seq, boolean show) {
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.value = value;
        this.parent = parent;
        this.icon = icon;
        this.seq = seq;
        this.show = show;
    }

    @Column(name = "res_icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 一对多关联
     * 一个资源有多个下属子资源
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy(value="seq ASC") //通过自关联查到的 children 按照 seq 自动升序排序
    public Set<Resources> getChildren() {
        if(children == null){
            children = new HashSet<>();
        }
        return children;
    }

    public void setChildren(Set<Resources> children) {
        this.children = children;
    }

    @Column(name = "res_name")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "res_type", nullable = false)
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

    @Column(name = "res_seq")
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

    @Column(name = "res_show")
    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", value='" + value + '\'' +
                ", icon='" + icon + '\'' +
                ", seq=" + seq +
                ", show=" + show +
                '}';
    }

}
