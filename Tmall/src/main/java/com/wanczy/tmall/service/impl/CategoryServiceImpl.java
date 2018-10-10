package com.wanczy.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanczy.tmall.mapper.CategoryMapper;
import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	public List<Category> list() {
		// TODO Auto-generated method stub
		return this.categoryMapper.list();
	}
	public void add(Category category) {
		this.categoryMapper.add(category);
		
	}
	public void delete(int id) {
		this.categoryMapper.delete(id);
		
	}
	public Category get(int id) {
		// TODO Auto-generated method stub
		return this.categoryMapper.get(id);
	}
	public void update(Category category) {
		this.categoryMapper.update(category);
	}
 
}
