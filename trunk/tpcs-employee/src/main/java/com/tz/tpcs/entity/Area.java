package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 地区实体类
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "tbl_area")
public class Area extends BaseEntity {

	private static final long serialVersionUID = 3091806785347201985L;

	private String name;// 区域名(a_name)
	private String zipCode;// 邮政编码(a_zipcode)
	private String divisionCode;// 行政区划码(a_divisioncode)
	private int level;// 区域等级(a_level)
	private Area parent;
	private List<Area> children;
	
	/**
	 * @author guan
	 */
	public Area() {
	}

	/**
	 * @author guan
	 */
	public Area(String zipCode, String divisionCode, String name) {
		super();
		this.zipCode = zipCode;
		this.divisionCode = divisionCode;
		this.name = name;
	}

	@Column(name = "a_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "a_zipcode", nullable = false)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "a_divisioncode", unique = true, nullable = false)
	public String getDivisionCode() {
		return divisionCode;
	}
	
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	
	@Column(name = "a_level")
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

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Area [name=" + name + ", zipCode=" + zipCode + ",divisionCode=" +divisionCode + ", level=" + level
				+ "]";
	}
}
