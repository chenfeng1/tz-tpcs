package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Knowledge
 * 描述 知识控制的实体节点
 *
 * @author 叶加飞
 * @version 1.0
 * @since 2015-02-06
 */
@Entity
@Table(name = "tbl_knowledge")
public class Knowledge extends BaseEntity {

    /** 知识节点名 */
    private String knowledgeName;
    /** 子知识节点 */
    private Set<Knowledge> children;
    /** 父知识节点 */
    private Knowledge parent;
    /** 知识描述 */
    private String remark;

    /** 知识下面的事实知识 */
    private List<KnowledgeFact> knowledgeFactList;

    /**
     * 空参构造
     */
    public Knowledge() {
    }

    /**
     * get method
     * @return
     */
    @Column(name = "k_name")
    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<Knowledge> getChildren() {
        return children;
    }

    public void setChildren(Set<Knowledge> children) {
        this.children = children;
    }

    @ManyToOne
    @JoinColumn(name = "k_parent_id")
    public Knowledge getParent() {
        return parent;
    }

    public void setParent(Knowledge parent) {
        this.parent = parent;
    }

    @Column(name = "k_remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(mappedBy = "knowledge")
    public List<KnowledgeFact> getKnowledgeFactList() {
        return knowledgeFactList;
    }

    public void setKnowledgeFactList(List<KnowledgeFact> knowledgeFactList) {
        this.knowledgeFactList = knowledgeFactList;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "knowledgeName='" + knowledgeName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
