package com.tz.tpcs.web.form;

import java.util.List;

/**
 * 分页封装类， 用于实现分页+查询功能
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 14:25
 * @param <T> 当前需要分页的实体类
 */
public class Pager<T> {

    public static final Integer MAX_PAGE_SIZE = 50;// 每页最大记录数限制
    public static final Integer DEFAULT_PAGE_SIZE = 10;// 每页最大记录数限制


	private Integer pageNumber = 1; // 当前页码，初始值1
	private Integer pageSize; // 每页记录数, 初始值10
	private Integer totalCount = 0; // 总记录数，初始值0
	private Integer pageCount = 0; // 总页数， 初始值0
	private List<T> list; // 数据List，用于存放分页数据

    /** empty constructor */
    public Pager() {
    }

	/** 有参构造 */
    public Pager(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //getter & setter
    public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置页码
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * 获得页码
	 * @return 页码
	 */
    public Integer getPageSize() {
		if(pageSize == null){
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	/**
	 * 设置每页显示条数
	 * @param pageSize 每页显示条数
	 */
	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		} else if(pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

    public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获得总分页数
	 * @return 总分页数
	 */
    public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
