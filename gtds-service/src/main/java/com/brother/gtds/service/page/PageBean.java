package com.brother.gtds.service.page;

import java.util.List;

/**
 * 处理分页的类
 */
public class PageBean<T> {

	//每页记录数
	private int pageSize;
	//总记录数
	private int rows;
	//当前页
	private int currentPage;
	//当前页的List
	private List<T> list;

	public int getRows() {
		return rows;
	}

	public int getCurrentPage() {
		if(currentPage <= 0)
			currentPage = 1;
		if(currentPage > this.getTotalPageCount())
			currentPage = this.getTotalPageCount();
		return currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	/**
	 * 返回总页数
	 */
	public int getTotalPageCount()
	{
		int totalPageCount = (int)rows / pageSize;
		
		if(rows % pageSize != 0){
			totalPageCount++;
		}
		
		return totalPageCount;
	}
	
	/**
	 * 得到当前开始记录号
	 */
	public int getCurrentPageOffset()
	{
		return (this.getCurrentPage() -1) * pageSize;
	}
	
	//是否有上一页
	public boolean isHasPrev()
	{
		return this.getCurrentPage() > 1;
	}
	//是否有下一页
	public boolean isHasNext()
	{
		return this.getCurrentPage() < this.getTotalPageCount();
	}
}
