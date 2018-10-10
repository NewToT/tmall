package com.wanczy.tmall.service;

import java.util.List;

import com.wanczy.tmall.pojo.Category;
import com.wanczy.tmall.pojo.Product;

public interface ProductService {
	public void add(Product p);
	
	public void delete(int id);
	
	public void update(Product p);
	
	public Product get(int id);
	
	public List<Product> list(int cid);//获得这个分类下所有的商品
	
	public void setFirstProductImage(Product p);
//	为多个分类填充商品的方法，用于在首页左侧纵向导航栏显示推荐商品
	public void fill(Category c);
	
	public void fill(List<Category> cs);
	
	public void fillByRow(List<Category> cs);
	
//	为商品设置销售数量与评论数量的方法
	public void setSaleAndReviewNumber(Product p);
	
	public void setSaleAndReviewNumber(List<Product> ps);
//搜索商品	
	public List<Product> search(String keyword);
}
