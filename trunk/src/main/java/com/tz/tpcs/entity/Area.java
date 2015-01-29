package com.tz.tpcs.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 地区实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tbl_area")
public class Area extends BaseEntity{

	private static final long serialVersionUID = 3091806785347201985L;
	
	private String name;//区域名(a_name)
	private String code;//区域码(a_code)
	private int level;//区域等级(a_level)
	private Area parent;
	private List<Area> children;
	
	public Area() {
	}
	
	public Area(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	@Column(name="a_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="a_code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="a_level")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	@ManyToOne
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy="parent",cascade={CascadeType.ALL})
	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Area [name=" + name + ", code=" + code + ", level=" + level
				+ "]";
	}
	
	
	

}