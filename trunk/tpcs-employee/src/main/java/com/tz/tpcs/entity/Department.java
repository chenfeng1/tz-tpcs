package com.tz.tpcs.entity;

import org.dozer.Mapping;

import javax.persistence.*;
import java.util.Set;

/**
 * 部门 实体类
 * 自关联
 * @author 胡荆陵
 * @since 2015-02-04
 */
@Entity
@Table(name="tbl_department")
public class Department extends BaseEntity{
	
	private static final long serialVersionUID = 4735907212206380861L;
	
	private String name; //部门名称
	private Department parent; //父部门
	private Set<Department> children; //子部门
	private String remark; //备注信息
	private int level; //层级
	private int seq; //排序

	/**
	 * 空参构造
	 */
	public Department() {
	}

	/**
	 * 有参构造
	 * @param parent 上级部门
	 */
	public Department(String name, Department parent, int level, int seq) {
		this.name = name;
		this.parent = parent;
		this.level = level;
		this.seq = seq;
	}

	@Mapping("text") //映射到Dozer
	@Column(name="d_name", nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="d_parent_id")
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy="parent")
	@OrderBy("seq")
	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}

	@Column(name="d_remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="d_level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name="d_seq")
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "Department{" +
				"name='" + name + '\'' +
				", parent=" + parent +
				", remark='" + remark + '\'' +
				", level=" + level +
				", seq=" + seq +
				'}';
	}
}
