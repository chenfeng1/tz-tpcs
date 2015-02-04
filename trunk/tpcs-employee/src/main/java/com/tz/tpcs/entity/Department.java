package com.tz.tpcs.entity;

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
	
	private String deptName;//部门名称
	private Department parent;//父部门
	private Set<Department> children;//子部门
	private String remark;//备注信息

	@Column(name="d_name")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@ManyToOne
	@JoinColumn(name="d_parent_id")
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy="parent",cascade={CascadeType.ALL})
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

	@Override
	public String toString() {
		return "Department{" +
				"deptName='" + deptName + '\'' +
				", parent=" + parent +
				", children=" + children +
				", remark='" + remark + '\'' +
				'}';
	}
}
