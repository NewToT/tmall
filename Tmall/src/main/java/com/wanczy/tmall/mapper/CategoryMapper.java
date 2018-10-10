package com.wanczy.tmall.mapper;

import java.util.List;

import com.wanczy.tmall.pojo.Category;


public interface CategoryMapper {
	public   List<Category> list();
	 
	public void add(Category category);
	
	public void delete(int id);
	
	public Category get(int id);
	
	void update(Category category);
}
