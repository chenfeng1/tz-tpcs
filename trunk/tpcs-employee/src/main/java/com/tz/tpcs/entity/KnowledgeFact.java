package com.tz.tpcs.entity;

import javax.persistence.*;

/**
 * Knowledge
 * 描述 事实知识的实体节点
 *
 * @author 叶加飞
 * @version 1.0
 * @since 2015-02-06
 */

@Entity
@Table(name = "tbl_knowledge_fact")
public class KnowledgeFact extends BaseEntity {

    /** 文件类型 */
    private String filetype;
    /** 文件名 */
    private String filename;
    /** 重要性  */
    private String importance;
    /** 难易度  */
    private String difficulty;
    /** 发布者 */
    private String publisher;
    /** 更新者 */
    private String republisher;

    //发布日期与更新日期 使用父类的属性

    /** 所属哪个知识节点 */
    private Knowledge knowledge;

    /****
     * 空参构造
     */
    public KnowledgeFact() {
    }

    @Column(name = "k_file_type", nullable = false)
    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    @Column(name = "k_file_name",nullable = false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name = "k_importance",nullable = false)
    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    @Column(name = "k_difficulty",nullable = false)
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Column(name = "k_publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(name = "k_re_publisher")
    public String getRepublisher() {
        return republisher;
    }

    public void setRepublisher(String republisher) {
        this.republisher = republisher;
    }

    @ManyToOne
    @JoinColumn(name = "knowledge_id")
    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public String toString() {
        return "KnowledgeFact{" +
                "filetype='" + filetype + '\'' +
                ", filename='" + filename + '\'' +
                ", importance='" + importance + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", publisher='" + publisher + '\'' +
                ", republisher='" + republisher + '\'' +
                '}';
    }
}
