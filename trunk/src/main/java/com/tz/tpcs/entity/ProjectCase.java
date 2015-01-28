package com.tz.tpcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 学员项目案例 实体类
 *
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 */
@Entity
@Table(name = "project_case")
public class ProjectCase extends BaseEntity {

    private static final int DESC_MAX_LEN = 500; //项目描述最大长度

    private String name; //项目名
    private String code; //代码
    private String desc; //描述
    //add by yejf
    private String snapshot; //项目截图
    //add by huJingLing
    private String functionSpec; //项目规格说明书
    private int seq; //排序

    /** 空参构造 */
    public ProjectCase() {
    }

    @Column(name = "case_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "case_code", nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "case_desc", nullable = false, length = DESC_MAX_LEN)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Column(name = "case_snapshot")
    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    @Column(name = "case_fs")
    public String getFunctionSpec() {
        return functionSpec;
    }

    public void setFunctionSpec(String functionSpec) {
        this.functionSpec = functionSpec;
    }

    @Column(name = "case_seq")
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "ProjectCase{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", snapshot='" + snapshot + '\'' +
                ", functionSpec='" + functionSpec + '\'' +
                ", seq=" + seq +
                '}';
    }
}
